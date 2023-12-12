package game.model;

public class GameParameter {
    private Difficulty difficulty;
    private String firstPlayerName, secondPlayerName;

    
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public String getFirstPlayerName() {
        return firstPlayerName;
    }
    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }
    public String getSecondPlayerName() {
        return secondPlayerName;
    }
    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }
    @Override
    public String toString() {
        return "GameParameter [difficulty=" + difficulty + ", firstPlayerName=" + firstPlayerName
                + ", secondPlayerName=" + secondPlayerName + "]";
    }

    
}
