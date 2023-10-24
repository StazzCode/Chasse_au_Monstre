package game;

import java.util.ArrayList;

public class Monster extends Player {

    protected Maze maze;
    ArrayList<Coordinate> deplacementPossible = new ArrayList<Coordinate>();

    public Monster(Coordinate c, Maze m){
        this.coordinate = c;
        this.maze = m;
    }

    public boolean canMove(Coordinate c){
        return maze.maze[c.row][c.col].getState() != CellInfo.WALL;
    }

    public void deplacement(ArrayList<Coordinate> deplacementPossible){
        int random = (int) (Math.random() * deplacementPossible.size());
        move(deplacementPossible.get(random));
    }

    public void move(Coordinate c){
        this.coordinate = c;
        notifyObservers();
    }

    
}
