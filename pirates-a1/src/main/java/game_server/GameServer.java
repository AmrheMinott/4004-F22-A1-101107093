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
import pirates_logic.GameLogic;
import player.Player;

public class GameServer implements Runnable {

    ServerSocket serverSocket = null;
    private int TOTAL_NUMBER_OF_PLAYERS = 1;

    private int currentPlayer = 0;
    private int numPlayers;
    public boolean isAcceptingConnections;
    private boolean isRunning = true;

    ArrayList<Server> playerServer = new ArrayList<Server>();
    ArrayList<Player> players = new ArrayList<Player>();
    GameLogic game = new GameLogic();
    
    ArrayList<FortuneCard> deck = new ArrayList<FortuneCard>();

    public GameServer() {
        System.out.println("Opening Game Server");

        for (int i = 0; i < 4; i++) {
            deck.add(new Chest());
            deck.add(new Captain());
            deck.add(new MonkeyBusiness());
            deck.add(new DiamondCard());
            deck.add(new GoldCard());
        }
        for (int i = 0; i < 2; i++) {
            deck.add(new SeaBattleTypeOne());
            deck.add(new SeaBattleTypeTwo());
            deck.add(new SeaBattleTypeThree());
        }
        Collections.shuffle(deck);
        try {
            serverSocket = new ServerSocket(ServerConstants.GAME_SERVER_PORT_NUMBER);
        } catch (IOException ex) {
            System.out.println("Server Failed to open");
        }
    }

    @Override
    public void run() {
        try {
            acceptConnections();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (isRunning) {

        }
    }

    synchronized public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < TOTAL_NUMBER_OF_PLAYERS) {
                isAcceptingConnections = true;
                Socket s = serverSocket.accept();
                numPlayers++;

                Server server = new Server(s, numPlayers);

                // Get the player name
                Player player = (Player) server.dIn.readObject();
                System.out.println("Player " + server.playerId + " ~ " + player.getName() + " ~ has joined");

                players.add(player);
                playerServer.add(server);
            }
            System.out.println("Three players have joined the game");

            // Start server threads
            for (int i = 0; i < playerServer.size(); i++) {
                Thread t = new Thread(playerServer.get(i));
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
        isAcceptingConnections = false;
    }

    synchronized public void gameLoop() {
        int roundsPlayed = 0;
        int deckIndex = 0;
        while (true) {
            System.out.println("*****************************************");
            System.out.println("Round number " + roundsPlayed);
            playerServer.get(currentPlayer).sendRoundStatus(new PirateStatus(deck.get(deckIndex), GameStatus.PLAY));

            int score_player = playerServer.get(currentPlayer).receiveScoreFromCurrentPlayer();
            System.out.println("SERVER: Score = " + score_player);
            players.get(currentPlayer).setScore(score_player);
            playerServer.get(currentPlayer).sendRoundStatus(new PirateStatus(null, GameStatus.PLAY));

            roundsPlayed++;
            currentPlayer++;
            deckIndex ++;
            if (currentPlayer == TOTAL_NUMBER_OF_PLAYERS)
                currentPlayer = 0;
            if (deckIndex == deck.size())
                deckIndex = 0;
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
         * @param status - int value representing the status for the player.
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
         * Receives from the score value from the current player.
         * 
         * @return int - the value of the score that the player calculated and sent back
         *         to the server.
         */
        public int receiveScoreFromCurrentPlayer() {
            try {

                return dIn.readInt();
            } catch (Exception e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return 0;
        }

        public void terminate() throws IOException {
            socket.close();
            isRunning = false;
        }
    }

    public ArrayList<FortuneCard> getDeck() {
        return deck;
    }
}
