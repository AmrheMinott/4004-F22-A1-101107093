package player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import constants.DieSides;
import constants.GameStatus;
import constants.ServerConstants;
import fortune_cards.FortuneCard;
import pirates_logic.GameLogic;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String playerName = "";
    private boolean hasRoundPlayed = false;
    private int currentScore = 0;
    private final int END_PLAYER_ROUND = 2;
    private final int RE_ROLL_COMMAND = 1;

    private FortuneCard fortuneCard = new FortuneCard();
    private Client clientConnection;
    private GameLogic game = new GameLogic();

    private ArrayList<String> dieRolled = new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
            DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE));
    private static Scanner playerNameScanner;

    public Player(String playerName, int portId) {
        this.playerName = playerName;
        this.clientConnection = new Client(portId);
    }

    public void setScore(int score) {
        this.currentScore = score;
    }

    public void startGame() {
        while (true) {
            

        }
    }

    public String getName() {
        return this.playerName;
    }

    @SuppressWarnings("resource")
    public void playRound() {

    }

    public Player getPlayer() {
        return this;
    }

    public void killClient() {
        clientConnection.terminate();
    }

    private class Client {
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client() {
            try {
                socket = new Socket("localhost", ServerConstants.GAME_SERVER_PORT_NUMBER);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public Client(int portId) {
            try {
                socket = new Socket("localhost", ServerConstants.GAME_SERVER_PORT_NUMBER);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        /**
         * Updates the server with the information of the current player.
         */
        public void sendPlayer() {
            try {
                dOut.writeObject(getPlayer());
                dOut.flush();
            } catch (IOException e) {
                System.out.println("ERROR - Player failed to send!");
                e.printStackTrace();
            }
        }

        /**
         * Sending current player score to server.
         */
        public void sendScores() {
            try {

                dOut.writeInt(currentScore);
                dOut.flush();

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
        }

        /**
         * Receive round status from the server indicating how the player should perform
         * going forward.
         * 
         * @return int - the value of the status from the Server
         */
        public int receiveRoundStatus() {
            try {
//				dIn.readObject();
                return dIn.readInt();

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return 0;
        }

        public void terminate() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {
        playerNameScanner = new Scanner(System.in);
        System.out.println("Enter Name!");
        String name = playerNameScanner.nextLine();
        Player player = new Player(name, (int) (Math.random() * 98764321));

        player.startGame();
    }

}
