package step_definitions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import game_server.GameServer;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pirates_logic.GameLogic;
import player.Player;

public class StepDefinitionsSinglePlayer {

    private Map<String, FortuneCard> fortuneCardMap = Map.ofEntries(entry("Coin", new GoldCard()),
            entry("Chest", new Chest()), entry("Captain", new Captain()), entry("Diamond", new DiamondCard()),
            entry("Monkey Business", new MonkeyBusiness()), entry("Sea Battle One", new SeaBattleTypeOne()),
            entry("Sea Battle Two", new SeaBattleTypeTwo()), entry("Sea Battle Three", new SeaBattleTypeThree()),
            entry("Skull One", new SkullTypeOne()), entry("Skull Two", new SkullTypeTwo()),
            entry("Sorceress", new Sorceress()));

    private FortuneCard card = null;
    private GameServer server = new GameServer(123456789);
    private GameLogic gameLogic = new GameLogic();
    private Player player_1 = new Player("Cumcumber 1");

    private ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(player_1));

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("player {int} Fortune Card as {string}")
    public void player_fortune_card_as(Integer playerIndex, String cardString) {
        String cardFromServer = server.getDeck().get(0).getClass().getSimpleName();
        server.getDeck().remove(0);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                + " was granted Fortune Card -> " + cardFromServer);
        card = fortuneCardMap.get(cardString);
        players.get(playerIndex - 1).setFortuneCard(card);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                + " Fortune Card was set to -> " + cardString);
    }

    @When("player {int} rolls {int} {string}")
    public void player_rolls(Integer playerIndex, Integer int1, String string) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out.println(this.scenario.getName() + " Player roll after simulation -> "
                + players.get(playerIndex - 1).getRoll().toString());
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " Player roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} rolls {int} {string} and {int} {string}")
    public void player_rolls_and(Integer playerIndex, Integer int1, String string, Integer int2, String string2) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out.println(this.scenario.getName() + " Player roll after simulation -> "
                + players.get(playerIndex - 1).getRoll().toString());
        addSkullBasedOnSkullCard(tempRoll);
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " Player roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    private void addSkullBasedOnSkullCard(ArrayList<String> tempRoll) {
        if (card instanceof SkullTypeOne) {
            tempRoll.add("Skull");
        }
        if (card instanceof SkullTypeTwo) {
            tempRoll.add("Skull");
            tempRoll.add("Skull");
        }
    }

    @When("player {int} rolls {int} {string}, {int} {string} and {int} {string}")
    public void player_rolls_and(Integer playerIndex, Integer int1, String string, Integer int2, String string2,
            Integer int3,
            String string3) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        for (int i = 0; i < int3; i++) {
            tempRoll.add(string3);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out.println(this.scenario.getName() + " Player roll after simulation -> "
                + players.get(playerIndex - 1).getRoll().toString());
        addSkullBasedOnSkullCard(tempRoll);
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " Player roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} rolls {int} {string}, {int} {string}, {int} {string} and {int} {string}")
    public void player_rolls_and(Integer playerIndex, Integer int1, String string, Integer int2, String string2,
            Integer int3,
            String string3, Integer int4, String string4) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        for (int i = 0; i < int3; i++) {
            tempRoll.add(string3);
        }
        for (int i = 0; i < int4; i++) {
            tempRoll.add(string4);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out.println(this.scenario.getName() + " Player roll after simulation -> "
                + players.get(playerIndex - 1).getRoll().toString());
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " Player roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} rolls {int} {string}, {int} {string}, {int} {string}, {int} {string} and {int} {string}")
    public void player_rolls_and(Integer playerIndex, Integer int1, String string, Integer int2, String string2,
            Integer int3,
            String string3, Integer int4, String string4, Integer int5, String string5) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        for (int i = 0; i < int3; i++) {
            tempRoll.add(string3);
        }
        for (int i = 0; i < int4; i++) {
            tempRoll.add(string4);
        }
        for (int i = 0; i < int5; i++) {
            tempRoll.add(string5);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out.println(this.scenario.getName() + " Player roll after simulation -> "
                + players.get(playerIndex - 1).getRoll().toString());
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " Player roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @Then("player {int} gets {int} {string}")
    public void player_gets(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                int1--;
            }
        }
        System.out
                .println(this.scenario.getName() + " Player roll after reroll is set to -> "
                        + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} reroll {int} {string}")
    public void player_reroll(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string)) {
                gameLogic.rollDiePair(i + 1, i + 1, players.get(playerIndex - 1).getRoll());
                System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                        + " reroll die at position " + (i + 1));
                players.get(playerIndex - 1).setRollAtIndex(i, "-");
            }
        }
    }

    @Then("player {int} gets {int} {string} after reroll")
    public void player_gets_after_reroll(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                int1--;
            }
        }
        System.out
                .println(this.scenario.getName() + " Player roll after reroll is set to -> "
                        + players.get(playerIndex - 1).getRoll().toString());
    }

    @Then("player {int} gets {int} {string} and {int} {string} after reroll")
    public void player_gets_and_after_reroll(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                int1--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int2 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string2);
                int2--;
            }
        }
        System.out
                .println(this.scenario.getName() + " Player roll after reroll is set to -> "
                        + players.get(playerIndex - 1).getRoll().toString());
    }

    @Then("player {int} gets {int} {string}, {int} {string} and {int} {string} after reroll")
    public void player_gets_and_after_reroll(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2, Integer int3, String string3) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                int1--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int2 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string2);
                int2--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int3 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string3);
                int3--;
            }
        }
        System.out
                .println(this.scenario.getName() + " Player roll after reroll is set to -> "
                        + players.get(playerIndex - 1).getRoll().toString());
    }

    @Then("player {int} scores {int} after Death")
    public void player_scores_after_death(Integer playerIndex, Integer int1) {
        assertEquals(int1, gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard()));
        boolean isPlayerDead = players.get(playerIndex - 1).isPlayerDead();
        System.out.println(this.scenario.getName() + " Player Died -> " + Boolean.toString(isPlayerDead));
        assertTrue(isPlayerDead);
    }

    @Then("player {int} scores {int}")
    public void player_scores(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + " Player earned " + Integer.toString(score));
        players.get(playerIndex - 1).incrementScore(score);
        System.out.println(this.scenario.getName() + " Player new score = " + players.get(playerIndex - 1).getScore());
        assertEquals(int1, score);
    }

    @Then("player {int} will cause deductions of {int}")
    public void player_will_cause_deductions_of(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreIslandOfTheDeadDeduction(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        players.get(playerIndex - 1).incrementScore(score);
        assertEquals(int1, score);
    }

    @When("Game declares {string} as winner")
    public void game_declares_as_winner(String string) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Player p : players) {
            map.put(p.getName(), p.getScore());
        }
        String winner = gameLogic.determineWinner(map);
        System.out.println(this.scenario.getName() + " The winner in the end is -> " + winner);
        assertEquals(string, gameLogic.determineWinner(map));
    }

    /**
     * Fortune Card Activation Section
     */

    /**
     * Sorceress Card
     */
    @Then("player {int} activates Sorceress getting a {string}")
    public void player_activates_sorceress_getting_a(Integer playerIndex, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals("Skull")) {
                players.get(playerIndex - 1).sorceressActivation();
                System.out.println(this.scenario.getName() + " Player activated Sorceress card");
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                break;
            }
        }
    }

    /**
     * Chest Card
     */
    @When("player {int} puts {int} {string} and {int} {string} in chest")
    public void player_puts_and_in_chest(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2) {

        for (int i = players.get(playerIndex - 1).getRoll().size() - 1; i > 0; i--) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string)) {
                players.get(playerIndex - 1).addItemAtIndexToChest(i + 1);
                int1--;
            }
            if (int1 == 0) {
                break;
            }
        }

        for (int i = players.get(playerIndex - 1).getRoll().size() - 1; i > 0; i--) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string2)) {
                players.get(playerIndex - 1).addItemAtIndexToChest(i + 1);
                int2--;
            }
            if (int2 == 0) {
                break;
            }
        }
        System.out.println(this.scenario.getName() + " Player chess contents is now -> "
                + ((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().toString());
    }

    @When("player {int} puts {int} {string} in chest")
    public void player_puts_in_chest(Integer playerIndex, Integer int1, String string) {
        for (int i = players.get(playerIndex - 1).getRoll().size() - 1; i >= 0; i--) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string)) {
                players.get(playerIndex - 1).addItemAtIndexToChest(i + 1);
                int1--;
            }
            if (int1 == 0) {
                break;
            }
        }

        System.out.println(this.scenario.getName() + " Player chess contents is now -> "
                + ((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().toString());
    }

    @When("player {int} takes out {int} {string} and {int} {string} in chest")
    public void player_takes_out_and_in_chest(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2) {
        for (int i = ((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().size()
                - 1; i >= 0; i--) {
            if (((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().get(i).equals(string)) {
                players.get(playerIndex - 1).getRoll()
                        .add(((Chest) players.get(playerIndex - 1).getFortuneCard()).takeOut(i));
                int1--;
            }
            if (int1 == 0) {
                break;
            }
        }

        for (int i = ((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().size()
                - 1; i >= 0; i--) {
            if (((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().get(i).equals(string)) {
                players.get(playerIndex - 1).getRoll()
                        .add(((Chest) players.get(playerIndex - 1).getFortuneCard()).takeOut(i));
                int2--;
            }
            if (int2 == 0) {
                break;
            }
        }
        System.out.println(this.scenario.getName() + " Player chess contents is now -> "
                + ((Chest) players.get(playerIndex - 1).getFortuneCard()).getChestContent().toString());
    }

    /**
     * Sea Battle Fortune Card
     */
    @When("player {int} scores an additional {int} losing at sea")
    public void player_scores_an_additional_losing_at_sea(Integer playerIndex, Integer seaBattleScoreDeductions) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + " Player lost at sea a total of -> " + score);

        assertEquals(seaBattleScoreDeductions, score);
    }

    @Then("player {int} scores {int} after winning at sea")
    public void player_scores_after_winning_at_sea(Integer playerIndex, Integer seaBattleWinScore) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + " Player won at sea a total of -> " + score);

        assertEquals(seaBattleWinScore, score);
    }
}
