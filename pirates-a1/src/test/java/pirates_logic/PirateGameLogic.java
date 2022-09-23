package pirates_logic;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class PirateGameLogic {

	private GameLogic gameLogic = new GameLogic();

	@Test
	public void givenThreeSkulls_assertScoreIsZero() {
		Captain captian = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList("Skull", "Skull", "Skull", "Monkey", "Diamond", "Monkey", "Diamond", "Monkey"));
		
		assertThat(gameLogic.scoreTurn(dice, captian), 0);
	}
}
