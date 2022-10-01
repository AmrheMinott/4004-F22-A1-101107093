package game_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import constants.GameStatus;
import constants.ServerConstants;
import pirates_logic.GameLogic;
import player.Player;

public class GameServer implements Runnable {

    ServerSocket serverSocket = null;
    private int TOTAL_NUMBER_OF_PLAYERS = 2;

    private int currentPlayer = 0;
    private int numPlayers;
    public boolean isAcceptingConnections;
    private boolean isRunning = true;

    ArrayList<Server> playerServer = new ArrayList<Server>();
    ArrayList<Player> players = new ArrayList<Player>();
    GameLogic game = new GameLogic();

    public GameServer() {
        System.out.println("Opening Game Server");

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
        while (true) {
            System.out.println("*****************************************");
            System.out.println("Round number " + roundsPlayed);
            playerServer.get(currentPlayer).sendRoundStatus(GameStatus.PLAY);

            int score_player = playerServer.get(currentPlayer).receiveScoreFromCurrentPlayer();
            System.out.println("SERVER: Score = " + score_player);
            players.get(currentPlayer).setScore(score_player);
            playerServer.get(currentPlayer).sendRoundStatus(GameStatus.WAITING);

            roundsPlayed++;
            currentPlayer++;
            if (currentPlayer == TOTAL_NUMBER_OF_PLAYERS)
                currentPlayer = 0;
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
        public void sendRoundStatus(int status) {
            try {
                dOut.writeInt(status);
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
}
