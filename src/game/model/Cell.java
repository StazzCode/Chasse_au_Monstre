package game.model;

/**
 * La classe Cell représente une cellule dans le labyrinthe du jeu.
 * Chaque cellule a une coordonnée, un état(découverte ou non), un indice sur la dernière fois que le monstre est apparu sur cette case et un type de cellule(donner par l'enum CellInfo).
 */
public class Cell {

    private int lastMonsterAppearance;
    private int previousLastMonsterAppearance;
    private boolean discovered;
    private Coordinate coordinate;
    private CellInfo state;

    /**
     * Constructeur d'une cellule avec les coordonnées de la cellule et l'état de la cellule.
     * @param c les coordonnées de la cellule
     * @param state l'état de la cellule
     */
    public Cell(Coordinate c, CellInfo state){
        this.lastMonsterAppearance = -1;
        this.previousLastMonsterAppearance = lastMonsterAppearance;
        this.discovered = false;
        this.coordinate = c;
        this.state = state;
    }

    /**
     * Constructeur d'une cellule avec les coordonnées de la cellule, l'état de la cellule et l'indice de la dernière apparence du monstre sur cette case.
     * @param c les coordonnées de la cellule
     * @param state l'état de la cellule
     * @param lastMonsterAppearance la dernière apparence du monstre sur cette case
     */
    public Cell(Coordinate c, CellInfo state, int lastMonsterAppearance){
        this(c,state);
        this.lastMonsterAppearance = lastMonsterAppearance;
    }

    /**
     * Retourne l'indice de la dernière apparence du monstre sur cette case.
     * @return l'indice de la dernière apparence du monstre sur cette case
     */
    public int getLastMonsterAppearance() {
        return this.lastMonsterAppearance;
    }

    /**
     * Redéfinie l'indice de la dernière apparence du monstre sur cette case.
     * @param lastMonsterAppearance L'indice de la dernière apparence du monstre sur cette case.
     */
    public void setLastMonsterAppearance(int lastMonsterAppearance) {
        this.lastMonsterAppearance = lastMonsterAppearance;
    }

    /**
     * Retourne l'indice de l'avant-dernière apparence du monstre sur cette case.
     * @return l'indice de l'avant-dernière apparence du monstre sur cette case
     */
    public int getPreviousLastMonsterAppearance() {
        return previousLastMonsterAppearance;
    }

    /**
     * Redéfinie l'indice de l'avant-dernière apparence du monstre sur cette case.
     * @param previousLastMonsterAppearance L'indice de l'avant-dernière apparence du monstre sur cette case.
     */
    public void setPreviousLastMonsterAppearance(int previousLastMonsterAppearance) {
        this.previousLastMonsterAppearance = previousLastMonsterAppearance;
    }

    /**
     * Retourne si la cellule a été découverte, c'est-à-dire visible par le chasseur.
     * @return si la cellule a été découverte, c'est-à-dire visible par le chasseur
     */
    public boolean isDiscovered() {
        return this.discovered;
    }
    public void discover() {
        this.discovered = true;
    }

    /**
     * Redéfinie l'attribut discovered de la cellule.
     * @param discovered L'attribut discovered de la cellule.
     */
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
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

    /**
     * Met à jour l'état de la cellule.
     * @param state l'état de la cellule
     */
    public void setState(CellInfo state) {
        this.state = state;
    }
}
