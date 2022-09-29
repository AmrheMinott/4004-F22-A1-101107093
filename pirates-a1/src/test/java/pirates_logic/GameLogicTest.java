package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.Chest;
import fortune_cards.MonkeyBusiness;
import fortune_cards.SeaBattleTypeOne;
import fortune_cards.SeaBattleTypeThree;
import fortune_cards.SeaBattleTypeTwo;

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
	public void givenDiamondOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.DIAMOND, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}

	@Test
	public void givenTwoDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenGoldCoinOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 200);
	}

	@Test
	public void givenTwoGoldOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenTwoGoldCoins_andCaptainCard_assertScoreIsEightHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.PARROT, DieSides.PARROT,
				DieSides.SWORD, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 800);
	}

	@Test
	public void givenAGoldAndDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
				DieSides.MONKEY, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
		assertEquals(gameLogic.scoreTurn(dice, captain), 400);
	}

	@Test
	public void givenThreeGoldAndThreeDiamond_andCaptainCard_assertScoreIsSixteenHundred() {
		Captain captain = new Captain();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.GOLD,
				DieSides.GOLD, DieSides.GOLD, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.DIAMOND));
		assertEquals(gameLogic.scoreTurn(dice, captain), 1600);
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

	@Test
	public void givenSevenOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.SWORD, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 4000);
	}

	@Test
	public void givenEightOfAKind_andCaptainCard() {
		Captain captain = new Captain();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.SWORD, DieSides.SWORD,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
		assertEquals(gameLogic.scoreTurn(dice, captain), 8000);
	}

	@Test
	public void givenThreeOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD, DieSides.PARROT,
				DieSides.SKULL, DieSides.SKULL, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 200);
	}

	@Test
	public void givenFourOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT,
				DieSides.SKULL, DieSides.SKULL, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 200);
	}

	@Test
	public void givenFiveOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT,
				DieSides.SKULL, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 500);
	}

	@Test
	public void givenSixOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
				DieSides.SKULL, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 1000);
	}

	@Test
	public void givenSevenOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
				DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 2000);
	}

	@Test
	public void givenEightOfAKind_andMonkeyBusinessCard() {
		MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
				DieSides.PARROT, DieSides.PARROT, DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), 4000);
	}

	@Test
	public void givenFourSwordsOfAKind_andSeaBattleCardTypeOne() {
		SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();
		int additionalPoints = seaBattle.getAdditionalPoints();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, seaBattle), additionalPoints + 200);
	}

	@Test
	public void givenPlayerLostAtSea_andSeaBattleCardTypeOne() {
		SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, seaBattle), -seaBattle.getAdditionalPoints());
	}

	@Test
	public void givenPlayerWon_andSeaBattleCardTypeTwo() {
		SeaBattleTypeTwo seaBattle = new SeaBattleTypeTwo();
		int additionalPoints = seaBattle.getAdditionalPoints();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, seaBattle), additionalPoints + 100);
	}

	@Test
	public void givenPlayerLostAtSea_andSeaBattleCardTypeThree() {
		SeaBattleTypeThree seaBattle = new SeaBattleTypeThree();

		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
				DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, seaBattle), -seaBattle.getAdditionalPoints());
	}

	/*
	 * Chest Testing
	 */
	@Test
	public void givenPlayerPlaced_andDiedFromSkulls_assertDiceInChestAreValid() {
		Chest chest = new Chest();
		chest.addDiceToChest(DieSides.SWORD);
		chest.addDiceToChest(DieSides.SWORD);
		chest.addDiceToChest(DieSides.SWORD);

		ArrayList<String> dice = new ArrayList<>(
				Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL));
		assertEquals(gameLogic.scoreTurn(dice, chest), 100);
	}

	@Test
	public void givenPlayerPlacedDiceInChest_assertScoreIsStillRecorded() {
		Chest chest = new Chest();
		chest.addDiceToChest(DieSides.SWORD);
		chest.addDiceToChest(DieSides.SWORD);
		chest.addDiceToChest(DieSides.SWORD);
		chest.addDiceToChest(DieSides.SWORD);

		ArrayList<String> dice = new ArrayList<>(
				Arrays.asList(DieSides.MONKEY, DieSides.PARROT, DieSides.GOLD, DieSides.PARROT));
		assertEquals(gameLogic.scoreTurn(dice, chest), 300);
	}
}
