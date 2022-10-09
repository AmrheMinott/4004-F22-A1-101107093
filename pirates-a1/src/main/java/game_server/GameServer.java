package game_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

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
import player.Player;

public class GameServer implements Runnable {

    private ServerSocket serverSocket = null;
    private int TOTAL_NUMBER_OF_PLAYERS = 1;

    private int currentConnectedPlayer = 0;
    private int numberOfConnectedPlayers;

    private ArrayList<Server> playerServer = new ArrayList<Server>();
    private ArrayList<Player> players = new ArrayList<Player>();
//    private GameLogic game = new GameLogic();

    private ArrayList<FortuneCard> deck = new ArrayList<FortuneCard>();

    public GameServer() {
        shuffleDeck();
        try {
            serverSocket = new ServerSocket(ServerConstants.GAME_SERVER_PORT_NUMBER);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
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

                Server server = new Server(s, numberOfConnectedPlayers);

                Player player = (Player) server.dIn.readObject();
                System.out.println("Player " + server.playerId + " ~ " + player.getName() + " ~ has joined");

                players.add(player);
                playerServer.add(server);
            }
            System.out.println(TOTAL_NUMBER_OF_PLAYERS + " players have joined the game!");

            // Start server threads
            for (int i = 0; i < playerServer.size(); i++) {
                Thread t = new Thread(playerServer.get(i));
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Could not connect with all " + TOTAL_NUMBER_OF_PLAYERS + " players");
        }
    }

    synchronized public void gameLoop() {
        int roundsPlayed = 0;
        int deckIndex = 0;
        while (true) {
            System.out.println("Round number " + roundsPlayed);
            playerServer.get(currentConnectedPlayer)
                    .sendRoundStatus(new PirateStatus(deck.get(deckIndex), GameStatus.PLAY, 0, 0));

            PirateStatus status = playerServer.get(currentConnectedPlayer).receiveScoreFromCurrentPlayer();
            int score_player = status.getScore();
            int message_code = status.getMessageCode();
            int deductions = status.getScoreDeduction();
            System.out.println("SERVER: Score = " + score_player + " DEDUCTIONS: " + deductions);
            if (message_code == GameStatus.ISLAND_OF_THE_DEAD)
                updateOtherPlayerScoresAfterIslandOfTheDead(deductions);
            playerServer.get(currentConnectedPlayer).sendRoundStatus(new PirateStatus(null, GameStatus.WAITING, 0, 0));

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
    }

    private void updateOtherPlayerScoresAfterIslandOfTheDead(int deductions) {
        if (TOTAL_NUMBER_OF_PLAYERS != 1) {
            for (int i = 0; i < TOTAL_NUMBER_OF_PLAYERS; i ++) {
                if (i != currentConnectedPlayer) {
                    players.get(i).incrementScore(deductions);
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        try {
            GameServer sr = new GameServer();

            sr.acceptConnections();
            sr.gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;
        private int playerId;
        private boolean isRunning = true;

        public Server(Socket s, int playerid) {
            socket = s;
            playerId = playerid;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        public void run() {
            try {
                while (isRunning) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Run failed");
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Communicates to the current playing player if they are able to play.
         * 
         * @param status - PirateStatus value representing the status for the player.
         */
        public void sendRoundStatus(PirateStatus status) {
            try {
                dOut.writeObject(status);
                dOut.flush();
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
        public PirateStatus receiveScoreFromCurrentPlayer() {
            try {
                return (PirateStatus) dIn.readObject();
            } catch (Exception e) {
                System.out.println("Score sheet not received");
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
