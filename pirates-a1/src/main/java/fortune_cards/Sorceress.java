package fortune_cards;

public class Sorceress extends FortuneCard {

    private boolean hasBeenActivated = false;

    public void activate() {
        this.hasBeenActivated = true;
    }

    public boolean getHasBeenActivated() {
        return this.hasBeenActivated;
    }
}
