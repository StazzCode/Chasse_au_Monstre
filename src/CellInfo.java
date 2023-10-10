public enum CellInfo {
    EMPTY(1),
    WALL(2),
    ENTER(3),
    EXIT(4);

    private int valeur;

    private CellInfo(int valeur) {
        this.valeur = valeur;
    }

    private int getValeur() {
        return this.valeur;
    }
        
}
