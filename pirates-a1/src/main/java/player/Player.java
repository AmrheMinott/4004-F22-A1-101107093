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
import constants.PlayerCommand;
import constants.ServerConstants;
import fortune_cards.Chest;
import fortune_cards.FortuneCard;
import fortune_cards.SeaBattle;
import fortune_cards.SkullTypeOne;
import fortune_cards.SkullTypeTwo;
import fortune_cards.Sorceress;
import game_server.PirateStatus;
import pirates_logic.GameLogic;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String playerName = "";
    private int currentScore = 0;
    private int playerOption = -99;
    private boolean isPlayerAlive = true;
    private boolean islandOfTheDeadMode = false;

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
            if (status.getMessageCode() == GameStatus.STOP) {
                break;
            }

            if (status.getMessageCode() == GameStatus.DEDUCT_COMMAND) {
                if (status.getScoreDeduction() < 0) {
                    System.out.println(
                            "\n\n While waiting you have lost some points sadly of " + status.getScoreDeduction());
                    incrementScore(status.getScoreDeduction());
                    status.setScoreDeduction(0);
                    clientConnection.sendDeductStatus(status);
                    System.out.println("AFTER " + this);
                }
            }

            if (status.getMessageCode() == GameStatus.PLAY) {
                System.out.println(status);
                playRound();
                if (islandOfTheDeadMode) {
                    status.setFortuneCard(this.fortuneCard);
                    status.setMessageCode(GameStatus.ISLAND_OF_THE_DEAD);
                    status.setScore(this.currentScore);
                    status.setScoreDeduction(game.scoreIslandOfTheDeadDeduction(this.dieRolled));
                    islandOfTheDeadMode = false;

                    clientConnection.sendEndOfRoundStatus(status);
                } else {
                    status.setFortuneCard(this.fortuneCard);
                    status.setMessageCode(GameStatus.NONE);
                    status.setScore(this.currentScore);
                    status.setScoreDeduction(0);

                    clientConnection.sendEndOfRoundStatus(status);
                }
                islandOfTheDeadMode = false;
            }
        }
    }

    @SuppressWarnings("resource")
    public void playRound() {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("\n\n\n\n Start of " + this.playerName + " turn with FC Card "
                + this.fortuneCard.getClass().getSimpleName() + ". Roll All 8 dice.");
        this.isPlayerAlive = true;
        if (this.fortuneCard instanceof SkullTypeTwo) {
            this.setRoll(new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE,
                    DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.SKULL, DieSides.SKULL)));
        } else if (this.fortuneCard instanceof SkullTypeOne) {
            this.setRoll(new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE,
                    DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.SKULL)));
        } else {
            this.setRoll(new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE,
                    DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE)));
        }
        game.rollAllEightDie(dieRolled);

        if (shouldGoToIslandOfDead()) {
            islandOfTheDeadMode = true;
            System.out.println("Welcome to the land of the dead! " + this);
            playerReroll(playerInput, true);
            System.out.println("End of turn after island of the dead! for -> " + this);
            return;
        }

        if (hasPlayerDied()) {
            return;
        }

        game.printPlayerDice(dieRolled);
        while (true) {
            menuOption();
            playerOption = 0;
            if (!playerInput.hasNextLine()) {
                break;
            }

            try {
                playerOption = Integer.parseInt(playerInput.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (playerOption) {
                case PlayerCommand.RE_ROLL_COMMAND:
                    playerReroll(playerInput, false);
                    break;
                case PlayerCommand.ACTIVATE_SORCERER_COMMAND:
                    sorceressActivation();
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

    private boolean shouldGoToIslandOfDead() {
        return Collections.frequency(this.dieRolled, DieSides.SKULL) >= 4;
    }

    private void playerReroll(Scanner playerInput, boolean landOfTheDead) {
        while (true) {
            game.printPlayerDice(dieRolled);

            if (((this.dieRolled.size() - Collections.frequency(dieRolled, DieSides.SKULL)) < 2) && landOfTheDead) {
                break;
            }

            System.out.println("Select the die you wish to re-roll: Format -> 1 2...");
            String selectedDies = playerInput.nextLine();

            String[] die = selectedDies.split(" ");
            if (die.length != 2)
                continue;

            int index_1 = Integer.valueOf(die[0]) - 1;
            int index_2 = Integer.valueOf(die[1]) - 1;
            try {
                if (dieRolled.get(index_1).equals(DieSides.SKULL)
                        || dieRolled.get(index_2).equals(DieSides.SKULL)) {
                    System.out.println("You selected a Skull, and Skulls can not be re rolled.");
                    continue;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Incorrect Format!");
                continue;
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Index out of bounds!");
                continue;
            }

            game.rollDiePair(index_1 + 1, index_2 + 1, dieRolled);
            System.out.println("After reroll your new die hand is");
            game.printPlayerDice(dieRolled);
            if (!this.dieRolled.get(index_2).equals(DieSides.SKULL)
                    && !this.dieRolled.get(index_1).equals(DieSides.SKULL) && landOfTheDead) {
                break;
            } else if (landOfTheDead) {
                continue;
            }
            break;
        }
    }

    private void removeFromChest(Scanner playerInput) {
        if (this.fortuneCard instanceof Chest && ((Chest) this.fortuneCard).getChestContent().size() > 0) {
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
        if (this.fortuneCard instanceof Chest && this.dieRolled.size() > 0) {
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

    public void sorceressActivation() {
        if (!(this.fortuneCard instanceof Sorceress))
            return;
        ((Sorceress) this.fortuneCard).activateSorceress(this.dieRolled);
    }

    public void killClient() {
        clientConnection.terminate();
    }

    private boolean hasPlayerDied() {
        if (this.fortuneCard instanceof Sorceress && !((Sorceress) this.fortuneCard).getHasBeenActivated()) {
            return false;
        }
        int skullCount = 0;
        for (String dieFace : dieRolled) {
            if (dieFace.equals(DieSides.SKULL)) {
                skullCount++;
            }
        }
        if (skullCount >= 3) {
            isPlayerAlive = false;
            return true;
        }
        return false;
    }

    private void menuOption() {
        System.out.println("\n");
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

                objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
                objectInputStream = new ObjectInputStream((socket.getInputStream()));

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
         * Sending current player status to server.
         */
        public void sendEndOfRoundStatus(PirateStatus pirateStatus) {
            try {

                objectOutputStream.writeObject(pirateStatus);
                objectOutputStream.flush();

            } catch (IOException e) {
                System.out.println("End of round status not sent!");
                e.printStackTrace();
            }
        }

        public void sendDeductStatus(PirateStatus pirateStatus) {
            try {

                objectOutputStream.writeObject(pirateStatus);
                objectOutputStream.flush();
                objectOutputStream.reset();

            } catch (IOException e) {
                System.out.println("Deducut status not sent!");
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

    public void incrementScore(int score) {
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

    public boolean getIsPlayerAlive() {
        return this.isPlayerAlive;
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

    @Override
    public String toString() {
        return "Name: " + this.playerName + " Score " + this.currentScore;
    }

}
