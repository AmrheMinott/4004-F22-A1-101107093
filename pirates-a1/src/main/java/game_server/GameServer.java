package game_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import constants.GameStatus;
import constants.ServerConstants;
import fortune_cards.Captain;
import fortune_cards.Chest;
import fortune_cards.DiamondCard;
import fortune_cards.FortuneCard;
import fortune_cards.GoldCard;
import fortune_cards.MonkeyBusiness;
import fortune_cards.SeaBattleTypeOne;
import fortune_cards.SeaBattleTypeThree;
import fortune_cards.SeaBattleTypeTwo;
import fortune_cards.SkullTypeOne;
import fortune_cards.SkullTypeTwo;
import fortune_cards.Sorceress;
import pirates_logic.GameLogic;
import player.Player;

public class GameServer implements Runnable {

    private ServerSocket serverSocket = null;
    private int TOTAL_NUMBER_OF_PLAYERS = 3;

    private int currentConnectedPlayer = 0;
    private int deckIndex = 0;
    private int numberOfConnectedPlayers;

    private ArrayList<Server> playerServer = new ArrayList<Server>();
    private ArrayList<Player> players = new ArrayList<Player>();

    public ArrayList<Server> getPlayerServer() {
        return playerServer;
    }

    private HashMap<String, Integer> playerScores = new HashMap<String, Integer>();
    private HashMap<String, Integer> playerDeductions = new HashMap<String, Integer>();

    private ArrayList<FortuneCard> deck = new ArrayList<FortuneCard>();
    private GameLogic gameLogic = new GameLogic();

    public GameServer() {
        shuffleDeck();
        try {
            serverSocket = new ServerSocket(ServerConstants.GAME_SERVER_PORT_NUMBER);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public GameServer(int port) {
        shuffleDeck();
    }

    private void shuffleDeck() {
        deck.clear();
        for (int i = 0; i < 4; i++) {
            deck.add(new Chest());
            deck.add(new Captain());
            deck.add(new MonkeyBusiness());
            deck.add(new DiamondCard());
            deck.add(new GoldCard());
            deck.add(new Sorceress());
        }
        for (int i = 0; i < 2; i++) {
            deck.add(new SeaBattleTypeOne());
            deck.add(new SeaBattleTypeTwo());
            deck.add(new SeaBattleTypeThree());
            deck.add(new SkullTypeOne());
            deck.add(new SkullTypeTwo());
        }
        Collections.shuffle(deck);
    }

    @Override
    public void run() {
        try {
            acceptConnections();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }

    synchronized public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numberOfConnectedPlayers < TOTAL_NUMBER_OF_PLAYERS) {
                Socket s = serverSocket.accept();
                numberOfConnectedPlayers++;

                Server server = new Server(s);

                Player player = (Player) server.objectInputStream.readObject();
                System.out.println("Player: " + player.getName() + " has joined.");

                players.add(player);
                playerServer.add(server);
            }
            System.out.println(TOTAL_NUMBER_OF_PLAYERS + " players have joined the game!");

            for (int i = 0; i < playerServer.size(); i++) {
                Thread t = new Thread(playerServer.get(i));
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Could not connect with all " + TOTAL_NUMBER_OF_PLAYERS + " players.");
        }
    }

    synchronized public void gameLoop() {
        int roundsPlayed = 1;
        int roundsLeft = -1;

        PirateStatus status = new PirateStatus(deck.get(deckIndex), GameStatus.PLAY, 0, 0);

        while (true) {
            System.out.println("Round number " + roundsPlayed);
            status.setFortuneCard(deck.get(deckIndex));
            status.setMessageCode(GameStatus.PLAY);
            status.setScore(0);
            status.setScoreDeduction(0);

            playerServer.get(currentConnectedPlayer).sendRoundStatus(status);

            status = playerServer.get(currentConnectedPlayer).receiveStatusFromCurrentPlayer();

            playerScores.put(players.get(currentConnectedPlayer).getName(), status.getScore());

            printPlayersScore();

            System.out.println("SERVER: " + players.get(currentConnectedPlayer) + "\n" + status);
            if (status.getMessageCode() == GameStatus.ISLAND_OF_THE_DEAD) {
                for (int i = 0; i < TOTAL_NUMBER_OF_PLAYERS; i++) {
                    if (i != currentConnectedPlayer) {
                        System.out.println(
                                "Server: -> " + players.get(i).getName() + " will lose: " + status.getScoreDeduction());
                        updatePlayerDeductions(status, i);
                        status.setScoreDeduction(playerDeductions.get(players.get(i).getName()));
                        status.setMessageCode(GameStatus.DEDUCT_COMMAND);
                        playerServer.get(i).sendRoundStatus(status);
                        playerDeductions.put(players.get(i).getName(), 0);
                        status = playerServer.get(i).receiveStatusFromCurrentPlayer();

                        playerScores.put(players.get(i).getName(), status.getScore());
                        System.out.println("SERVER: Scores after deductions.");
                        printPlayersScore();
                    }
                }
            }

            if (anyPotentialWinners()) {
                if (roundsLeft < 0) {
                    roundsLeft = TOTAL_NUMBER_OF_PLAYERS;
                }
                roundsLeft--;
                if (roundsLeft == 0) {
                    String playerWinner = gameLogic.determineWinner(playerScores);
                    for (int i = 0; i < TOTAL_NUMBER_OF_PLAYERS; i++) {
                        status.setMessageCode(GameStatus.STOP);
                        status.setWinMessage(playerWinner + " won with a score of " + playerScores.get(playerWinner));
                        playerServer.get(i).sendRoundStatus(status);
                        status = playerServer.get(i).receiveStatusFromCurrentPlayer();
                    }

                    System.out.println("Winner -> " + playerWinner);
                    break;
                }
            } else {
                roundsLeft = -1;
            }

            status.setFortuneCard(null);
            status.setMessageCode(GameStatus.WAITING);
            status.setScore(playerScores.get(players.get(currentConnectedPlayer).getName()));
            status.setScoreDeduction(0);

            playerServer.get(currentConnectedPlayer).sendRoundStatus(status);

            roundsPlayed++;
            currentConnectedPlayer++;
            deckIndex++;
            if (currentConnectedPlayer == TOTAL_NUMBER_OF_PLAYERS)
                currentConnectedPlayer = 0;
            if (deckIndex == deck.size()) {
                shuffleDeck();
                deckIndex = 0;
            }
        }

        for (Server s : playerServer) {
            try {
                s.terminate();
            } catch (IOException e) {
                System.out.println("Server could not close!");
                e.printStackTrace();
            }
        }
        System.exit(1);
    }

    private boolean anyPotentialWinners() {
        for (Entry<String, Integer> entry : playerScores.entrySet()) {
            if (entry.getValue() >= ServerConstants.MAX_SCORE) {
                return true;
            }
        }
        return false;
    }

    private void printPlayersScore() {
        String scoreString = "";

        for (Entry<String, Integer> entry : playerScores.entrySet()) {
            scoreString += (entry.getKey() + ": " + playerScores.get(entry.getKey()) + " ");
        }

        scoreString = (playerScores.keySet().size() == 0 ? "No Scores Yet." : scoreString);
        System.out.println("Players Scores: " + scoreString);
    }

    private void updatePlayerDeductions(PirateStatus status, int playerIndex) {
        if (playerDeductions.containsKey(players.get(playerIndex).getName())) {
            playerDeductions.put(players.get(playerIndex).getName(),
                    (playerDeductions.get(players.get(playerIndex).getName()) + status.getScoreDeduction()));
        } else {
            playerDeductions.put(players.get(playerIndex).getName(), status.getScoreDeduction());
        }
    }

    public static void main(String args[]) throws Exception {
        try {
            GameServer gameServer = new GameServer();

            gameServer.acceptConnections();
            gameServer.gameLoop();
        } catch (NullPointerException e) {
            System.out.println("Servers are down due to player premature exit from system.");
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private boolean isRunning = true;

        public Server(Socket socket) {
            this.socket = socket;
            try {
                objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
                objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        public void run() {
            try {
                while (isRunning) {
                }

            } catch (Exception ex) {
                System.out.println("Run failed");
                ex.printStackTrace();
            }
        }

        /**
         * Communicates to the current playing player if they are able to play.
         * 
         * @param status - PirateStatus value representing the status for the player.
         */
        public void sendRoundStatus(PirateStatus status) {
            try {
                objectOutputStream.writeObject(status);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (Exception e) {
                System.out.println("SERVER: Player Status not sent.");
                e.printStackTrace();
            }
        }

        /**
         * Receives from the status from the current player.
         * 
         * @return PirateStatus - the status of the player and sent back
         *         to the server.
         */
        public PirateStatus receiveStatusFromCurrentPlayer() {
            try {
                return (PirateStatus) objectInputStream.readObject();
            } catch (Exception e) {
                System.out.println("SERVER: Player status not received!");
                e.printStackTrace();
            }
            return null;
        }

        public void terminate() throws IOException {
            socket.close();
            isRunning = false;
        }
    }

    /**
     * Returns the deck of cards that the players will be using.
     * 
     * @return ArrayList<FortuneCard>
     */
    public ArrayList<FortuneCard> getDeck() {
        return deck;
    }
}
