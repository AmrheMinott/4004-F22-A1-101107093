package fortune_cards;

import java.util.ArrayList;

public class Chest extends FortuneCard {

	private ArrayList<String> chestDice = new ArrayList<String>();

	public void addDiceToChest(String dieFace) {
		chestDice.add(dieFace);
	}

	public ArrayList<String> getChestContent() {
		return chestDice;
	}

	public void emptyChest() {
		chestDice.clear();
	}
}
