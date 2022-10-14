package pirates_logic;

import static java.util.Map.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

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
public class GameLogic implements Serializable {

    private static final long serialVersionUID = -6515253901269225393L;
    private Map<Integer, Integer> ofAKindScoreMap = Map.ofEntries(entry(3, 100), entry(4, 200), entry(5, 500),
            entry(6, 1000), entry(7, 2000), entry(8, 4000), entry(9, 4000));
    private HashMap<String, Integer> diceSideMap = new HashMap<String, Integer>();
    private final int MAX_NUMBER_OF_SKULLS = 3;
    private final int DIAMOND_AND_GOLD_MULTIPLIER = 100;
    private boolean hasPlayerDied = false;
    private boolean hasWonAtSea = false;

    /**
     * Handles scoring of a player die rolled at the end of the turn.
     * 
     * @param dice - {@link ArrayList<String>}
     * @param card - {@link FortuneCard}
     * @return int - the score that the player deserves for the round.
     */
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
            hasPlayerDied = true;
            System.out.println("Player died!");

            if (card instanceof Chest) {
                resetDiceSideMap();
                populateDiceSideMap(((Chest) card).getChestContent());
                ((Chest) card).emptyChest();

            } else if (diceSideMap.get(DieSides.SKULL) >= MAX_NUMBER_OF_SKULLS && card instanceof SeaBattle) {
                System.out.println("Player lost at sea worth " + ((SeaBattle) card).getAdditionalPoints());
                return -((SeaBattle) card).getAdditionalPoints();
            } else {
                return 0;
            }
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

    public void printPlayerDice(ArrayList<String> dice) {
        for (int i = 0; i < dice.size(); i++) {
            if (i % 4 == 0 && i != 0)
                System.out.println();
            System.out.print(i + 1 + "-> " + dice.get(i) + "\t ");
        }
        System.out.println();
    }

    public void rollAllEightDie(ArrayList<String> dice) {
        rollDiePair(1, 2, dice);
        rollDiePair(3, 4, dice);
        rollDiePair(5, 6, dice);
        rollDiePair(7, 8, dice);
    }

    public ArrayList<String> rollDiePair(int index_1, int index_2, ArrayList<String> dice) {
        dice.set(index_1 - 1, DieSides.DICE_FACES.get((int) (Math.random() * 6)));
        dice.set(index_2 - 1, DieSides.DICE_FACES.get((int) (Math.random() * 6)));
        return dice;
    }

    public int scoreIslandOfTheDeadDeduction(ArrayList<String> roll, FortuneCard card) {
        if (card instanceof Captain) {
            return -((Collections.frequency(roll, DieSides.SKULL) * 100) * 2);
        }
        return -(Collections.frequency(roll, DieSides.SKULL) * 100);
    }

    public String determineWinner(HashMap<String, Integer> scoreMap) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String name : scoreMap.keySet()) {
            if (scoreMap.get(name) >= 3000) {
                list.add(scoreMap.get(name));
            }
        }
        if (list.size() == 1) {
            return getKeyByValue(scoreMap, list.get(0));
        }
        return null;
    }

    private static String getKeyByValue(Map<String, Integer> map, Integer value) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
