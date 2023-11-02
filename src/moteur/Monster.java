package moteur;

public class Monster extends Player {

    protected Maze maze;

    public Monster(Coordinate c, Maze m){
        this.coordinate = c;
        this.maze = m;
    }

    public boolean canMove(Coordinate c){
        return maze.maze[c.row][c.col].getState() != CellInfo.WALL;
    }

    public void move(Coordinate c){
        this.coordinate = c;
        notifyObservers();
        this.maze.maze[c.row][c.col].setLastMonsterAppearance(GameMain.turn);
    }
}
