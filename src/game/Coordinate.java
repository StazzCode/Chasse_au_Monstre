package game;

/**
 * Cette classe représente les coordonnées d'une cellule dans le labyrinthe du jeu.
 */
public class Coordinate {
    int column;
    int row;

    /**
     * Constructeur de la classe Coordinate avec les valeurs en ligne et en colonne données.
     * @param column la colonne des coordonnées
     * @param row la ligne des coordonnées
     */
    public Coordinate(int column,int row){
        this.column = column;
        this.row = row;
    }
    
    /**
     * Retourne la valeur de la colonne des coordonnées.
     * @return la valeur de la colonne des coordonnées
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Retourne la valeur de la ligne des coordonnées
     * @return la valeur de la ligne des coordonnées
     */
    public int getRow(){
        return this.row;
    }
}
