package fortune_cards;

public class SeaBattleTypeTwo extends SeaBattle {

    private static final long serialVersionUID = 2805731354155502584L;

    @Override
    public int getAdditionalPoints() {
        return 500;
    }

    @Override
    public int getRequiredNumberOfSwords() {
        return 3;
    }

    @Override
    public String toString() {
        return "Sea Battle Type Two Card - Potential points: " + getAdditionalPoints() + "and needed Swords to win: "
                + getRequiredNumberOfSwords();
    }
}
