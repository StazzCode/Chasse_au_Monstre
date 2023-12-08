package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
     * @param nbObstacles nombre d'obstacles à générer.
     */
    public void generateObstacles(int nbObstacles) {
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
     * Méthode qui génère l'entrée et la sortie du labyrinthe.
     */
    public void generateEnterExit() {
        Random random = new Random();

        int rand = random.nextInt(4);

        if (rand == 0) {// TOP
            int col = random.nextInt(this.columns);
            this.maze[col][0] = new Cell(new Coordinate(col, 0), CellInfo.ENTER);
            this.monster.setMonsterPosition(new Coordinate(col, 0));
            col = random.nextInt(this.columns);
            this.maze[col][this.rows - 1] = new Cell(new Coordinate(col, this.rows - 1), CellInfo.EXIT);
        } else if (rand == 1) { // RIGHT
            int row = random.nextInt(this.rows);
            this.maze[this.columns - 1][row] = new Cell(new Coordinate(this.columns - 1, row), CellInfo.ENTER);
            this.monster.setMonsterPosition(new Coordinate(this.columns - 1, row));
            row = random.nextInt(this.columns);
            this.maze[0][row] = new Cell(new Coordinate(0, row), CellInfo.EXIT);
        } else if (rand == 2) { // BOTTOM
            int col = random.nextInt(this.columns);
            this.maze[col][this.rows - 1] = new Cell(new Coordinate(col, this.rows - 1), CellInfo.ENTER);
            this.monster.setMonsterPosition(new Coordinate(col, this.rows - 1));
            col = random.nextInt(this.rows);
            this.maze[col][0] = new Cell(new Coordinate(col, 0), CellInfo.EXIT);
        } else if (rand == 3) { // LEFT
            int row = random.nextInt(this.rows);
            this.maze[0][row] = new Cell(new Coordinate(0, row), CellInfo.ENTER);
            this.monster.setMonsterPosition(new Coordinate(0, row));
            row = random.nextInt(this.columns);
            this.maze[this.columns - 1][row] = new Cell(new Coordinate(this.columns - 1, row), CellInfo.EXIT);
        }
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
            if (current.equals(exit)) {
                return true;
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
        return false;
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

    public void genererLabyrinthe(int x, int y) {
        this.maze[y][x].setState(CellInfo.HOLE);
        int[] directions = { 0, 1, 2, 3 }; // 0: haut, 1: droite, 2: bas, 3: gauche
        shuffleArray(directions);

        for (int direction : directions) {
            int newX = x;
            int newY = y;

            switch (direction) {
                case 0: // Haut
                    newY -= 2;
                    break;
                case 1: // Droite
                    newX += 2;
                    break;
                case 2: // Bas
                    newY += 2;
                    break;
                case 3: // Gauche
                    newX -= 2;
                    break;
            }

            if (newX > 0 && newX < this.columns - 1 && newY > 0 && newY < this.rows - 1
                    && this.maze[newY][newX].getState().getCar() == CellInfo.WALL.getCar()) {
                this.maze[newY][newX].setState(CellInfo.HOLE);
                this.maze[y + (newY - y) / 2][x + (newX - x) / 2].setState(CellInfo.HOLE);
                genererLabyrinthe(newX, newY);
            }
        }
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}
