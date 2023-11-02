package game;

import java.util.ArrayList;

public class Monster extends Player {

    protected Maze maze;
    ArrayList<Coordinate> deplacementPossible = new ArrayList<Coordinate>();

    public Monster(Maze m){
        this.coordinate = new Coordinate(0, 0);
        this.attach(m);
        this.maze = m;
    }

    public boolean canMove(Coordinate c){
        if (c.getColumn()<0 || c.getColumn()>= maze.getColumns() || c.getRow()<0 || c.getRow()>= maze.getRows()) {
            return false;
        }
        return maze.maze[c.getColumn()][c.getRow()].getState() != CellInfo.WALL;
    }

    public void deplacement(ArrayList<Coordinate> deplacementPossible){
        int random = (int) (Math.random() * deplacementPossible.size());
        move(deplacementPossible.get(random));
    }

    public void move(Coordinate c){
    	if(canMove(c)) {
    		Coordinate previousCoordinate = this.coordinate;
            this.coordinate = c;
            this.notifyObservers(previousCoordinate);
    	}
    }

    
}
