package fortune_cards;

public class SeaBattleTypeThree extends SeaBattle {

    @Override
    public int getAdditionalPoints() {
        return 1000;
    }

    @Override
    public int getRequiredNumberOfSwords() {
        return 4;
    }

    @Override
    public String toString() {
        return "Sea Battle Type Three Card - Potential points: " + getAdditionalPoints() + " and needed Swords to win: "
                + getRequiredNumberOfSwords();
    }
}
