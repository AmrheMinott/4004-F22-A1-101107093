package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Captain;

public class GameLogicTest {

	private GameLogic gameLogic = new GameLogic();

	@Test
	public void givenThreeSkulls_assertScoreIsZero() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
				DieSides.MONKEY, DieSides.DIAMOND, DieSides.MONKEY, DieSides.DIAMOND, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 0);
	}
}
