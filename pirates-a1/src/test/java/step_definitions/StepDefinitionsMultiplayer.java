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
import game_server.GameServer;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pirates_logic.GameLogic;
import player.Player;

public class StepDefinitionsMultiplayer {

    private Map<String, FortuneCard> fortuneCardMap = Map.ofEntries(entry("Coin", new GoldCard()),
            entry("Chest", new Chest()), entry("Captain", new Captain()), entry("Diamond", new DiamondCard()),
            entry("Monkey Business", new MonkeyBusiness()), entry("Sea Battle One", new SeaBattleTypeOne()),
            entry("Sea Battle Two", new SeaBattleTypeTwo()), entry("Sea Battle Three", new SeaBattleTypeThree()),
            entry("Skull One", new SkullTypeOne()), entry("Skull Two", new SkullTypeTwo()),
            entry("Sorceress", new Sorceress()));

    private FortuneCard card = null;
    private GameServer server = new GameServer(1234567);
    private GameLogic gameLogic = null;

    private ArrayList<Player> players = new ArrayList<Player>();

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("player {int} is initialised MP")
    public void player_is_initialised_mp(Integer playerIndex) throws ClassNotFoundException {
        players.add(new Player("Cumcumber " + playerIndex));
    }

    @Given("Game Logic is initialised MP")
    public void game_logic_is_initialised_mp() {
        gameLogic = new GameLogic();
    }

    @When("player {int} draws Fortune Card as {string} MP")
    public void player_draws_fortune_card_as_mp(Integer playerIndex, String cardString) {
        String cardFromServer = server.getDeck().get(0).getClass().getSimpleName();
        server.getDeck().remove(0);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                + " was granted Fortune Card -> " + cardFromServer);
        card = fortuneCardMap.get(cardString);
        players.get(playerIndex - 1).setFortuneCard(card);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                + " Fortune Card was set to -> " + cardString);
    }

    @When("player {int} rolls {int} {string} MP")
    public void player_rolls_mp(Integer playerIndex, Integer int1, String string) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }

        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out
                .println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                        + " roll after simulation -> "
                        + players.get(playerIndex - 1).getRoll().toString());
        addSkullBasedOnSkullCard(tempRoll);
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} rolls {int} {string} and {int} {string} MP")
    public void player_rolls_and_mp(Integer playerIndex, Integer int1, String string, Integer int2, String string2) {
        ArrayList<String> tempRoll = new ArrayList<String>();
        for (int i = 0; i < int1; i++) {
            tempRoll.add(string);
        }
        for (int i = 0; i < int2; i++) {
            tempRoll.add(string2);
        }
        gameLogic.rollAllEightDie(players.get(playerIndex - 1).getRoll());
        System.out
                .println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                        + " roll after simulation -> "
                        + players.get(playerIndex - 1).getRoll().toString());
        addSkullBasedOnSkullCard(tempRoll);
        players.get(playerIndex - 1).setRoll(tempRoll);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " roll set to -> "
                + players.get(playerIndex - 1).getRoll().toString());
    }

    @Then("player {int} ends turn MP")
    public void player_ends_turn_mp(Integer playerIndex) {
        System.out.println(
                this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " has ended their turn!");
    }

    @Then("player {int} is {string} MP")
    public void player_is_mp(Integer playerIndex, String string) {
        System.out.println(
                this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " is currently " + string);
    }

    @Then("player {int} loses score due to player {int} deductions score now {int}")
    public void player_loses_score_due_to_player_deductions_score_now(Integer playerIndex1, Integer playerIndex2,
            Integer score) {
        int score_deductions = gameLogic.scoreIslandOfTheDeadDeduction(players.get(playerIndex2 - 1).getRoll(),
                players.get(playerIndex2 - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + players.get(playerIndex1 - 1).getName() + " deductions -> "
                + score_deductions);
        players.get(playerIndex1 - 1).incrementScore(score_deductions);
        System.out.println(this.scenario.getName() + players.get(playerIndex1 - 1).getName() + " new score = "
                + players.get(playerIndex1 - 1).getScore());
        assertEquals(players.get(playerIndex1 - 1).getScore(), score);
    }

    @When("player {int} scores {int} MP")
    public void player_scores_mp(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " earned "
                + Integer.toString(score));

        players.get(playerIndex - 1).incrementScore(score);
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " new score = "
                + players.get(playerIndex - 1).getScore());

        assertEquals(int1, score);
    }

    @Then("player {int} reroll {int} {string} MP")
    public void player_reroll_mp(Integer playerIndex, Integer int2, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (players.get(playerIndex - 1).getRoll().get(i).equals(string)) {
                gameLogic.rollDiePair(i + 1, i + 1, players.get(playerIndex - 1).getRoll());
                System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                        + "reroll " + (i + 1));
                players.get(playerIndex - 1).setRollAtIndex(i, "-");
            }
        }
    }

    @Then("player {int} gets {int} {string} after reroll MP")
    public void player_gets_after_reroll_mp(Integer playerIndex, Integer int1, String string) {
        for (int i = 0; i < players.get(playerIndex - 1).getRoll().size(); i++) {
            if (int1 == 0) {
                break;
            }
            if (players.get(playerIndex - 1).getRoll().get(i).equals("-")) {
                players.get(playerIndex - 1).setRollAtIndex(i, string);
                int1--;
            }
        }
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName()
                + " roll after reroll is set to -> " + players.get(playerIndex - 1).getRoll().toString());
    }

    @When("player {int} scores {int} after Death MP")
    public void player_scores_after_death_mp(Integer playerIndex, Integer int1) {
        int score = gameLogic.scoreTurn(players.get(playerIndex - 1).getRoll(),
                players.get(playerIndex - 1).getFortuneCard());
        System.out.println(this.scenario.getName() + players.get(playerIndex - 1).getName() + " earned "
                + Integer.toString(score));
        players.get(playerIndex - 1).incrementScore(score);
        System.out.println(this.scenario.getName() + players.get(playerIndex - 1).getName() + " new score = "
                + players.get(playerIndex - 1).getScore());
        assertEquals(int1, score);
    }

    @Then("player {int} is dead MP")
    public void player_is_dead_mp(Integer playerIndex) {
        boolean isPlayerDead = players.get(playerIndex - 1).isPlayerDead();
        System.out.println(this.scenario.getName() + " " + players.get(playerIndex - 1).getName() + " Died -> "
                + Boolean.toString(isPlayerDead));
        assertTrue(isPlayerDead);
    }

    @Then("Game declares {string} as winner MP")
    public void game_declares_as_winner_mp(String string) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Player p : players) {
            map.put(p.getName(), p.getScore());
        }
        String winner = gameLogic.determineWinner(map);
        System.out.println(this.scenario.getName() + " The winner in the end is -> " + winner);
        assertEquals(string, gameLogic.determineWinner(map));
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

}
