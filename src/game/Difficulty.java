package game;

/**
 * Cette énumération représente les différents niveaux de difficulté du jeu,
 * elle prend en paramètre un nombre de lignes, de colonnes et d'obstacles pour
 * le labyrinthe en fonction du niveau de difficulté choisi.
 */

public enum Difficulty {
    TRES_FACILE(8, 8, 16),
    FACILE(10, 10, 20),
    MOYEN(12, 12, 24),
    DIFFICILE(14, 14, 28),
    TRES_DIFFICILE(16, 16, 32);

    public final int rowsDifficulty;
    public final int columnsDifficulty;
    public final int nbObstaclesDifficulty;

    /**
     * Constructeur prenant en paramètres le nombre de lignes, de colonnes et
     * d'obstacles pour le niveau de labyrinthe sélectionné
     * 
     * @param rowsDifficulty        le nombre de lignes du labyrinthe
     * @param columnsDifficulty     le nombre de colonnes du labyrinthe
     * @param nbObstaclesDifficulty le nombre d'obstacles du labyrinthe
     */
    private Difficulty(int rowsDifficulty, int columnsDifficulty, int nbObstaclesDifficulty) {
        this.rowsDifficulty = rowsDifficulty;
        this.columnsDifficulty = columnsDifficulty;
        this.nbObstaclesDifficulty = nbObstaclesDifficulty;
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
     * Retourne le niveau de difficulté du labyrinthe en fonction de l'entier
     * @param i l'entier
     * @return le niveau de difficulté du labyrinthe
     */
	public static Difficulty fromInt(int i){
		return Difficulty.values()[i];
	}
}
