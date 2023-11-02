package game;

public class Cell {

    private int lastMonsterAppearance;
    private boolean discovered;
    private Coordinate coordinate;
    private CellInfo state;

    public Cell(Coordinate c, CellInfo state){
        this.coordinate = c;
        this.state = state;
    }

    public int getLastMonsterAppearance() {
        return this.lastMonsterAppearance;
    }

    public boolean isDiscovered() {
        return this.discovered;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public CellInfo getState(){
        return this.state;
    }
}
