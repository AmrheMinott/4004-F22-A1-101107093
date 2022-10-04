package player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Chest;
import fortune_cards.SeaBattleTypeOne;
import fortune_cards.Sorceress;

public class PlayerTest {

    private ArrayList<String> dieRolled = new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
            DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE));

    private Player player = new Player("PLAYER_TEST_CLASS", 1234567890);

    private Chest chest = new Chest();

    private SeaBattleTypeOne seaBattleTypeOne = new SeaBattleTypeOne();
    private Sorceress sorceress = new Sorceress();

    @Test
    public void givenPlayerRolledSomeSkull_assertThatSorceressWasActivated() {

        player.setFortuneCard(sorceress);
        player.setRoll(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.SKULL, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(true, ((Sorceress) player.getFortuneCard()).activateSorceress(player.getRoll()));
    }

    @Test
    public void givenPlayerRolledNoSkull_assertThatSorceressWasNotActivated() {

        player.setFortuneCard(sorceress);
        player.setRoll(dieRolled);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        assertEquals(false, ((Sorceress) player.getFortuneCard()).activateSorceress(player.getRoll()));
    }

    @Test
    public void givenSeaBattleCard_assertThatTheNumberOfRequiredCardIsCorrect() {

        player.setFortuneCard(seaBattleTypeOne);

        assertEquals(2, ((SeaBattleTypeOne) player.getFortuneCard()).getRequiredNumberOfSwords());
    }

    @Test
    public void givenPlayerLostAtSea_assertScoreIsNotLessThanZero() {

        player.setFortuneCard(seaBattleTypeOne);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD),
                player.getRoll());

        player.setScore(-((SeaBattleTypeOne) player.getFortuneCard()).getAdditionalPoints());
        assertEquals(0, player.getScore());
    }

    @Test
    public void givenPlayerPlaceItems_assertThereIsContent() {

        player.setFortuneCard(chest);

        player.setRoll(new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.GOLD)));
        assertEquals(Arrays.asList(DieSides.PARROT, DieSides.PARROT,
                DieSides.PARROT, DieSides.SWORD, DieSides.SWORD, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.GOLD),
                player.getRoll());

        assertEquals(0, ((Chest) player.getFortuneCard()).getChestContent().size());

        player.addItemAtIndexToChest(8);
        player.addItemAtIndexToChest(7);
        player.addItemAtIndexToChest(6);

        assertEquals(3, ((Chest) player.getFortuneCard()).getChestContent().size());
    }
}
