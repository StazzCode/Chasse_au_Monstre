package game;

/**
 * La classe Cell représente une cellule dans le labyrinthe du jeu.
 * Chaque cellule a une coordonnée, un état(découverte ou non), un indice sur la dernière fois que le monstre est apparu sur cette case et un type de cellule(donner par l'enum CellInfo).
 */
public class Cell {

    private int lastMonsterAppearance;
    private boolean discovered;
    private Coordinate coordinate;
    private CellInfo state;

    /**
     * Constructeur d'une cellule avec les coordonnées de la cellule et l'état de la cellule.
     * @param c les coordonnées de la cellule
     * @param state l'état de la cellule
     */
    public Cell(Coordinate c, CellInfo state){
        this.coordinate = c;
        this.state = state;
    }

    /**
     * Retourne l'indice de la dernière apparence du monstre sur cette case.
     * @return l'indice de la dernière apparence du monstre sur cette case
     */
    public int getLastMonsterAppearance() {
        return this.lastMonsterAppearance;
    }

    /**
     * Retourne si la cellule a été découverte, c'est à dire visible par le chasseur.
     * @return si la cellule a été découverte, c'est à dire visible par le chasseur
     */
    public boolean isDiscovered() {
        return this.discovered;
    }

    /**
     * Retourne les coordonnées de la cellule.
     * @return les coordonnées de la cellule
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Retourne l'état de la cellule.
     * @return l'état de la cellule
     */
    public CellInfo getState(){
        return this.state;
    }
}
