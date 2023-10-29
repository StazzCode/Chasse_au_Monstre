package game;
import java.util.Random;
import java.util.Scanner;

import utils.Observer;
import utils.Subject;

public class Maze implements Observer{
    Cell[][] maze;
    int rows, cols;
    int compteur;
    static Monster monster;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
        this.monster = new Monster(this);
    }

    public void updateMaze(int rows, int cols) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.maze[i][j] = maze[i][j];
            }
        }
    }

    public void resetMap() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                maze[i][j] = new Cell(new Coordinate(i,j), CellInfo.EMPTY);
            }
        }
        maze[this.monster.getCoordinate().row][this.monster.getCoordinate().col] = 
    			new Cell(new Coordinate(this.monster.getCoordinate().row,this.monster.getCoordinate().col), CellInfo.MONSTER);
    }

    public void generateObstacles() {
        int obstacles = 10;
        for (int i = 0; i < obstacles; i++) {
            int x = new Random().nextInt(rows);
            int y = new Random().nextInt(cols);
            if(this.maze[x][y].getState() != CellInfo.MONSTER) {
            	this.maze[x][y] = new Cell(new Coordinate(x,y), CellInfo.WALL);
            }
        }
    }

    // affichage plateau
    public void displayMaze() {
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j].getState().getCar());
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        maze.resetMap();
        maze.generateObstacles();        
        maze.displayMaze();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(i < 10) {
        	System.out.println("bouge:");
        	int chiffre = sc.nextInt();
        	if(chiffre == 1) {
        		monster.move(new Coordinate(monster.getCoordinate().row-1, monster.getCoordinate().col));
        	}
        	if(chiffre == 2) {
        		monster.move(new Coordinate(monster.getCoordinate().row, monster.getCoordinate().col+1));
        	}
        	if(chiffre == 3) {
        		monster.move(new Coordinate(monster.getCoordinate().row+1, monster.getCoordinate().col));
        	}
        	if(chiffre == 4) {
        		monster.move(new Coordinate(monster.getCoordinate().row, monster.getCoordinate().col));
        	}
        	i += 1;
        }
    }

    public void updateLastAppearance() {
        //
    }

	@Override
	public void update(Subject subject) {
	}

	@Override
	public void update(Subject subject, Object lastCoordinate) {
		 	Monster monster = (Monster) subject;
		 	Coordinate c = (Coordinate) lastCoordinate;
	        this.maze[monster.coordinate.row][monster.coordinate.col] = new Cell(monster.coordinate, CellInfo.MONSTER);
	        this.maze[c.row][c.col] =  new Cell(monster.coordinate, CellInfo.EMPTY);
	        System.out.println("Le joueur s'est déplacé en " + monster.getCoordinate().row + ", " + monster.getCoordinate().col);
	        this.displayMaze();
	        
	}
}
    