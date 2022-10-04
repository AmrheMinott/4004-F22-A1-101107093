package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.DiamondCard;
import fortune_cards.GoldCard;
import fortune_cards.MonkeyBusiness;
import fortune_cards.SeaBattleTypeOne;
import fortune_cards.Sorceress;
import player.Player;

public class PiratesCustomerAcceptanceTests {

    private Player player = new Player("TEST", 1234567890);
    private GameLogic gameLogic = new GameLogic();
    private ArrayList<String> dieRolled = new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
            DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE));

    private GoldCard coin = new GoldCard();
    private DiamondCard diamond = new DiamondCard();
    private Captain captain = new Captain();
    private MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

    private Sorceress sorceress = new Sorceress();
    private SeaBattleTypeOne seaBattleTypeOne = new SeaBattleTypeOne();

    @Test
    public void row45() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY)));

        assertEquals(0, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row46() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        player.setRollAtIndex(5, DieSides.SKULL);
        player.setRollAtIndex(6, DieSides.SKULL);
        player.setRollAtIndex(7, DieSides.SWORD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL, DieSides.SWORD));

        assertEquals(0, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row47() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD)));

        player.setRollAtIndex(6, DieSides.SKULL);
        player.setRollAtIndex(7, DieSides.SWORD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SWORD));

        assertEquals(0, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row49() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        gameLogic.rollDiePair(5, 6, dieRolled);
        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRollAtIndex(4, DieSides.PARROT);
        player.setRollAtIndex(5, DieSides.SKULL);
        player.setRollAtIndex(6, DieSides.MONKEY);
        player.setRollAtIndex(7, DieSides.MONKEY);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY));

        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRollAtIndex(6, DieSides.SKULL);
        player.setRollAtIndex(7, DieSides.MONKEY);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY));

        assertEquals(0, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row51() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.PARROT,
                DieSides.PARROT, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRollAtIndex(1, DieSides.GOLD);
        player.setRollAtIndex(2, DieSides.GOLD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));

        gameLogic.rollDiePair(5, 6, dieRolled);
        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRollAtIndex(4, DieSides.GOLD);
        player.setRollAtIndex(5, DieSides.GOLD);
        player.setRollAtIndex(6, DieSides.GOLD);
        player.setRollAtIndex(7, DieSides.GOLD);

        assertEquals(player.getRoll(), Arrays.asList(DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD));

        assertEquals(4800, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
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

        player.setRollAtIndex(2, DieSides.MONKEY);
        player.setRollAtIndex(3, DieSides.PARROT);

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

    @Test
    public void row55() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL)));
        assertEquals(player.getRoll(), Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL));

        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL)));

        assertEquals(300, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row56() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.DIAMOND, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL)));
        assertEquals(player.getRoll(), Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.DIAMOND, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL));

        assertEquals(500, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row57() {
        player.setFortuneCard(diamond);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SKULL)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SKULL),
                player.getRoll());

        assertEquals(700, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row58() {
        player.setFortuneCard(diamond);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL),
                player.getRoll());

        assertEquals(400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row59() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(1, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(5, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD),
                player.getRoll());

        assertEquals(800, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row60() {
        player.setFortuneCard(captain);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(1, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(5, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD),
                player.getRoll());

        assertEquals(1200, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row61() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.MONKEY,
                DieSides.PARROT, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(1, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT),
                player.getRoll());

        assertEquals(600, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row62() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT),
                player.getRoll());

        assertEquals(1100, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row63() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.PARROT, DieSides.PARROT, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.PARROT, DieSides.PARROT, DieSides.PARROT),
                player.getRoll());

        assertEquals(2100, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row64() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(5400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row65() {
        player.setFortuneCard(diamond);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(5400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row66() {
        player.setFortuneCard(captain);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.SWORD,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.SWORD,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD),
                player.getRoll());

        assertEquals(9000, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row67() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(5, 7, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY),
                player.getRoll());

        assertEquals(4600, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row68() {
        player.setFortuneCard(diamond);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(5, 6, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        assertEquals(400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row69() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.DIAMOND, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD,
                DieSides.PARROT, DieSides.DIAMOND, DieSides.SKULL, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        assertEquals(500, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row70() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.SWORD,
                DieSides.PARROT, DieSides.GOLD, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.SWORD,
                DieSides.PARROT, DieSides.GOLD, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        assertEquals(600, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row71() {
        player.setFortuneCard(diamond);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.SWORD,
                DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(1, 4, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.SWORD,
                DieSides.PARROT, DieSides.GOLD, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.SWORD,
                DieSides.PARROT, DieSides.GOLD, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        assertEquals(500, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row72() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT),
                player.getRoll());

        gameLogic.rollDiePair(7, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.GOLD, DieSides.MONKEY, DieSides.SKULL, DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(800, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row77() {
        player.setFortuneCard(sorceress);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.SKULL, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        player.activateSorceress();

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());
    }

    @Test
    public void row82() {
        player.setFortuneCard(monkeyBusiness);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.SKULL, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD),
                player.getRoll());

        assertEquals(1100, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row83() {
        player.setFortuneCard(monkeyBusiness);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(4, 6, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        assertEquals(400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row84() {
        player.setFortuneCard(monkeyBusiness);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 3, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.GOLD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(4, 6, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SWORD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.SWORD, DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(5, 7, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.SWORD,
                DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.SWORD),
                player.getRoll());

        gameLogic.rollDiePair(2, 8, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.SWORD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT, DieSides.SWORD),
                player.getRoll());

        assertEquals(2000, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row98() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.DIAMOND)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.DIAMOND),
                player.getRoll());

        assertEquals(400, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row99() {
        player.setFortuneCard(captain);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(1800, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row100() {
        player.setFortuneCard(coin);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.DIAMOND)));
        assertEquals(Arrays.asList(DieSides.SWORD, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.DIAMOND),
                player.getRoll());

        assertEquals(1000, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row103() {
        player.setFortuneCard(seaBattleTypeOne);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD),
                player.getRoll());

        gameLogic.rollDiePair(6, 7, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD),
                player.getRoll());

        assertEquals(1200, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row104() {
        player.setFortuneCard(monkeyBusiness);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT, DieSides.PARROT, DieSides.GOLD),
                player.getRoll());

        gameLogic.rollDiePair(1, 2, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD),
                player.getRoll());

        gameLogic.rollDiePair(5, 6, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.GOLD, DieSides.SWORD, DieSides.GOLD),
                player.getRoll());

        gameLogic.rollDiePair(5, 7, dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.GOLD, DieSides.DIAMOND, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.DIAMOND, DieSides.DIAMOND,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT, DieSides.GOLD, DieSides.DIAMOND, DieSides.GOLD),
                player.getRoll());

        assertEquals(1200, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }

    @Test
    public void row114() {
        player.setFortuneCard(seaBattleTypeOne);
        player.setRoll(dieRolled);
        gameLogic.rollAllEightDie(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL)));
        assertEquals(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY,
                DieSides.MONKEY, DieSides.MONKEY, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL),
                player.getRoll());

        assertEquals(-300, gameLogic.scoreTurn(player.getRoll(), player.getFortuneCard()));
    }
}
