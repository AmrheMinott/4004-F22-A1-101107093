package game_server;

import java.io.Serializable;

import fortune_cards.FortuneCard;

public class PirateStatus implements Serializable {

    private static final long serialVersionUID = 8291837245019196617L;
    private FortuneCard fortuneCard;
    private int messageCode;
    private int scoreDeduction = 0;
    private int score = 0;

    public PirateStatus(FortuneCard fortuneCard, int messageCode, int scoreDeduction, int score) {
        this.fortuneCard = fortuneCard;
        this.messageCode = messageCode;
        this.scoreDeduction = scoreDeduction;
        this.score = score;
    }

    public FortuneCard getFortuneCard() {
        return this.fortuneCard;
    }

    public int getMessageCode() {
        return this.messageCode;
    }

    public int getScoreDeduction() {
        return scoreDeduction;
    }

    public int getScore() {
        return score;
    }
}
