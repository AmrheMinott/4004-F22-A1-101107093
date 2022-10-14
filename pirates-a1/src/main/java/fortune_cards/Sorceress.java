package fortune_cards;

import java.util.ArrayList;

import constants.DieSides;

public class Sorceress extends FortuneCard {

    private boolean hasBeenActivated = false;

    public boolean activateSorceress(ArrayList<String> dieRolled) {
        if (!this.getHasBeenActivated() && dieRolled.contains(DieSides.SKULL)) {
            dieRolled.set(dieRolled.indexOf(DieSides.SKULL),
                    DieSides.DICE_FACES.get((int) (Math.random() * 6)));
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
