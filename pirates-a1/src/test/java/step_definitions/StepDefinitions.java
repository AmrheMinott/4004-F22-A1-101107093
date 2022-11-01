package step_definitions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pirates_logic.GameLogic;
import player.Player;

public class StepDefinitions {

    private Map<String, FortuneCard> fortuneCardMap = Map.ofEntries(entry("Coin", new GoldCard()),
            entry("Chest", new Chest()), entry("Captain", new Captain()), entry("Diamond", new DiamondCard()),
            entry("Monkey Business", new MonkeyBusiness()), entry("Sea Battle One", new SeaBattleTypeOne()),
            entry("Sea Battle Two", new SeaBattleTypeTwo()), entry("Sea Battle Three", new SeaBattleTypeThree()),
            entry("Skull One", new SkullTypeOne()), entry("Skull Two", new SkullTypeTwo()),
            entry("Sorceress", new Sorceress()));

    private FortuneCard card = null;
    private GameLogic gameLogic = new GameLogic();
    private Player player = new Player("Cumcumber 1");

    private ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(player));

    @Given("player {int} Fortune Card as {string}")
    public void player_fortune_card_as(Integer playerIndex, String cardString) {
        card = fortuneCardMap.get(cardString);
        players.get(playerIndex - 1).setFortuneCard(card);
    }

    @When("player {int} rolls {int} {string}")
    public void player_rolls(Integer playerIndex, Integer int1, String string) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        players.get(playerIndex - 1).setRoll(tempRoll);
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
        players.get(playerIndex - 1).setRoll(tempRoll);
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
        players.get(playerIndex - 1).setRoll(tempRoll);
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
        players.get(playerIndex - 1).setRoll(tempRoll);
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
        players.get(playerIndex - 1).setRoll(tempRoll);
    }

    @Then("player {int} gets {int} {string}")
    public void player_gets(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string);
                int1--;
            }
        }
    }

    @When("player {int} reroll {int} {string}")
    public void player_reroll(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string)) {
                players.get(playerIndex - 1).getRoll().set(i, "-");
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
                players.get(playerIndex - 1).getRoll().set(i, string);
                int1--;
            }
        }
    }

    @Then("player {int} gets {int} {string} and {int} {string} after reroll")
    public void player_gets_and_after_reroll(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string);
                int1--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int2 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string2);
                int2--;
            }
        }
    }

    @Then("player {int} gets {int} {string}, {int} {string} and {int} {string} after reroll")
    public void player_gets_and_after_reroll(Integer playerIndex, Integer int1, String string, Integer int2,
            String string2, Integer int3, String string3) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string);
                int1--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int2 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string2);
                int2--;
            }
        }

        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int3 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).getRoll().set(i, string3);
                int3--;
            }
        }
    }

    @Then("player {int} scores {int} after Death")
    public void player_scores_after_death(Integer playerIndex, Integer int1) {
        assertEquals(int1, gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard()));
    }

    @Then("player {int} scores {int}")
    public void player_scores(Integer playerIndex, Integer int1) {
        assertEquals(int1, gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard()));
    }

    /**
     * Fortune Card Activation Section
     */
    @Then("player {int} activates Sorceress getting a {string}")
    public void player_activates_sorceress_getting_a(Integer playerIndex, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals("Skull")) {
                players.get(playerIndex - 1).getRoll().set(i, string);
                break;
            }
        }
    }

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
    }

}
