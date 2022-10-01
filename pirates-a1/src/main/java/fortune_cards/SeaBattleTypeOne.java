package fortune_cards;

public class SeaBattleTypeOne extends SeaBattle {

	private static final long serialVersionUID = 5454772453067333957L;

	@Override
	public int getAdditionalPoints() {
		return 300;
	}

	@Override
	public int getRequiredNumberOfSwords() {
		return 2;
	}

}
