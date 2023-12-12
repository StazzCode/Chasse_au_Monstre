package game.model;

public class GameParameter {
    private Difficulty difficulty;
    private String firstPlayerName, secondPlayerName;
    private Integer longueur, largeur, pourcentageObs;

    
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
    public Integer getLongueur() {return longueur;}
    public Integer getLargeur() {return largeur;}
    public void setLongueur(Integer longueur) {this.longueur = longueur;}
    public void setLargeur(Integer largeur) {this.largeur = largeur;}
    public Integer getPourcentageObs(){return pourcentageObs;}
    public void setPourcentageObs(Integer pourcentageObs){this.pourcentageObs = pourcentageObs;}

    @Override
    public String toString() {
        return "GameParameter [difficulty=" + difficulty + ", firstPlayerName=" + firstPlayerName
                + ", secondPlayerName=" + secondPlayerName + "]";
    }

    
}
