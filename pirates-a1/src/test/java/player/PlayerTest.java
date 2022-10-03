package player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import pirates_logic.Sorceress;

public class PlayerTest {

    private ArrayList<String> dieRolled = new ArrayList<>(Arrays.asList(DieSides.NONE, DieSides.NONE, DieSides.NONE,
            DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE, DieSides.NONE));

    private Player player = new Player("PLAYER_TEST_CLASS", 1234567890);

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

        assertEquals(true, player.activateSorceress());
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

        assertEquals(false, player.activateSorceress());
    }
}
