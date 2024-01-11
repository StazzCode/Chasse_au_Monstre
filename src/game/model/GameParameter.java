package game.model;

/**
 * La classe GameParameter représente les paramètres du jeu.
 * Elle contient des informations telles que la difficulté, les noms des
 * joueurs,
 * la longueur et la largeur du jeu, ainsi que le pourcentage d'obstacles.
 */
public class GameParameter {
    private Difficulty difficulty;
    private String firstPlayerName, secondPlayerName;
    private Integer longueur, largeur, pourcentageObs, fogRange;
    private boolean iaHunter = false;
    private boolean iaMonster = false;

    /**
     * Obtient la difficulté du jeu.
     * 
     * @return La difficulté du jeu.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Définit la difficulté du jeu.
     * 
     * @param difficulty La difficulté du jeu.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Obtient le nom du premier joueur.
     * 
     * @return Le nom du premier joueur.
     */
    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    /**
     * Définit le nom du premier joueur.
     * 
     * @param firstPlayerName Le nom du premier joueur.
     */
    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    /**
     * Obtient le nom du deuxième joueur.
     * 
     * @return Le nom du deuxième joueur.
     */
    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    /**
     * Définit le nom du deuxième joueur.
     * 
     * @param secondPlayerName Le nom du deuxième joueur.
     */
    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    /**
     * Obtient la longueur du jeu.
     * 
     * @return La longueur du jeu.
     */
    public Integer getLongueur() {
        return longueur;
    }

    /**
     * Obtient la largeur du jeu.
     * 
     * @return La largeur du jeu.
     */
    public Integer getLargeur() {
        return largeur;
    }

    /**
     * Définit la longueur du jeu.
     * 
     * @param longueur La longueur du jeu.
     */
    public void setLongueur(Integer longueur) {
        this.longueur = longueur;
    }

    /**
     * Définit la largeur du jeu.
     * 
     * @param largeur La largeur du jeu.
     */
    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    /**
     * Obtient le pourcentage d'obstacles.
     * 
     * @return Le pourcentage d'obstacles.
     */
    public Integer getPourcentageObs() {
        return pourcentageObs;
    }

    /**
     * Définit le pourcentage d'obstacles.
     * 
     * @param pourcentageObs Le pourcentage d'obstacles.
     */
    public void setPourcentageObs(Integer pourcentageObs) {
        this.pourcentageObs = pourcentageObs;
    }

    /**
     * Méthode toString de la classe GameParameter.
     */
    @Override
    public String toString() {
        return "GameParameter [difficulty=" + difficulty + ", firstPlayerName=" + firstPlayerName
                + ", secondPlayerName=" + secondPlayerName + "]";
    }

    /**
     * Méthode qui permet de savoir si l'IA pour le chasseur est activée
     * 
     * @return true si l'IA est activée, false sinon
     */
    public boolean getIaHunter() {
        return iaHunter;
    }

    /**
     * Méthode qui permet de savoir si l'IA pour le monstre est activée
     * 
     * @return true si l'IA est activée, false sinon
     */
    public boolean getIaMonster() {
        return iaMonster;
    }

    /**
     * Méthode qui permet de définir si l'IA pour le chasseur est activée
     * 
     * @param iaHunter
     */
    public void setIaHunter(boolean iaHunter) {
        this.iaHunter = iaHunter;
    }

    /**
     * Méthode qui permet de définir si l'IA pour le monstre est activée
     * 
     * @param iaMonster
     */
    public void setIaMonster(boolean iaMonster) {
        this.iaMonster = iaMonster;
    }

    public Integer getFogRange() {
        return fogRange;
    }

    public void setFogRange(Integer fogRange) {
        this.fogRange = fogRange;
    }
}
