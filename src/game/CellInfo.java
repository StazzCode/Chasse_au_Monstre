package game;

/**
 * Cette enum représente les différents types de cellules qui se trouvent sur le labyrinthe.
 */
public enum CellInfo {
    EMPTY(1, ' '),
    WALL(2, 'W'),
    ENTER(3, '_'),
    EXIT(4, '_'),
	MONSTER(5, 'M');

    private int valeur;
    private char car;
    
    /**
     * Constructeur de l'enum CellInfo.
     * @param valeur L'entier correspondant au type de cellule.
     * @param car Le caractère représentant le type de cellule.
     */
    private CellInfo(int valeur, char car) {
        this.valeur = valeur;
        this.car = car;
    }

    /**
     * Retourne l'entier correspondant au type de cellule.
     * @return L'entier correspondant au type de cellule.
     */
    public int getValeur() {
        return this.valeur;
    }
    
    /**
     * Retourne le caractère correspondant au type de cellule.
     * @return Le caractère correspondant au type de cellule.
     */
    public char getCar() {
        return this.car;
    }
        
}
