package fortune_cards;

import java.util.ArrayList;

public class Chest extends FortuneCard {

    private ArrayList<String> chestDice = new ArrayList<String>();

    public void addDiceToChest(String dieFace) {
        System.out.println(dieFace + " was added to chest.");
        chestDice.add(dieFace);
    }

    public String takeOut(int index) {
        String die = chestDice.get(index);
        System.out.println("Removed from Chest: " + die);
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
