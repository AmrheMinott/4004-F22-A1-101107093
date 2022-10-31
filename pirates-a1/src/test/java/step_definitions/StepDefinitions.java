package step_definitions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pirates_logic.GameLogic;

public class StepDefinitions {

    private Map<String, FortuneCard> fortuneCardMap = Map.ofEntries(entry("Coin", new GoldCard()),
            entry("Chest", new Chest()), entry("Captain", new Captain()), entry("Diamond", new DiamondCard()),
            entry("Monkey Business", new MonkeyBusiness()), entry("Sea Battle One", new SeaBattleTypeOne()),
            entry("Sea Battle Two", new SeaBattleTypeTwo()), entry("Sea Battle Three", new SeaBattleTypeThree()),
            entry("Skull One", new SkullTypeOne()), entry("Skull Two", new SkullTypeTwo()));

    private FortuneCard card = null;
    private GameLogic gameLogic = new GameLogic();
    private ArrayList<String> roll = new ArrayList<String>();

    @Given("Fortune Card as {string}")
    public void fortune_card_as(String cardString) {
        card = fortuneCardMap.get(cardString);
    }

    @When("player rolls {int} {string} and {int} {string}")
    public void player_rolls_and(Integer int1, String string, Integer int2, String string2) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        roll = tempRoll;
    }

    @When("player rolls {int} {string}, {int} {string} and {int} {string}")
    public void player_rolls_and(Integer int1, String string, Integer int2, String string2, Integer int3,
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
        roll = tempRoll;
    }

    @Then("player gets {int} {string}")
    public void player_gets(Integer int1, String string) {
        for (int i = 0; i < roll.size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (roll.get(i) == "-") {
                roll.set(i, string);
                int1--;
            }
        }
    }

    @When("player reroll {int} {string}")
    public void player_reroll(Integer int1, String string) {
        for (int i = 0; i < roll.size(); i++) {
            if (roll.get(i).equals(string)) {
                roll.set(i, "-");
            }
        }
    }

    @Then("player gets {int} {string} and {int} {string} after reroll")
    public void player_gets_and_after_reroll(Integer int1, String string, Integer int2, String string2) {
        for (int i = 0; i < roll.size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (roll.get(i) == "-") {
                roll.set(i, string);
                int1--;
            }
        }

        for (int i = 0; i < roll.size(); i++) {
            if (int2 == 0) {
                break;
            }
            if (roll.get(i) == "-") {
                roll.set(i, string2);
                int2--;
            }
        }
    }

    @Then("player scores {int} after Death")
    public void player_scores_after_death(Integer int1) {
        assertEquals(int1, gameLogic.scoreTurn(roll, card));
    }
}
