package fortune_cards;

import java.util.ArrayList;

import constants.DieSides;

public class Sorceress extends FortuneCard {

	private static final long serialVersionUID = -1826416321724934438L;
	private boolean hasBeenActivated = false;

    public void activate() {
        this.hasBeenActivated = true;
    }

    public boolean getHasBeenActivated() {
        return this.hasBeenActivated;
    }

    public boolean activateSorceress(ArrayList<String> dieRolled) {
        if (!this.getHasBeenActivated() && dieRolled.contains(DieSides.SKULL)) {
            dieRolled.set(dieRolled.indexOf(DieSides.SKULL),
                    DieSides.DICE_FACES.get((int) (Math.random() * 6)));
            this.activate();
            return true;
        }
        return false;
    }
}
