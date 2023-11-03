package game;

/**
 * import
 */
import java.util.Random;
import java.util.Scanner;
import utils.Observer;
import utils.Subject;


/**
 * La classe Maze représente un labyrinthe dans un jeu de chasse au monstre. Elle implémente l'interface Observer pour recevoir des mises à jour de la classe Monster.
 * Le labyrinthe est représenté comme un tableau 2D de cellules. Il contient des méthodes pour générer des obstacles, réinitialiser la carte, mettre à jour le labyrinthe, afficher le labyrinthe, et obtenir le labyrinthe, les colonnes, les lignes, le compteur et le monstre. Il contient également une méthode de mise à jour pour mettre à jour le labyrinthe lorsque le monstre se déplace.
 */
public class Maze implements Observer{
    Cell[][] maze;
    int columns;
    int rows;
    int compteur;
    Monster monster;

    /**
     * Constructeur d'un labyrinthe avec un nombre de lignes et de colonnes spécifié.
     * @param columns le nombre de colonnes du labyrithe
     * @param rows le nombre de lignes du labyrinthe
     */
    public Maze(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        maze = new Cell[columns][rows];
        this.monster = new Monster(this);
    }

    /**
     * Returns the 2D array of cells representing the maze.
     * @return the maze
     */
    public Cell[][] getMaze() {
        return maze;
    }
    
    /**
     * Retourne le nombre de colonnes du labyrinthe
     * @return le nombre de colonnes du labyrinthe
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Retourne le nombre de lignes du labyrinthe
     * @return le nombre de lignes du labyrinthe
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Retourne le compteur du labyrinthe
     * @return le compteur du labyrinthe
     */
    public int getCompteur() {
        return compteur;
    }
    
    /**
     * Retourne le monstre du labyrinthe
     * @return le monstre du labyrinthe
     */
    public Monster getMonster() {
        return monster;
    }
    
    /**
     * Méthode qui mets à jour le labyrinthe avec un nouveau labyrinthe
     * @param newMaze le nouveau labyrinthe 
     */
    public void updateMaze(Cell[][] newMaze) {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.maze[i][j] = newMaze[i][j];
            }
        }
    }

    /**
     * Méthode qui remet toutes les cases du labyrinthe à vide, sauf celle du monstre.
     */
    public void resetMaze() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                maze[i][j] = new Cell(new Coordinate(i,j), CellInfo.EMPTY);
            }
        }
        maze[this.monster.getCoordinate().getColumn()][this.monster.getCoordinate().getRow()] = 
    			new Cell(new Coordinate(this.monster.getCoordinate().getColumn(),this.monster.getCoordinate().getRow()), CellInfo.MONSTER);
    }

    /**
     * Méthode qui génère un nombre d'obstacles spécifié
     * @param nbObstacles nombre d'obstacles à générer.
     */
    public void generateObstacles(int nbObstacles) {
        Random random = new Random();
        int countObstacles = 0;
        while (countObstacles != nbObstacles) {
            int x = random.nextInt(columns);
            int y = random.nextInt(rows);
            if(this.maze[x][y].getState() == CellInfo.EMPTY) {
            	this.maze[x][y] = new Cell(new Coordinate(x,y), CellInfo.WALL);
                countObstacles++;
            }
        }
    }

    /**
     * Méthode qui génère dix obstacles par défaut.
     */
    public void generateObstacles(){
        generateObstacles(10);
    }

    /**
     * Méthode qui affiche le labyrinthe dans la console
     */
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

    /**
     * Méthode qui met à jour la dernière apparition du monstre
     */
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
    