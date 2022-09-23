package pirates_logic;

import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.FortuneCard;
import fortune_cards.MonkeyBusiness;

/**
 * 
 * @author Amrhe Minott
 *
 */
public class GameLogic {

	private Map<Integer, Integer> ofAKindScoreMap = Map.ofEntries(entry(3, 100), entry(4, 200), entry(5, 500),
			entry(6, 1000), entry(7, 2000), entry(8, 4000));
	private HashMap<String, Integer> diceSideMap = new HashMap<String, Integer>();
	private final int MAX_NUMBER_OF_SKULLS = 3;
	private final int DIAMOND_AND_GOLD_MULTIPLIER = 100;

	public int scoreTurn(ArrayList<String> dice, FortuneCard card) {
		diceSideMap.put(DieSides.DIAMOND, 0);
		diceSideMap.put(DieSides.GOLD, 0);
		diceSideMap.put(DieSides.MONKEY, 0);
		diceSideMap.put(DieSides.PARROT, 0);
		diceSideMap.put(DieSides.SKULL, 0);
		diceSideMap.put(DieSides.SWORD, 0);

		for (String dieSide : dice) {
			diceSideMap.put(dieSide, diceSideMap.get(dieSide) + 1);
		}

		if (diceSideMap.get(DieSides.SKULL) >= MAX_NUMBER_OF_SKULLS) {
			return 0;
		}

		int final_score = 0;

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

		final_score += (POINTS_DIAMOND_COIN + POINTS_GOLD_COIN);
		if (card instanceof Captain) {
			final_score = final_score * 2;
		}
		return final_score;
	}
}
