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

}
