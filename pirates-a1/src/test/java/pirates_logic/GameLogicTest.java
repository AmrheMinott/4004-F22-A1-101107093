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

	@Test
	public void givenDiamondOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTOneHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}

	@Test
	public void givenTwoDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}
	
	
	@Test
	public void givenGoinCoinOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTOneHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 100);
	}
	
	@Test
	public void givenTwoGoldOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}
	
	@Test
	public void givenAGoldAndDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}
}
