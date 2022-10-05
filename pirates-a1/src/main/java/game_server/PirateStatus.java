package game_server;

import java.io.Serializable;

import fortune_cards.FortuneCard;

public class PirateStatus implements Serializable {

	private static final long serialVersionUID = 8291837245019196617L;
	private FortuneCard fortuneCard;
    private int message;

    public PirateStatus(FortuneCard fortuneCard, int play) {
        this.fortuneCard = fortuneCard;
        this.message = play;
    }

    public FortuneCard getFortuneCard() {
        return this.fortuneCard;
    }

    public int getMessage() {
        return this.message;
    }
}
