package game;

/**
 * import
 */
import java.util.Random;
// import java.util.Scanner;
import utils.Observer;
import utils.Subject;

/**
 * La classe Maze représente un labyrinthe dans un jeu de chasse au monstre.
 * Elle implémente l'interface Observer pour recevoir des mises à jour de la
 * classe Monster.
 * Le labyrinthe est représenté comme un tableau 2D de cellules. Il contient des
 * méthodes pour générer des obstacles, réinitialiser la carte, mettre à jour le
 * labyrinthe, afficher le labyrinthe, et obtenir le labyrinthe, les colonnes,
 * les lignes, le compteur et le monstre. Il contient également une méthode de
 * mise à jour pour mettre à jour le labyrinthe lorsque le monstre se déplace.
 */
public class Maze implements Observer {
    Cell[][] maze;
    int columns;
    int rows;
    int compteur;
    Monster monster;
    Hunter hunter;
    boolean end = false;
    Difficulty difficulty;

    /**
     * Constructeur d'un labyrinthe avec un nombre de lignes et de colonnes
     * spécifié.
     * 
     * @param columns le nombre de colonnes du labyrithe
     * @param rows    le nombre de lignes du labyrinthe
     */
    public Maze(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        maze = new Cell[columns][rows];
        this.monster = new Monster(this);
        this.hunter = new Hunter(this);
    }

    /**
     * Returns the 2D array of cells representing the maze.
     * 
     * @return the maze
     */
    public Cell[][] getMaze() {
        return maze;
    }

    /**
     * Retourne le nombre de colonnes du labyrinthe
     * 
     * @return le nombre de colonnes du labyrinthe
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Retourne le nombre de lignes du labyrinthe
     * 
     * @return le nombre de lignes du labyrinthe
     */
    public int getRows() {
        return rows;
    }

    /**
     * Retourne le compteur du labyrinthe
     * 
     * @return le compteur du labyrinthe
     */
    public int getCompteur() {
        return compteur;
    }

    /**
     * Retourne le monstre du labyrinthe
     * 
     * @return le monstre du labyrinthe
     */
    public Monster getMonster() {
        return this.monster;
    }

    /**
     * Retourne le chasseur du labyrinthe
     * @return le chasseur du labyrinthe
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Retourne l'arrivée du labyrinthe
     * @return l'arrivée du labyrinthe
     */
    public boolean getEnd() {
        return this.end;
    }

    /**
     * Méthode qui met à jour le labyrinthe avec un nouveau labyrinthe
     * 
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
     * Méthode qui remet toutes les cases du labyrinthe à vide, sauf celle du
     * monstre.
     */
    public void resetMaze() {
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                maze[i][j] = new Cell(new Coordinate(i, j), CellInfo.EMPTY);
            }
        }
        maze[this.monster.getCoordinate().getColumn()][this.monster.getCoordinate().getRow()].setState(CellInfo.MONSTER);
    }

    /**
     * Méthode qui génère un nombre d'obstacles spécifié
     * 
     * @param nbObstacles nombre d'obstacles à générer.
     */
    public void generateObstacles(int nbObstacles) {
        Random random = new Random();
        int countObstacles = 0;
        while (countObstacles != nbObstacles) {
            int x = random.nextInt(columns);
            int y = random.nextInt(rows);
            if(this.maze[x][y].getState() == CellInfo.EMPTY) {
            	this.maze[x][y].setState(CellInfo.WALL);
                countObstacles++;
            }
        }
    }

    /**
     * Méthode qui génère dix obstacles par défaut.
     */
    public void generateObstacles() {
        generateObstacles(10);
    }

    /**
     * Méthode qui permet de générer un nombre d'obstacles en fonction de la taille
     * du labyrinthe
     * 
     * @param columns le nombre de colonnes du labyrinthe
     * @param rows    le nombre de lignes du labyrinthe
     * @return nbObstacles le nombre d'obstacles générés
     */
    public void generateMazeObstacles(int columns, int rows) {
        int nbObstacles = (columns * rows) / 4;

        generateObstacles(nbObstacles);

    }

    /**
     * Méthode qui permet de générer un trou sur le labyrinthe
     */
    public void generateHole() {
        Random random = new Random();
        int x = random.nextInt(columns);
        int y = random.nextInt(rows);
        if (this.maze[x][y].getState() == CellInfo.EMPTY) {
            this.maze[x][y] = new Cell(new Coordinate(x, y), CellInfo.HOLE);
        }
    }


    /**
     * Méthode qui génère le nombre de colonnes du labyrinthe en fonction de la difficulté
     * @param d le niveau de difficulté choisi
     * @return le nombre de colonnes du labyrinthe en fonction de la difficulté
     */
    public int generateColumnsDifficulty(Difficulty d) {

        if (d.equals(Difficulty.TRES_FACILE)) {
            columns = 6;
        } else if (d.equals(Difficulty.FACILE)) {
            columns = 8;
        } else if (d.equals(Difficulty.MOYEN)) {
            columns = 10;
        } else if (d.equals(Difficulty.DIFFICILE)) {
            columns = 12;
        } else if (d.equals(Difficulty.TRES_DIFFICILE)) {
            columns = 14;
        }
        return columns;
    }

    /**
     * Méthode qui génère le nombre de lignes du labyrinthe en fonction de la difficulté
     * @param d le niveau de difficulté choisi
     * @return le nombre de lignes du labyrinthe en fonction de la difficulté
     */
    public int generateRowsDifficulty(Difficulty d) {

        if (d.equals(Difficulty.TRES_FACILE)) {
            rows = 6;
        } else if (d.equals(Difficulty.FACILE)) {
            rows = 8;
        } else if (d.equals(Difficulty.MOYEN)) {
            rows = 10;
        } else if (d.equals(Difficulty.DIFFICILE)) {
            rows = 12;
        } else if (d.equals(Difficulty.TRES_DIFFICILE)) {
            rows = 14;
        }
        return rows;
    }

    /**
     * Méthode qui génère l'entrée et la sortie du labyrinthe.
     */
    public void generateEnterExit(){
    	Random random = new Random();
    	
    	int rand =3;
    	System.out.println(rand);
    	
    	if(rand == 0) {// TOP
    		int col = random.nextInt(this.columns);
    		this.maze[col][0] = new Cell(new Coordinate(col,0), CellInfo.ENTER);
    		this.monster.setMonsterPosition(new Coordinate(col,0));
    		col = random.nextInt(this.columns);
    		this.maze[col][this.rows-1] = new Cell(new Coordinate(col,this.rows-1), CellInfo.EXIT);
    	}else if(rand == 1) { // RIGHT
    		int row = random.nextInt(this.rows);
    		this.maze[this.columns-1][row] = new Cell(new Coordinate(this.columns-1,row), CellInfo.ENTER);
    		this.monster.setMonsterPosition(new Coordinate(this.columns-1 ,row));
    		row = random.nextInt(this.columns);
    		this.maze[0][row] = new Cell(new Coordinate(0, row), CellInfo.EXIT);
    	}else if(rand == 2) { // BOTTOM
    		int col = random.nextInt(this.columns);
    		this.maze[col][this.rows-1] = new Cell(new Coordinate(col, this.rows-1), CellInfo.ENTER);
    		this.monster.setMonsterPosition(new Coordinate(col, this.rows-1));
    		col = random.nextInt(this.rows);
    		this.maze[col][0] = new Cell(new Coordinate(col, 0), CellInfo.EXIT);
    	}else if(rand == 3) { // LEFT
    		int row = random.nextInt(this.rows);
    		this.maze[0][row] = new Cell(new Coordinate(0, row), CellInfo.ENTER);
    		this.monster.setMonsterPosition(new Coordinate(0, row));
    		row = random.nextInt(this.columns);
    		this.maze[this.columns-1][row] = new Cell(new Coordinate(this.columns-1,row), CellInfo.EXIT);
    	}
    }
    
    /**
     * Méthode qui retourne les coordonnées de l'entrée du labyrinthe
     * @return les coordonnées de l'entrée du labyrinthe
     */
    public Coordinate getEnter() {
    	Coordinate enter = null;
    	for(int i = 0; i < this.columns; i++) {
    		for(int j = 0; j < this.rows; i++) {
    			if(this.maze[i][j].getState().getCar() == CellInfo.ENTER.getCar()) {
    				enter = new Coordinate(i, j);
    			}
    		}
    	}
    	return enter;
    }
    
    /**
     * Méthode qui retourne les coordonnées de la sortie du labyrinthe
     * @return les coordonnées de la sortie du labyrinthe
     */
    public Coordinate getExit() {
    	Coordinate exit = null;
    	for(int i = 0; i < this.columns; i++) {
    		for(int j = 0; j < this.rows; i++) {
    			if(this.maze[i][j].getState().getCar() == CellInfo.EXIT.getCar()) {
    				exit = new Coordinate(i, j);
    			}
    		}
    	}
    	return exit;
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

    /**
     * Méthode qui met à jour 
     */
    @Override
	public void update(Subject subject) {
		Hunter hunter = (Hunter) subject;
		this.hunter = hunter;
		int row = this.hunter.getHitsList().get(this.hunter.getHitsList().size()-1).getRow();
        int col = this.hunter.getHitsList().get(this.hunter.getHitsList().size()-1).getColumn();
		if(this.maze[col][row].getState().getCar() == CellInfo.MONSTER.getCar()) {
			end = true;
		}else {
            end = false;
        }
        this.maze[col][row].setDiscovered(true);
	}

    /**
     * Méthode qui met à jour le labyrinthe 
     */
	@Override
	public void update(Subject subject, Object lastCoordinate) {
		 	Monster monster = (Monster) subject;
		 	Coordinate c = (Coordinate) lastCoordinate;
		 	int row = monster.getCoordinate().getRow();
		 	int col = monster.getCoordinate().getColumn();
		 	if(this.maze[col][row].getState().getCar() == CellInfo.EXIT.getCar()) {
		 		this.maze[monster.getCoordinate().getColumn()][monster.getCoordinate().getRow()].setState(CellInfo.MONSTER);
				end = true;
			}
	        this.maze[col][row].setState(CellInfo.MONSTER);
	        this.maze[c.getColumn()][c.getRow()].setState(CellInfo.EMPTY);
            this.maze[c.getColumn()][c.getRow()].setLastMonsterAppearance(compteur);
    }

    /**
     * Redéfinie l'attribut compteur représentant le nombre de tours de la partie.
     * @param compteur Le compteur représentant le nombre de tours de la partie.
     */
    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }
}
