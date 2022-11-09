package step_definitions;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pirates_logic.GameLogic;
import player.Player;

public class StepDefinitionsMultiplayer_1 {

    private Map<String, FortuneCard> fortuneCardMap = Map.ofEntries(entry("Coin", new GoldCard()),
            entry("Chest", new Chest()), entry("Captain", new Captain()), entry("Diamond", new DiamondCard()),
            entry("Monkey Business", new MonkeyBusiness()), entry("Sea Battle One", new SeaBattleTypeOne()),
            entry("Sea Battle Two", new SeaBattleTypeTwo()), entry("Sea Battle Three", new SeaBattleTypeThree()),
            entry("Skull One", new SkullTypeOne()), entry("Skull Two", new SkullTypeTwo()),
            entry("Sorceress", new Sorceress()));

    private FortuneCard card = null;
    private GameLogic gameLogic = new GameLogic();

    private ArrayList<Player> players = new ArrayList<Player>();

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("player {int} is initialised MP1")
    public void player_is_initialised_mp1(Integer playerIndex) throws ClassNotFoundException {
        players.add(new Player("Cumcumber " + playerIndex));
    }

    @Given("Game Logic is initialised MP1")
    public void game_logic_is_initialised_mp1() {
        gameLogic = new GameLogic();
    }

    @When("server tells player {int} to {string} MP1")
    public void server_tells_player_to_mp1(Integer playerIndex, String string) throws ClassNotFoundException {

    }

    @When("player {int} draws Fortune Card as {string} MP1")
    public void player_draws_fortune_card_as_mp1(Integer playerIndex, String cardString) {
        card = fortuneCardMap.get(cardString);
        players.get(playerIndex - 1).setFortuneCard(card);
    }

    @When("player {int} rolls {int} {string} and {int} {string} MP1")
    public void player_rolls_and_mp1(Integer playerIndex, Integer int1, String string, Integer int2, String string2) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        players.get(playerIndex - 1).setRoll(tempRoll);
    }

    @When("player {int} scores {int} MP1")
    public void player_scores_mp1(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        players.get(playerIndex - 1).incrementScore(score);
        assertEquals(int1, score);
    }

    @Then("player {int} ends turn MP1")
    public void player_ends_turn_mp1(Integer playerIndex) {
        System.out.println(
                this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " has ended their turn!");
    }
    
    @Then("player {int} is {string} MP1")
    public void player_is_mp1(Integer playerIndex, String string) {
        System.out.println(players.get(playerIndex - 1).getName() + " is currently " + string);
    }

    @When("player {int} scores {int} after Death MP1")
    public void player_scores_after_death_mp1(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        players.get(playerIndex - 1).incrementScore(score);
        assertEquals(int1, score);
        assertTrue(players.get(playerIndex - 1).isPlayerDead());
    }

    @Then("player {int} is dead MP1")
    public void player_is_dead_mp1(Integer playerIndex) {
        assertTrue(players.get(playerIndex - 1).isPlayerDead());
    }

    @Then("Game declares {string} as winner MP1")
    public void game_declares_as_winner_mp1(String string) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Player p : players) {
            map.put(p.getName(), p.getScore());
        }
        assertEquals(string, gameLogic.determineWinner(map));
    }

}
