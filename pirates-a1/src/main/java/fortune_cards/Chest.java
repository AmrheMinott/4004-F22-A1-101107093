package fortune_cards;

import java.util.ArrayList;

public class Chest extends FortuneCard {

	private ArrayList<String> chestDice = new ArrayList<String>();

	public void addDiceToChest(String dieface) {
		chestDice.add(dieface);
	}

	public ArrayList<String> getChestContent() {
		return chestDice;
	}

	public void emptyChest() {
		chestDice.clear();
	}
}
