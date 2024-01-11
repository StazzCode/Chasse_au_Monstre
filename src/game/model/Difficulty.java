package game.model;

/**
 * Cette énumération représente les différents niveaux de difficulté du jeu,
 * elle prend en paramètre un nombre de lignes, de colonnes et d'obstacles pour
 * le labyrinthe en fonction du niveau de difficulté choisi.
 */

public enum Difficulty {
    TRES_FACILE(4, 4, 25, 3),
    FACILE(5, 5, 35, 2),
    MOYEN(6, 6, 50, 2),
    DIFFICILE(8, 8, 65, 2),
    TRES_DIFFICILE(10, 10, 75, 1);

    public final int rowsDifficulty;
    public final int columnsDifficulty;
    public final int nbObstaclesDifficulty;
    public final int fogRange;

    /**
     * Constructeur prenant en paramètres le nombre de lignes, de colonnes et
     * d'obstacles pour le niveau de labyrinthe sélectionné
     * 
     * @param rowsDifficulty        le nombre de lignes du labyrinthe
     * @param columnsDifficulty     le nombre de colonnes du labyrinthe
     * @param nbObstaclesDifficulty le nombre d'obstacles du labyrinthe
     * @param fogRange              le rayon du champ de vision du Monstre
     */
    private Difficulty(int rowsDifficulty, int columnsDifficulty, int nbObstaclesDifficulty, int fogRange) {
        this.rowsDifficulty = rowsDifficulty;
        this.columnsDifficulty = columnsDifficulty;
        this.nbObstaclesDifficulty = nbObstaclesDifficulty;
        this.fogRange = fogRange;
    }

    /**
     * Retourne le niveau de difficulté du labyrinthe.
     * 
     * @return le niveau de difficulté du labyrinthe
     */
    public Difficulty getDifficulty() {
        return this;
    }

    /**
     * Retourne le nombre de lignes du niveau de difficulté du labyrinthe.
     * 
     * @return le nombre de lignes du niveau de difficulté du labyrinthe
     */
    public int getRowsDifficulty() {
        return rowsDifficulty;
    }

    /**
     * Retourne le nombre de colonnes du niveau de difficulté du labyrinthe.
     * 
     * @return le nombre de colonnes du niveau de difficulté du labyrinthe
     */
    public int getColumnsDifficulty() {
        return columnsDifficulty;
    }

    /**
     * Retourne le nombre d'obstacles du niveau de difficulté du labyrinthe.
     * 
     * @return le nombre d'obstacles du niveau de difficulté du labyrinthe
     */
    public int getNbObstaclesDifficulty() {
        return nbObstaclesDifficulty;
    }

    /**
     * Retourne le rayon de champ de vision du Monstre.
     *
     * @return le rayon de champ de vision du Monstre
     */
    public int getFogRange() {
        return fogRange;
    }

    /**
     * Retourne le niveau de difficulté du labyrinthe en fonction de l'entier
     * 
     * @param i l'entier
     * @return le niveau de difficulté du labyrinthe
     */
    public static Difficulty fromInt(int i) {
        return Difficulty.values()[i];
    }
}
