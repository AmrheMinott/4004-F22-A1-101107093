package pirates_logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.Chest;
import fortune_cards.DiamondCard;
import fortune_cards.GoldCard;
import fortune_cards.MonkeyBusiness;
import fortune_cards.SeaBattleTypeOne;
import fortune_cards.SeaBattleTypeThree;
import fortune_cards.SeaBattleTypeTwo;
import fortune_cards.SkullTypeOne;
import fortune_cards.SkullTypeTwo;

public class GameLogicTest {

    private GameLogic gameLogic = new GameLogic();
    private int DIAMOND_SCORE = 100;
    private int GOLD_SCORE = 100;
    private int THREE_OF_A_KIND = 100;
    private int FOUR_OF_A_KIND = 200;
    private int FIVE_OF_A_KIND = 500;
    private int SIX_OF_A_KIND = 1000;
    private int SEVEN_OF_A_KIND = 2000;
    private int EIGHT_OF_A_KIND = 4000;

    private int BONUS = 500;

    /**
     * Captain Card testing
     */
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
        assertEquals(gameLogic.scoreTurn(dice, captain), (DIAMOND_SCORE * 2));
    }

    @Test
    public void givenTwoDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), ((DIAMOND_SCORE + DIAMOND_SCORE) * 2));
    }

    @Test
    public void givenGoldCoinOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsTwoHundred() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), (GOLD_SCORE * 2));
    }

    @Test
    public void givenTwoGoldOnly_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), ((GOLD_SCORE + GOLD_SCORE) * 2));
    }

    @Test
    public void givenTwoGoldCoins_andCaptainCard_assertScoreIsEightHundred_withBonus() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.PARROT, DieSides.PARROT, DieSides.PARROT,
                DieSides.SWORD, DieSides.GOLD, DieSides.GOLD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain),
                (GOLD_SCORE + GOLD_SCORE + THREE_OF_A_KIND + THREE_OF_A_KIND + BONUS) * 2);
    }

    @Test
    public void givenAGoldAndDiamond_andNoScoringOfAKinds_andCaptainCard_assertScoreIsFourHundred() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.GOLD, DieSides.DIAMOND, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), (GOLD_SCORE + GOLD_SCORE) * 2);
    }

    @Test
    public void givenThreeGoldAndThreeDiamond_andCaptainCard_assertScoreIsSixteenHundred() {
        Captain captain = new Captain();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.DIAMOND, DieSides.DIAMOND, DieSides.DIAMOND));
        assertEquals(gameLogic.scoreTurn(dice, captain),
                ((DIAMOND_SCORE * 3) + (GOLD_SCORE * 3) + (THREE_OF_A_KIND * 2)) * 2);
    }

    @Test
    public void givenThreeOfAKind_andCaptainCard() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), (THREE_OF_A_KIND * 2));

        dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY));
        assertEquals(gameLogic.scoreTurn(dice, captain), (THREE_OF_A_KIND * 2 * 2));

        dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SKULL));
        assertEquals(gameLogic.scoreTurn(dice, captain), 0);
    }

    @Test
    public void givenFourOfAKind_andCaptainCard() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.MONKEY, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain), (FOUR_OF_A_KIND * 2));

        dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.SKULL));
        assertEquals(gameLogic.scoreTurn(dice, captain), 0);
    }

    @Test
    public void givenFiveOfAKind_andCaptainCard() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain), (FIVE_OF_A_KIND * 2));
    }

    @Test
    public void givenSixOfAKind_andCaptainCard() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SWORD, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain), (SIX_OF_A_KIND * 2));
    }

    @Test
    public void givenSevenOfAKind_andCaptainCard() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.SWORD, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain), (SEVEN_OF_A_KIND * 2));
    }

    @Test
    public void givenEightOfAKind_andCaptainCard_withBonus() {
        Captain captain = new Captain();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SWORD, DieSides.SWORD, DieSides.SWORD,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, captain), ((EIGHT_OF_A_KIND + BONUS) * 2));
    }

    /*
     * Monkey Business Card
     */
    @Test
    public void givenThreeOfAKind_andMonkeyBusinessCard() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.DIAMOND, DieSides.SWORD, DieSides.PARROT,
                DieSides.SKULL, DieSides.SKULL, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), THREE_OF_A_KIND + DIAMOND_SCORE);
    }

    @Test
    public void givenFourOfAKind_andMonkeyBusinessCard() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT,
                DieSides.SKULL, DieSides.SKULL, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), FOUR_OF_A_KIND);
    }

    @Test
    public void givenFiveOfAKind_andMonkeyBusinessCard() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SWORD, DieSides.PARROT,
                DieSides.SKULL, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), FIVE_OF_A_KIND);
    }

    @Test
    public void givenSixOfAKind_andMonkeyBusinessCard() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
                DieSides.SKULL, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), SIX_OF_A_KIND);
    }

    @Test
    public void givenSevenOfAKind_andMonkeyBusinessCard() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), SEVEN_OF_A_KIND);
    }

    @Test
    public void givenEightOfAKind_andMonkeyBusinessCard_withBonus() {
        MonkeyBusiness monkeyBusiness = new MonkeyBusiness();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
                DieSides.PARROT, DieSides.PARROT, DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, monkeyBusiness), EIGHT_OF_A_KIND + BONUS);
    }

    /**
     * Sea Battle Testing
     */
    @Test
    public void givenFourSwordsOfAKind_andSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), seaBattle.getAdditionalPoints() + FOUR_OF_A_KIND);
    }

    @Test
    public void givenPlayerLostAtSea_withTwo3OfAKind_andSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SKULL, DieSides.PARROT, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), -seaBattle.getAdditionalPoints());
    }

    @Test
    public void givenPlayerWonAtSea_withTwo3OfAKind_andSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), seaBattle.getAdditionalPoints() + THREE_OF_A_KIND + THREE_OF_A_KIND);
    }

    @Test
    public void givenPlayerWonAtSea_andSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.GOLD, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), (GOLD_SCORE + THREE_OF_A_KIND + seaBattle.getAdditionalPoints()));
    }

    @Test
    public void givenPlayerWonAtSea_andTwoSkulls_withSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SKULL, DieSides.MONKEY, DieSides.SKULL));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), (FOUR_OF_A_KIND + seaBattle.getAdditionalPoints()));
    }

    @Test
    public void givenPlayerWonAtSea_andSeaBattleCardTypeOne_andBonus() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY, DieSides.GOLD, DieSides.GOLD));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), (FOUR_OF_A_KIND + (GOLD_SCORE * 2) + BONUS + seaBattle.getAdditionalPoints()));
    }

    @Test
    public void givenPlayerWon_andSeaBattleCardTypeTwo() {
        SeaBattleTypeTwo seaBattle = new SeaBattleTypeTwo();
        int additionalPoints = seaBattle.getAdditionalPoints();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), additionalPoints + THREE_OF_A_KIND);
    }

    @Test
    public void givenPlayerWon_andSeaBattleCardTypeTwo_withBonus() {
        SeaBattleTypeTwo seaBattle = new SeaBattleTypeTwo();
        int additionalPoints = seaBattle.getAdditionalPoints();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.MONKEY, DieSides.SWORD));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), additionalPoints + THREE_OF_A_KIND + FOUR_OF_A_KIND);
    }

    @Test
    public void givenPlayerLostAtSea_andSeaBattleCardTypeThree() {
        SeaBattleTypeThree seaBattle = new SeaBattleTypeThree();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.SWORD, DieSides.PARROT, DieSides.SWORD, DieSides.PARROT));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), -seaBattle.getAdditionalPoints());
    }

    @Test
    public void givenPlayerLostAtSea_andSeaBattleCardTypeOne() {
        SeaBattleTypeOne seaBattle = new SeaBattleTypeOne();

        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.PARROT,
                DieSides.SWORD, DieSides.PARROT, DieSides.PARROT, DieSides.SKULL, DieSides.SKULL));
        assertEquals(gameLogic.scoreTurn(dice, seaBattle), -seaBattle.getAdditionalPoints());
    }

    /**
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
        assertEquals(gameLogic.scoreTurn(dice, chest), THREE_OF_A_KIND);
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
        assertEquals(gameLogic.scoreTurn(dice, chest), FOUR_OF_A_KIND + GOLD_SCORE);
    }

    @Test
    public void assertDieTakenFromChest_isWhatWasPlacedInside() {
        Chest chest = new Chest();
        chest.addDiceToChest(DieSides.SWORD);
        chest.addDiceToChest(DieSides.MONKEY);
        chest.addDiceToChest(DieSides.PARROT);

        String removedDie = chest.takeOut(1);
        assertEquals(removedDie, DieSides.MONKEY);
        assertEquals(chest.getChestContent().size(), 2);
    }

    /**
     * Diamond / Gold coin Card Bonus Test
     */
    @Test
    public void givenDiamondCard_whenCalculatingScore_assertAdditionalDiamondIsPresent_noBonus() {
        DiamondCard diamond = new DiamondCard();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.DIAMOND, DieSides.PARROT));

        assertEquals(gameLogic.scoreTurn(dice, diamond), (THREE_OF_A_KIND * 2) + DIAMOND_SCORE + DIAMOND_SCORE);
    }

    @Test
    public void givenDiamondCard_whenCalculatingScore_assertAdditionalDiamondIsPresent_bonus() {
        DiamondCard diamond = new DiamondCard();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.MONKEY, DieSides.MONKEY,
                DieSides.SWORD, DieSides.SWORD, DieSides.SWORD, DieSides.GOLD, DieSides.SWORD));

        assertEquals(gameLogic.scoreTurn(dice, diamond), (FOUR_OF_A_KIND + GOLD_SCORE + THREE_OF_A_KIND + DIAMOND_SCORE + BONUS));
    }

    @Test
    public void givenAllGold_whenCalculatingScore_assertAdditional_withBonus() {
        GoldCard gold = new GoldCard();
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.GOLD, DieSides.GOLD, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD));

        assertEquals(gameLogic.scoreTurn(dice, gold), (EIGHT_OF_A_KIND + (GOLD_SCORE * 9) + BONUS));
    }

    /**
     * Skull Cards test
     */
	@Test
	public void givenTwoSkullCard_assertViaDeathPlayerGetsZeroScore() {
		SkullTypeTwo skullTypeTwo = new SkullTypeTwo();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.GOLD, DieSides.GOLD,
				DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.SKULL, DieSides.SKULL));

		assertEquals(0, gameLogic.scoreTurn(dice, skullTypeTwo));
	}

	@Test
	public void givenOneSkullCard_assertViaDeathPlayerGetsZeroScore() {
		SkullTypeOne skullTypeOne = new SkullTypeOne();
		ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.GOLD,
				DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.SKULL));

		assertEquals(0, gameLogic.scoreTurn(dice, skullTypeOne));
	}
	
    /**
     * Island of the Dead Calculations
     */
    @Test
    public void givenTwoSkullCard_assertDeductionsOfFourSkulls() {
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.GOLD,
                DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.GOLD, DieSides.SKULL,
                DieSides.SKULL));

        assertEquals(-400, gameLogic.scoreIslandOfTheDeadDeduction(dice));
    }

    @Test
    public void givenTwoSkullCard_assertDeductionsOfMidSkulls() {
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.GOLD, DieSides.GOLD, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.SKULL));

        assertEquals(-800, gameLogic.scoreIslandOfTheDeadDeduction(dice));
    }

    @Test
    public void givenTwoSkullCard_assertDeductionsOfAllSkulls() {
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.SKULL, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL, DieSides.SKULL,
                DieSides.SKULL));

        assertEquals(-1000, gameLogic.scoreIslandOfTheDeadDeduction(dice));
    }
}
