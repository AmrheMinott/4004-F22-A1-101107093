package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.GoldCard;
import player.Player;

public class PiratesCustomerAcceptanceTests {

    private Player player = new Player("TEST", 1234567890);
    private GameLogic gameLogic = new GameLogic();
    private ArrayList<String> dieRolled = new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
            DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE));

    private GoldCard coin = new GoldCard();
    private Captain captain = new Captain();

    /**
     * Row 45 of SpreadSheet
     */
    @Test
    public void deathWithThreeSkullsOnFristRoll() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY)));

        assertEquals(gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()), 0);
    }

    @Test
    public void row46() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        player.reRollAtIndex(5, DieSides.SKULL);
        player.reRollAtIndex(6, DieSides.SKULL);
        player.reRollAtIndex(7, DieSides.SWORD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL, DieSides.SWORD));

        assertEquals(gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()), 0);
    }

    @Test
    public void row47() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD)));

        player.reRollAtIndex(6, DieSides.SKULL);
        player.reRollAtIndex(7, DieSides.SWORD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SWORD));

        assertEquals(gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()), 0);
    }

    @Test
    public void row49() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        player.reRollAtIndex(5, DieSides.SKULL);
        player.reRollAtIndex(6, DieSides.MONKEY);
        player.reRollAtIndex(7, DieSides.MONKEY);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY));

        player.reRollAtIndex(6, DieSides.SKULL);
        player.reRollAtIndex(7, DieSides.MONKEY);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY));

        assertEquals(gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()), 0);
    }

    @Test
    public void row51() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        player.reRollAtIndex(1, DieSides.GOLD);
        player.reRollAtIndex(2, DieSides.GOLD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        player.reRollAtIndex(5, DieSides.GOLD);
        player.reRollAtIndex(6, DieSides.GOLD);
        player.reRollAtIndex(7, DieSides.GOLD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD));

        assertEquals(gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()), 4800);
    }

    @Test
    public void row52() {
        player.setFortuneCard(captain);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.GOLD, DieSides.GOLD, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.SWORD)));

        assertEquals(800, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row53() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD)));
        assertEquals(player.getRoll(), Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD));

        gameLogic.rollDiePair(3, 4, dieRolled);

        player.reRollAtIndex(2, DieSides.MONKEY);
        player.reRollAtIndex(3, DieSides.PARROT);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD));

        assertEquals(200, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row54() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL)));
        assertEquals(player.getRoll(), Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL));

        assertEquals(300, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }
}
