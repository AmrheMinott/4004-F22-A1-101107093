package fortune_cards;

import java.util.ArrayList;

import constants.DieSides;

public class Sorceress extends FortuneCard {

    private boolean hasBeenActivated = false;

    public boolean activateSorceress(ArrayList<String> dieRolled) {
        if (!this.getHasBeenActivated() && dieRolled.contains(DieSides.SKULL)) {
            int indexOfSkull = dieRolled.indexOf(DieSides.SKULL);
            System.out.println("Sorceress selected index " + (indexOfSkull + 1) + " to reroll.");
            dieRolled.set(indexOfSkull, DieSides.DICE_FACES.get((int) (Math.random() * 6)));
            this.activate();
            return true;
        }
        return false;
    }

    public boolean getHasBeenActivated() {
        return this.hasBeenActivated;
    }

    private void activate() {
        System.out.println("Sorceress has been activated");
        this.hasBeenActivated = true;
    }
}
