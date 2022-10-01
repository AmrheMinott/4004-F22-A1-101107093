package fortune_cards;

import java.util.ArrayList;

public class Chest extends FortuneCard {

	private static final long serialVersionUID = 7859540471514470734L;
	private ArrayList<String> chestDice = new ArrayList<String>();

	public void addDiceToChest(String dieFace) {
		chestDice.add(dieFace);
	}

	public String takeOut(int index) {
		String die = chestDice.get(index);
		chestDice.remove(index);
		return die;
	}

	public ArrayList<String> getChestContent() {
		return chestDice;
	}

	public void emptyChest() {
		chestDice.clear();
	}
}
