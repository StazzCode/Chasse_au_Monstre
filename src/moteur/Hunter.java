package moteur;

public class Hunter extends Player{

    protected Maze maze;

    public Hunter(Coordinate c, Maze m){
        this.coordinate = c;
        this.maze = m;
    }


    public boolean monsterHit(moteur.Coordinate c){
        notifyObservers();
        return maze.maze[c.row][c.col].getLastMonsterAppearance() == GameMain.turn;
    }

}
