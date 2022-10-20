package game_server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import fortune_cards.FortuneCard;

public class PirateStatus implements Serializable {

    private static final long serialVersionUID = 8291837245019196617L;
    private FortuneCard fortuneCard = new FortuneCard();
    private int messageCode = 0;
    private int scoreDeduction = 0;
    private int score = 0;
    private String winMessage = "";

    private HashMap<String, Integer> playerScores = new HashMap<String, Integer>();

    public PirateStatus(FortuneCard fortuneCard, int messageCode, int scoreDeduction, int score) {
        this.fortuneCard = fortuneCard;
        this.messageCode = messageCode;
        this.scoreDeduction = scoreDeduction;
        this.score = score;
    }

    public FortuneCard getFortuneCard() {
        return this.fortuneCard;
    }

    public void setFortuneCard(FortuneCard fortuneCard) {
        this.fortuneCard = fortuneCard;
    }

    public int getMessageCode() {
        return this.messageCode;
    }

    public void setMessageCode(int code) {
        this.messageCode = code;
    }

    public int getScoreDeduction() {
        return scoreDeduction;
    }

    public void setScoreDeduction(int deduction) {
        this.scoreDeduction = deduction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWinMessage(String string) {
        winMessage = string;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public void setPlayerScores(String playerName, int score) {
        playerScores.put(playerName, score);
    }

    @Override
    public String toString() {
        String scoreString = "";

        for (Entry<String, Integer> entry : playerScores.entrySet()) {
            scoreString += (entry.getKey() + ": " + playerScores.get(entry.getKey()) + " ");
        }

        scoreString = (playerScores.keySet().size() == 0 ? "No Scores Yet." : scoreString);
        if (this.fortuneCard != null) {
            return "Fortune Card: " + this.fortuneCard.getClass().getSimpleName() + " Message Code: " + this.messageCode
                    + " Deductions: " + this.scoreDeduction + "\nPlayer Scores: " + scoreString;
        }
        return "Message Code: " + this.messageCode + " Deductions: " + this.scoreDeduction + "\nPlayer Scores: "
                + scoreString;
    }
}
