package pirates_logic;

import java.util.ArrayList;
import java.util.HashMap;

import constants.DieSides;
import fortune_cards.Captain;
import fortune_cards.FortuneCard;

/**
 * 
 * @author Amrhe Minott
 *
 */
public class GameLogic {

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
		int POINTS_DIAMOND_COIN = diceSideMap.get(DieSides.DIAMOND) * DIAMOND_AND_GOLD_MULTIPLIER;
		int POINTS_GOLD_COIN = diceSideMap.get(DieSides.GOLD) * DIAMOND_AND_GOLD_MULTIPLIER;
		
		int final_score = POINTS_DIAMOND_COIN + POINTS_GOLD_COIN;
		if (card instanceof Captain) {
			final_score = final_score * 2;
		}
		return final_score;
	}
}
