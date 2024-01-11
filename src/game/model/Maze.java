package game.model;

import java.util.Arrays;
import java.util.PriorityQueue;
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
     * 
     * @return le chasseur du labyrinthe
     */
    public Hunter getHunter() {
        return this.hunter;
    }

    /**
     * Retourne l'arrivée du labyrinthe
     * 
     * @return l'arrivée du labyrinthe
     */
    public boolean getEnd() {
        return this.end;
    }

    /**
     * Retourne une version booléenne du labyrinthe codant true pour un mur et false
     * pour autre chose.
     */
    public boolean[][] getBooleanMaze() {
        int columns = this.getColumns();
        int rows = this.getRows();
        boolean[][] bMaze = new boolean[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (this.getMaze()[i][j].getState().equals(CellInfo.WALL)) {
                    bMaze[i][j] = true;
                } else {
                    bMaze[i][j] = false;
                }
            }
        }
        return bMaze;
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
        maze[this.monster.getCoordinate().getColumn()][this.monster.getCoordinate().getRow()]
                .setState(CellInfo.MONSTER);
    }

    /**
     * Méthode qui génère un nombre d'obstacles spécifié
     * 
     * @param obstaclePercentage nombre d'obstacles à générer.
     */
    public void generateObstacles(int obstaclePercentage) {
        if (obstaclePercentage < 0 || obstaclePercentage > 100) {
            throw new IllegalArgumentException("Le pourcentage d'obstacles doit être compris entre 0 et 100.");
        }

        int nbObstacles = (int) Math.round((obstaclePercentage / 100.0) * this.getMaxObstaclePossible());

        Random random = new Random();
        int countObstacles = 0;

        while (countObstacles != nbObstacles) {
            int x = random.nextInt(columns);
            int y = random.nextInt(rows);

            if (this.maze[x][y].getState() == CellInfo.EMPTY) {
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
     */
    public void generateMazeObstacles(int columns, int rows) {
        int nbObstacles = (columns * rows) / 4;

        generateObstacles(nbObstacles);

    }

    /**
     * Méthode qui retourne le nombre d'obstacles maximum possible dans le
     * labyrinthe
     * 
     * @return le nombre d'obstacles maximum possible dans le labyrinthe
     */
    public int getMaxObstaclePossible() {
        int size = this.getColumns() * this.getRows() - 2;
        int manathan = (this.getColumns() - 1) + (this.getRows() - 1) - 1;
        return size - manathan;
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
     * Méthode qui génère l'entrée et la sortie du labyrinthe.
     */
    public void generateEnterExit() {
        Random random = new Random();
        int rand = random.nextInt(4);

        int colEntrance, rowEntrance, colExit, rowExit;

        if (rand == 0) { // TOP
            colEntrance = random.nextInt(this.columns);
            rowEntrance = 0;
            colExit = random.nextInt(this.columns);
            rowExit = this.rows - 1;
        } else if (rand == 1) { // RIGHT
            colEntrance = this.columns - 1;
            rowEntrance = random.nextInt(this.rows);
            colExit = 0;
            rowExit = random.nextInt(this.rows);
        } else if (rand == 2) { // BOTTOM
            colEntrance = random.nextInt(this.columns);
            rowEntrance = this.rows - 1;
            colExit = random.nextInt(this.columns);
            rowExit = 0;
        } else { // LEFT
            colEntrance = 0;
            rowEntrance = random.nextInt(this.rows);
            colExit = this.columns - 1;
            rowExit = random.nextInt(this.rows);
        }

        placeEntranceExit(colEntrance, rowEntrance, colExit, rowExit);

    }

    /**
     * Méthode qui place l'entrée et la sortie du labyrinthe.
     * 
     * @param colEntrance
     * @param rowEntrance
     * @param colExit
     * @param rowExit
     */
    private void placeEntranceExit(int colEntrance, int rowEntrance, int colExit, int rowExit) {
        this.monster.setMonsterPosition(new Coordinate(colEntrance, rowEntrance));
        this.maze[colEntrance][rowEntrance] = new Cell(new Coordinate(colEntrance, rowEntrance), CellInfo.ENTER);
        this.maze[colExit][rowExit] = new Cell(new Coordinate(colExit, rowExit), CellInfo.EXIT);
    }

    /**
     * Méthode qui vérifie si une case a des voisins
     * 
     * @return true si la case a des voisins, false sinon
     */
    public boolean hasNeighbors(Coordinate coord) {
        int x = coord.getColumn();
        int y = coord.getRow();

        boolean rightNeighbor = x < this.columns - 1 && this.maze[x + 1][y].getState() == CellInfo.EMPTY;
        boolean downNeighbor = y < this.rows - 1 && this.maze[x][y + 1].getState() == CellInfo.EMPTY;
        boolean leftNeighbor = x > 0 && this.maze[x - 1][y].getState() == CellInfo.EMPTY;
        boolean upNeighbor = y > 0 && this.maze[x][y - 1].getState() == CellInfo.EMPTY;

        return rightNeighbor || downNeighbor || leftNeighbor || upNeighbor;
    }

    /**
     * Méthode qui retourne les coordonnées de l'entrée du labyrinthe
     * 
     * @return les coordonnées de l'entrée du labyrinthe
     */
    public Coordinate getEnter() {
        Coordinate enter = new Coordinate(0, 0);
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (this.maze[i][j].getState().getCar() == CellInfo.ENTER.getCar()) {
                    enter = new Coordinate(i, j);
                }
            }
        }
        return enter;
    }

    /**
     * Méthode qui retourne les coordonnées de la sortie du labyrinthe
     * 
     * @return les coordonnées de la sortie du labyrinthe
     */
    public Coordinate getExit() {
        Coordinate exit = new Coordinate(0, 0);
        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                if (this.maze[i][j].getState().getCar() == CellInfo.EXIT.getCar()) {
                    exit = new Coordinate(i, j);
                }
            }
        }
        return exit;
    }

    /**
     * Méthode qui permet de vérifier s'il existe un chemin entre l'entrée et la
     * sortie
     */
    public boolean checkPathExists() {
        Coordinate enter = getEnter();
        Coordinate exit = getExit();

        if (enter == null || exit == null) {
            return false;
        }

        int[][] distances = new int[columns][rows];
        for (int i = 0; i < columns; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Coordinate> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(distances[a.getColumn()][a.getRow()], distances[b.getColumn()][b.getRow()]));

        distances[enter.getColumn()][enter.getRow()] = 0;
        queue.add(enter);

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (this.maze[current.getColumn()][current.getRow()].getState().getCar() == CellInfo.EXIT.getCar()) {
                return true; // Chemin trouvé
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current.getColumn() + dx[i];
                int nextY = current.getRow() + dy[i];

                if (nextX >= 0 && nextX < columns && nextY >= 0 && nextY < rows) {
                    if (maze[nextX][nextY].getState() != CellInfo.WALL
                            && distances[nextX][nextY] == Integer.MAX_VALUE) {
                        distances[nextX][nextY] = distances[current.getColumn()][current.getRow()] + 1;
                        queue.add(new Coordinate(nextX, nextY));
                    }
                }
            }
        }
        return false; // Aucun chemin trouvé
    }

    /**
<<<<<<< HEAD
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

    public Coordinate findShortedLastAppearance(){
        Coordinate shorted = new Coordinate(0, 0);
        for(int i = 0; i < this.getColumns(); i++){
            for(int j = 0; j  < this.getRows(); j++){
                if(this.maze[i][j].getLastMonsterAppearanceReverse(this.compteur) < this.maze[shorted.getColumn()][shorted.getRow()].getLastMonsterAppearanceReverse(this.compteur) &&
                        this.maze[i][j].getLastMonsterAppearanceReverse(this.compteur) > -1 && this.maze[i][j].isDiscovered()){
                    shorted =  this.maze[i][j].getCoordinate();
                }
            }
        }
        if(shorted.getColumn() == 0 && shorted.getRow() == 0){
            return null;
        }
        return shorted;
    }

    /**
=======
>>>>>>> 8d948fb819c270c7b15d479309f32308bea4b736
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
        int row = this.hunter.getHitsList().get(this.hunter.getHitsList().size() - 1).getRow();
        int col = this.hunter.getHitsList().get(this.hunter.getHitsList().size() - 1).getColumn();
        if (this.maze[col][row].getState().getCar() == CellInfo.MONSTER.getCar()) {
            end = true;
        } else {
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
        if (this.maze[col][row].getState().getCar() == CellInfo.EXIT.getCar()) {
            this.maze[monster.getCoordinate().getColumn()][monster.getCoordinate().getRow()].setState(CellInfo.MONSTER);
            end = true;
        }
        this.maze[col][row].setState(CellInfo.MONSTER);
        this.maze[c.getColumn()][c.getRow()].setState(CellInfo.EMPTY);

        int lastAppearance = this.maze[c.getColumn()][c.getRow()].getLastMonsterAppearance();
        if (lastAppearance == -1) {
            this.maze[c.getColumn()][c.getRow()].setLastMonsterAppearance(compteur);
            this.maze[c.getColumn()][c.getRow()].setPreviousLastMonsterAppearance(compteur);
        } else {
            this.maze[c.getColumn()][c.getRow()].setPreviousLastMonsterAppearance(lastAppearance);
            this.maze[c.getColumn()][c.getRow()].setLastMonsterAppearance(compteur);
        }
    }

    /**
     * Redéfinie l'attribut compteur représentant le nombre de tours de la partie.
     * 
     * @param compteur Le compteur représentant le nombre de tours de la partie.
     */
    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }
}
