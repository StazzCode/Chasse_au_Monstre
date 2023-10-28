package game;
public enum CellInfo {
    EMPTY(1, ' '),
    WALL(2, 'M'),
    ENTER(3, '_'),
    EXIT(4, '_'),
	MONSTER(5, 'O');

    private int valeur;
    private char car;
    
    private CellInfo(int valeur, char car) {
        this.valeur = valeur;
        this.car = car;
    }

    public int getValeur() {
        return this.valeur;
    }
    
    public char getCar() {
        return this.car;
    }
        
}
