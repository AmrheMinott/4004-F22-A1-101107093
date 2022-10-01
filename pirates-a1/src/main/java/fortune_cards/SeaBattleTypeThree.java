package fortune_cards;

public class SeaBattleTypeThree extends SeaBattle {

	private static final long serialVersionUID = -4163017126413858564L;

	@Override
	public int getAdditionalPoints() {
		return 1000;
	}

	@Override
	public int getRequiredNumberOfSwords() {
		return 4;
	}

}
