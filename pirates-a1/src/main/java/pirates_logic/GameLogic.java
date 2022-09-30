package pirates_logic;

import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.Chest;
import fortune_cards.DiamondCard;
import fortune_cards.FortuneCard;
import fortune_cards.GoldCard;
import fortune_cards.MonkeyBusiness;
import fortune_cards.SeaBattle;

/**
 * 
 * @author Amrhe Minott
 *
 */
public class GameLogic {

	private Map<Integer, Integer> ofAKindScoreMap = Map.ofEntries(entry(3, 100), entry(4, 200), entry(5, 500),
			entry(6, 1000), entry(7, 2000), entry(8, 4000), entry(9, 4000));
	private HashMap<String, Integer> diceSideMap = new HashMap<String, Integer>();
	private final int MAX_NUMBER_OF_SKULLS = 3;
	private final int DIAMOND_AND_GOLD_MULTIPLIER = 100;
	private boolean hasPlayerDied = false;
	private boolean hasWonAtSea = false;

	private ArrayList<String> diceFaces = new ArrayList<>(Arrays.asList(DieSides.MONKEY, DieSides.SKULL, DieSides.GOLD,
			DieSides.SWORD, DieSides.PARROT, DieSides.DIAMOND));

	public int scoreTurn(ArrayList<String> dice, FortuneCard card) {
		resetDiceSideMap();

		if (card instanceof DiamondCard) {
			dice.add(DieSides.DIAMOND);
		}

		if (card instanceof GoldCard) {
			dice.add(DieSides.GOLD);
		}

		populateDiceSideMap(dice);

		if (diceSideMap.get(DieSides.SKULL) >= MAX_NUMBER_OF_SKULLS) {
			if (card instanceof Chest) {
				resetDiceSideMap();
				populateDiceSideMap(((Chest) card).getChestContent());
				((Chest) card).emptyChest();
			} else {
				return 0;
			}
			hasPlayerDied = true;
		}

		if (card instanceof Chest) {
			populateDiceSideMap(((Chest) card).getChestContent());
		}

		int final_score = 0;

		if (card instanceof SeaBattle) {
			if (diceSideMap.get(DieSides.SWORD) >= ((SeaBattle) card).getRequiredNumberOfSwords()) {
				final_score += ((SeaBattle) card).getAdditionalPoints();
				hasWonAtSea = true;
			} else {
				return -((SeaBattle) card).getAdditionalPoints();
			}
		}

		if (card instanceof MonkeyBusiness) {
			int total = (diceSideMap.get(DieSides.MONKEY) + diceSideMap.get(DieSides.PARROT));
			diceSideMap.put(DieSides.MONKEY, total);
			diceSideMap.put(DieSides.PARROT, 0);
		}

		for (Integer value : diceSideMap.values()) {
			if (ofAKindScoreMap.get(value) != null) {
				final_score += ofAKindScoreMap.get(value).intValue();
			}
		}

		int POINTS_DIAMOND_COIN = diceSideMap.get(DieSides.DIAMOND) * DIAMOND_AND_GOLD_MULTIPLIER;
		int POINTS_GOLD_COIN = diceSideMap.get(DieSides.GOLD) * DIAMOND_AND_GOLD_MULTIPLIER;

		if (hasEarnedBonus() && !hasPlayerDied) {
			final_score += 500;
		}

		final_score += (POINTS_DIAMOND_COIN + POINTS_GOLD_COIN);
		if (card instanceof Captain) {
			final_score = final_score * 2;
		}

		hasWonAtSea = false;
		hasPlayerDied = false;
		return final_score > 0 ? final_score : 0;
	}

	private boolean hasEarnedBonus() {
		if (diceSideMap.get(DieSides.SKULL) > 0) {
			return false;
		}
		if (!hasWonAtSea) {
			if (diceSideMap.get(DieSides.SWORD) == 1 || diceSideMap.get(DieSides.SWORD) == 2) {
				return false;
			}
		}

		if (diceSideMap.get(DieSides.MONKEY) == 1 || diceSideMap.get(DieSides.MONKEY) == 2) {
			return false;
		}
		if (diceSideMap.get(DieSides.PARROT) == 1 || diceSideMap.get(DieSides.PARROT) == 2) {
			return false;
		}

		return true;
	}

	private void populateDiceSideMap(ArrayList<String> dice) {
		for (String dieSide : dice) {
			diceSideMap.put(dieSide, diceSideMap.get(dieSide) + 1);
		}
	}

	private void resetDiceSideMap() {
		diceSideMap.put(DieSides.DIAMOND, 0);
		diceSideMap.put(DieSides.GOLD, 0);
		diceSideMap.put(DieSides.MONKEY, 0);
		diceSideMap.put(DieSides.PARROT, 0);
		diceSideMap.put(DieSides.SKULL, 0);
		diceSideMap.put(DieSides.SWORD, 0);
	}
}
