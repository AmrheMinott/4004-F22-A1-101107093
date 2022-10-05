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
import constants.PlayerCommand;
import constants.ServerConstants;
import fortune_cards.Chest;
import fortune_cards.FortuneCard;
import fortune_cards.SeaBattle;
import fortune_cards.Sorceress;
import game_server.PirateStatus;
import pirates_logic.GameLogic;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String playerName = "";
    private int currentScore = 0;
    private int playerOption = -99;

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

    public Player(String playerName) {
        this.playerName = playerName;
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
            }

            if (status.getMessage() == GameStatus.PLAY) {
                System.out.println("STATUS " + status);
                playRound();
                clientConnection.sendScores();
            }

        }
    }

    @SuppressWarnings("resource")
    public void playRound() {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Start of " + this.playerName + " turn. Roll All 8 dice.");
        this.setRoll(new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
                DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE)));
        game.rollAllEightDie(dieRolled);

        // handle edge case where the player rolled 3 skulls but activates Sorceress and
        // drops it from 3 to 2 skulls
        if (hasPlayerDied()) {
            return;
        }

        game.printPlayerDice(dieRolled);

        // Has player died on first Roll Or gone to Island of the Dead.

        // this is where the main game play will be conducted by the player
        while (true) {
            menuOption();
            playerOption = 0;
            if (!playerInput.hasNextLine()) {
                break;
            }
            playerOption = Integer.parseInt(playerInput.nextLine());
            switch (playerOption) {
                case PlayerCommand.RE_ROLL_COMMAND:
                    playerReroll(playerInput);
                    break;
                case PlayerCommand.ACTIVATE_SORCERER_COMMAND:
                    ((Sorceress) this.fortuneCard).activateSorceress(this.dieRolled);
                    game.printPlayerDice(dieRolled);
                    break;
                case PlayerCommand.ADD_TO_CHEST_COMMAND:
                    addToChest(playerInput);
                    break;
                case PlayerCommand.REMOVE_FROM_CHEST_COMMAND:
                    removeFromChest(playerInput);
                    break;
                case PlayerCommand.END_PLAYER_ROUND:
                    break;
                default:
                    System.out.println("INVALID OPTION GIVEN\n\n");
            }
            if (hasPlayerDied()) {
                // handle edge case where the player rolled 3 skulls but activates Sorceress and
                // drops it from 3 to 2 skulls
                return;
            }

            if (playerOption == PlayerCommand.END_PLAYER_ROUND) {
                System.out.println(this.playerName + " has ended their turn.");
                incrementScore(game.scoreTurn(dieRolled, fortuneCard));
                return;
            }
        }

        playerInput.close();
    }

    private void playerReroll(Scanner playerInput) {
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
            System.out.println("After reroll your new die hand is");
            game.printPlayerDice(dieRolled);
            break;
        }
    }

    private void removeFromChest(Scanner playerInput) {
        if (((Chest) this.fortuneCard).getChestContent().size() > 0) {
            System.out.println("Chest contents");
            game.printPlayerDice(((Chest) this.fortuneCard).getChestContent());
            System.out.println("Specify the index you wish to remove -> 1...");
            playerOption = Integer.parseInt(playerInput.nextLine());
            if (playerOption > 0 && playerOption < ((Chest) this.fortuneCard).getChestContent().size()) {
                this.dieRolled.add(((Chest) this.fortuneCard).takeOut(playerOption - 1));
            }
            playerOption = -999;
            game.printPlayerDice(dieRolled);
        }
    }

    private void addToChest(Scanner playerInput) {
        if (this.dieRolled.size() > 0) {
            game.printPlayerDice(this.dieRolled);
            System.out.println("Specify the index you wish to add -> 1...");
            playerOption = Integer.parseInt(playerInput.nextLine());

            if (!addItemAtIndexToChest(playerOption)) {
                System.out.println("Item not removed as the index is too large or is a SKULL.");
            }

            playerOption = -999;
            game.printPlayerDice(dieRolled);
        }
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
        if (this.fortuneCard instanceof SeaBattle) {
            System.out.println(this.fortuneCard);
        }
        System.out.println("\n");
        System.out.println(PlayerCommand.RE_ROLL_COMMAND + " -> Re roll die of choice.... FORMAT 1 2");
        System.out.println(PlayerCommand.END_PLAYER_ROUND + " -> End turn!");

        if (this.fortuneCard instanceof Sorceress) {
            if (!((Sorceress) this.fortuneCard).getHasBeenActivated() && playerHasSkulls()) {
                System.out.println(PlayerCommand.ACTIVATE_SORCERER_COMMAND + " -> Activate Sorcerer Card");
            }
        }

        if (this.fortuneCard instanceof Chest) {
            System.out.println("Chest contents");
            game.printPlayerDice(((Chest) this.fortuneCard).getChestContent());
            System.out.println(PlayerCommand.ADD_TO_CHEST_COMMAND + " -> Add to Chest Card");
            System.out.println(PlayerCommand.REMOVE_FROM_CHEST_COMMAND + " -> Remove from Chest Card");
        }
    }

    private class Client {
        Socket socket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;

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

    private boolean playerHasSkulls() {
        return dieRolled.contains(DieSides.SKULL);
    }

    private void incrementScore(int score) {
        this.currentScore = (this.currentScore + score) < 0 ? 0 : this.currentScore + score;
    }

    public static void main(String args[]) throws ClassNotFoundException {
        playerNameScanner = new Scanner(System.in);
        System.out.println("Enter Name!");
        String name = playerNameScanner.nextLine();
        Player player = new Player(name, (int) (Math.random() * 98764321));

        player.startGame();
    }

    public void setRoll(ArrayList<String> dieRolled) {
        this.dieRolled = dieRolled;
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

    public Integer getScore() {
        return this.currentScore;
    }

    public void setScore(int score) {
        this.currentScore = (score < 0) ? 0 : score;
    }

    public String getName() {
        return this.playerName;
    }

    public Player getPlayer() {
        return this;
    }

    public boolean addItemAtIndexToChest(int index) {
        if (index - 1 >= this.dieRolled.size() || index <= 0) {
            return false;
        }
        String item = this.dieRolled.get(index - 1);
        if (item != DieSides.SKULL) {
            ((Chest) this.fortuneCard).addDiceToChest(item);
            this.dieRolled.remove(index - 1);
            return true;
        }
        return false;
    }

}
