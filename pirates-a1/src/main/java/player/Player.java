package player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import constants.DieSides;
import constants.GameStatus;
import constants.ServerConstants;
import fortune_cards.FortuneCard;
import fortune_cards.Sorceress;
import game_server.PirateStatus;
import pirates_logic.GameLogic;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String playerName = "";
    private boolean hasRoundPlayed = false;
    private int currentScore = 0;
    private final int END_PLAYER_ROUND = 2;
    private final int RE_ROLL_COMMAND = 1;
    private final int ACTIVATE_SORCERER_COMMAND = 3;

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

    public void startGame() throws ClassNotFoundException {
        while (true) {
            PirateStatus status = clientConnection.receiveRoundStatus();
            this.fortuneCard = status.getFortuneCard();
            System.out.println("Status" + status);
            if (status.getMessage() == GameStatus.STOP) {
                break;
            }

            if (status.getMessage() == GameStatus.WAITING) {
                System.out.println("HERE EWAITING");
            }

            if (status.getMessage() == GameStatus.PLAY) {
                System.out.println("STATUS " + status + " hasRoundPlayed " + hasRoundPlayed);
                playRound();
                System.out.println("HELLO");
                clientConnection.sendScores();
            }

        }
    }

    public String getName() {
        return this.playerName;
    }

    @SuppressWarnings("resource")
    public void playRound() {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Start of " + this.playerName + " Turn. Roll All 8 dice.");
        game.rollAllEightDie(dieRolled);

        // handle edge case where the player rolled 3 skulls but activates Sorceress and
        // drops it from 3 to 2 skulls
        if (hasPlayerDied()) {
            return;
        }

        game.printPlayerDice(dieRolled);

        // Has player died on first Roll Or gone to Island of the Dead.

        boolean exit = true;
        // this is where the main game play will be conducted by the player
        while (exit) {
            menuOption();
            int playerOption = 0;
            if (!playerInput.hasNextLine()) {
                System.out.println("UMMMM");
                break;
            }
            playerOption = Integer.parseInt(playerInput.nextLine());
            if (playerOption == RE_ROLL_COMMAND) {
                while (true) {
                    game.printPlayerDice(dieRolled);

                    System.out.println("Select the die you wish to re-roll: Format -> 1 2...");
                    String selectedDies = playerInput.nextLine();

                    String[] die = selectedDies.split(" ");
                    if (die.length != 2)
                        continue;

                    int index_1 = Integer.valueOf(die[0]) - 1;
                    int index_2 = Integer.valueOf(die[1]) - 1;
                    try {
                        if (dieRolled.get(index_1) == DieSides.SKULL
                                || dieRolled.get(index_2) == DieSides.SKULL) {
                            System.out.println("You selected a Skull, and Skulls can not be re rolled.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    game.rollDiePair(index_1 + 1, index_2 + 1, dieRolled);
                    System.out.println("New Roll");
                    game.printPlayerDice(dieRolled);
                    break;
                }
            }
            if (playerOption == ACTIVATE_SORCERER_COMMAND) {
                activateSorceress();
                game.printPlayerDice(dieRolled);
            }
            if (hasPlayerDied()) {
                // handle edge case where the player rolled 3 skulls but activates Sorceress and
                // drops it from 3 to 2 skulls
                return;
            }
            if (playerOption == END_PLAYER_ROUND) {
                hasRoundPlayed = true;
                exit = false;
                System.out.println("END TURN COMMAND");
                currentScore += game.scoreTurn(dieRolled, fortuneCard);
                return;
            }
        }

        playerInput.close();
    }

    public Player getPlayer() {
        return this;
    }

    public void killClient() {
        clientConnection.terminate();
    }

    private boolean hasPlayerDied() {
        int skullCount = 0;
        for (String dieFace : dieRolled) {
            if (dieFace.equals(DieSides.SKULL)) {
                skullCount++;
            }
        }
        if (skullCount >= 3) {
            return true;
        }
        return false;
    }

    private void menuOption() {
        System.out.println("\n\n\n");
        System.out.println(RE_ROLL_COMMAND + " -> Re roll die of choice.... FORMAT 1 2");
        System.out.println(END_PLAYER_ROUND + " -> End turn!");

        if (this.fortuneCard instanceof Sorceress) {
            if (!((Sorceress) this.fortuneCard).getHasBeenActivated() && playerHasSkulls()) {
                System.out.println(ACTIVATE_SORCERER_COMMAND + " -> Activate Sorcerer Card");
            }
        }
    }

    private class Client {
        Socket socket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;

        public Client() {
            try {
                socket = new Socket("localhost", ServerConstants.GAME_SERVER_PORT_NUMBER);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public Client(int portId) {
            try {
                socket = new Socket("localhost", ServerConstants.GAME_SERVER_PORT_NUMBER);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());

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
                objectOutputStream.writeObject(getPlayer());
                objectOutputStream.flush();
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

                objectOutputStream.writeInt(currentScore);
                objectOutputStream.flush();

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
         * @throws ClassNotFoundException
         */
        public PirateStatus receiveRoundStatus() throws ClassNotFoundException {
            try {
                // dIn.readObject();
                return (PirateStatus) objectInputStream.readObject();

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return null;
        }

        public void terminate() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) throws ClassNotFoundException {
        playerNameScanner = new Scanner(System.in);
        System.out.println("Enter Name!");
        String name = playerNameScanner.nextLine();
        Player player = new Player(name, (int) (Math.random() * 98764321));

        player.startGame();
    }

    public void setRoll(ArrayList<String> dieRolled) {
        Collections.copy(this.dieRolled, dieRolled);
    }

    public ArrayList<String> getRoll() {
        return this.dieRolled;
    }

    public void setRollAtIndex(int index, String dieSide) {
        this.dieRolled.set(index, dieSide);
    }

    public void setFortuneCard(FortuneCard fortuneCard) {
        this.fortuneCard = fortuneCard;
    }

    public FortuneCard getFortuneCard() {
        return this.fortuneCard;
    }

    public boolean activateSorceress() {
        if (!((Sorceress) this.fortuneCard).getHasBeenActivated() && playerHasSkulls()) {
            setRollAtIndex(dieRolled.indexOf(DieSides.SKULL),
                    Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.GOLD,
                            DieSides.SWORD, DieSides.PARROT, DieSides.DIAMOND).get((int) (Math.random() * 6)));
            ((Sorceress) this.fortuneCard).activate();
            return true;
        }
        return false;
    }

    private boolean playerHasSkulls() {
        return dieRolled.contains(DieSides.SKULL);
    }

}
