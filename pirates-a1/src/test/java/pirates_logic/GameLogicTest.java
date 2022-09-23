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
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenGoinCoinOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTOneHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}

	@Test
	public void givenTwoGoldOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenAGoldAndDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenThreeOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);

		dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);

		dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL));
		assertEquals(gameLogic.scoreTurn(dice, captain), 0);
	}

	@Test
	public void givenFourOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);

		dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
				DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL));
		assertEquals(gameLogic.scoreTurn(dice, captain), 0);
	}

	@Test
	public void givenFiveOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 1000);
	}

	@Test
	public void givenSixOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SWORD, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 2000);
	}
}
