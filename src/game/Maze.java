package game;

/**
 * import
 */
import java.util.Random;
import java.util.Scanner;
import utils.Observer;
import utils.Subject;


/**
 * Cette classe représente un labyrinthe dans un jeu. Elle implémente l'interface Observer pour recevoir des mises à jour de la classe Monster.
 * Le labyrinthe est représenté comme un tableau 2D de cellules. Il contient des méthodes pour générer des obstacles, réinitialiser la carte, mettre à jour le labyrinthe, afficher le labyrinthe, et obtenir le labyrinthe, les colonnes, les lignes, le compteur et le monstre. Il contient également une méthode de mise à jour pour mettre à jour le labyrinthe lorsque le monstre se déplace.
 */
public class Maze implements Observer{
    Cell[][] maze;
    int columns;
    int rows;
    int compteur;
    Monster monster;
    
    public Maze(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        maze = new Cell[columns][rows];
        this.monster = new Monster(this);
    }

    public Cell[][] getMaze() {
        return maze;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCompteur() {
        return compteur;
    }
    
    public Monster getMonster() {
        return monster;
    }
    
    /**
     * Méthode qui met à jour le labyrinthe.
     * @param newMaze la nouvelle grille remplaçant celle du labyrinthe actuel.
     */
    public void updateMaze(Cell[][] newMaze) {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.maze[i][j] = newMaze[i][j];
            }
        }
    }

    public void resetMap() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                maze[i][j] = new Cell(new Coordinate(i,j), CellInfo.EMPTY);
            }
        }
        maze[this.monster.getCoordinate().getColumn()][this.monster.getCoordinate().getRow()] = 
    			new Cell(new Coordinate(this.monster.getCoordinate().getColumn(),this.monster.getCoordinate().getRow()), CellInfo.MONSTER);
    }

    public void generateObstacles() {
        int obstacles = 10;
        for (int i = 0; i < obstacles; i++) {
            int x = new Random().nextInt(columns);
            int y = new Random().nextInt(rows);
            if(this.maze[x][y].getState() != CellInfo.MONSTER) {
            	this.maze[x][y] = new Cell(new Coordinate(x,y), CellInfo.WALL);
            }
        }
    }

    public void displayMaze() {
        for (int i = 0; i < columns; i++) {
            System.out.print("|");
            for (int j = 0; j < rows; j++) {
                System.out.print(maze[i][j].getState().getCar());
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // public static void main(String[] args) {
    //     Maze maze = new Maze(10, 10);
    //     maze.resetMap();
    //     maze.generateObstacles();        
    //     maze.displayMaze();
    //     Scanner sc = new Scanner(System.in);
    //     int i = 0;
    //     while(i < 10) {
    //     	System.out.println("bouge:");
    //     	int chiffre = sc.nextInt();
    //     	if(chiffre == 1) {
    //     		monster.move(new Coordinate(monster.getCoordinate().row-1, monster.getCoordinate().col));
    //     	}
    //     	if(chiffre == 2) {
    //     		monster.move(new Coordinate(monster.getCoordinate().row, monster.getCoordinate().col+1));
    //     	}
    //     	if(chiffre == 3) {
    //     		monster.move(new Coordinate(monster.getCoordinate().row+1, monster.getCoordinate().col));
    //     	}
    //     	if(chiffre == 4) {
    //     		monster.move(new Coordinate(monster.getCoordinate().row, monster.getCoordinate().col));
    //     	}
    //     	i += 1;
    //     }
    //     sc.close();
    // }

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
	        this.maze[monster.getCoordinate().getColumn()][monster.getCoordinate().getRow()] = new Cell(monster.getCoordinate(), CellInfo.MONSTER);
	        this.maze[c.getColumn()][c.getRow()] =  new Cell(c, CellInfo.EMPTY);
	        //System.out.println("Le joueur s'est déplacé en " + monster.getCoordinate().getColumn() + ", " + monster.getCoordinate().getRow());
	        //this.displayMaze();
    }

}
    