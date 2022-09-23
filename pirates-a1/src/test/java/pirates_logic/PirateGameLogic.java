package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fortune_cards.Captain;

public class PirateGameLogic {

	private GameLogic gameLogic = new GameLogic();

	@Test
	public void givenThreeSkulls_assertScoreIsZero() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(
				Arrays.asList("Skull", "Skull", "Skull", "Monkey", "Diamond", "Monkey", "Diamond", "Monkey"));
		assertEquals(gameLogic.scoreTurn(dice, captain), 0);
	}
}
