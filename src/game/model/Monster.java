package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * Cette classe représente le monstre dans le jeu. Elle contient des méthodes pour vérifier si le monstre peut se déplacer à une coordonnée donnée, pour déplacer le monstre à une coordonnée aléatoire parmi les coordonnées possibles et pour déplacer le monstre à la coordonnée donnée si c'est possible.
 * Elle étend la classe Player.
 */
public class Monster extends Player {

    protected Maze maze;
    ArrayList<Coordinate> deplacementPossible = new ArrayList<Coordinate>();
    Stack<Coordinate> stack = new Stack<>();
    List<Coordinate> marquer = new ArrayList<>(); 

    /**
     * Constructeur de la classe Monster.
     * @param m le labyrinthe dans lequel le monstre se déplace.
     */
    public Monster(Maze m){
        this.coordinate = new Coordinate(0, 0);
        this.attach(m);
        this.maze = m;
    }

    /**
     * Méthode qui vérifie si le monstre peut se déplacer à la coordonnée donnée.
     * @param c la coordonnée à vérifier.
     * @return true si le monstre peut se déplacer à la coordonnée donnée, false sinon.
     */
    public boolean canMove(Coordinate c){
        if (isCoordinateWrong(c) || isCoordinateDiagonally(c) || isDistanceMoreThan1(c)) return false;
        return maze.maze[c.getColumn()][c.getRow()].getState() != CellInfo.WALL;
    }

    /**
     * Méthode qui vérifie si le monstre peut se déplacer à la coordonnée donnée en diagonale.
     * @param c la coordonnée à vérifier
     * @return si le déplacement est possible en diagonale
     */
    public boolean canMoveWithDiagonals(Coordinate c){
        if (isCoordinateWrong(c) || isDistanceMoreThan1(c)) return false;
        return maze.maze[c.getColumn()][c.getRow()].getState() != CellInfo.WALL;
    }
    
    /**
     * Méthode qui mets à jour la position du monstre
     * @param c la nouvelle position du monstre
     */
    public void setMonsterPosition(Coordinate c) {
    	Coordinate previousCoordinate = this.coordinate;
        this.coordinate = c;
        this.notifyObservers(previousCoordinate);
    }


    /**
     * Méthode qui vérifie si la coordonnée donnée en paramètre n'est pas hors limite.
     * @param c la coordonnée à vérifier.
     * @return true si la coordonnée est inférieur à zéro ou si elle ne se situe pas dans le labyrinthe, false sinon.
     */
    private boolean isCoordinateWrong(Coordinate c) {
        return c.getColumn() < 0
                || c.getColumn() >= maze.getColumns()
                || c.getRow() < 0
                || c.getRow() >= maze.getRows();

    }

    /**
     * Méthode qui vérifie si la coordonnée donnée en paramètre est en diagonal au monstre.
     * @param c la coordonnée à vérifier.
     * @return true si la coordonnée se trouve en diagonale, false sinon.
     */
    private boolean isCoordinateDiagonally(Coordinate c){
        int column = this.getCoordinate().getColumn();
        int row = this.getCoordinate().getRow();

        return c.getColumn() == column - 1 && c.getRow() == row - 1
                || c.getColumn() == column + 1 && c.getRow() == row - 1
                || c.getColumn() == column - 1 && c.getRow() == row + 1
                || c.getColumn() == column + 1 && c.getRow() == row + 1;
    }

    /**
     * Méthode qui retourne true si la distance entre les coordonnées du monstre et la coordonnée 'c' est supérieure à 1.
     * @param c La coordonnée c.
     * @return true si la distance est supérieure à 1, false sinon.
     */
    private boolean isDistanceMoreThan1(Coordinate c){
        double x1 = coordinate.getColumn();
        double x2 = c.getColumn();
        double y1 = coordinate.getRow();
        double y2 = c.getRow();
        return Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2)) > 1;
    }

    /**
     * Méthode qui déplace le monstre à une coordonnée aléatoire parmi les coordonnées possibles.
     * @param deplacementPossible la liste des coordonnées possibles.
     */
    public void deplacement(ArrayList<Coordinate> deplacementPossible){
        int random = (int) (Math.random() * deplacementPossible.size());
        move(deplacementPossible.get(random));
    }

    /**
     * Méthode qui déplace le monstre à la coordonnée donnée si c'est possible.
     * @param c la coordonnée à laquelle le monstre doit se déplacer.
     */
    public boolean move(Coordinate c){
    	if(canMove(c)) {
    		Coordinate previousCoordinate = this.coordinate;
            this.coordinate = c;
            this.notifyObservers(previousCoordinate);
            return true;
    	}
        return false;
    }
    
    /**
     * Méthode qui gère un tour de l'IA monstre.
     * @return la coordonnée à laquelle le monstre doit se déplacer.
     */
    public Coordinate iaMove() {
        if (stack.isEmpty()) {
            initializeStack();
        }

        while (!stack.isEmpty()) {
            Coordinate c = stack.peek();

            if (this.maze.getMaze()[c.getColumn()][c.getRow()].getState().getCar() == CellInfo.EXIT.getCar()) {
                return new Coordinate(0, 0);
            } else {
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    for (int yOffset = -1; yOffset <= 1; yOffset++) {
                        if ((xOffset == 0 || yOffset == 0) && isValidMove(c.getColumn() + xOffset, c.getRow() + yOffset)) {
                            this.marquer.add(this.maze.getMaze()[c.getColumn() + xOffset][c.getRow() + yOffset].getCoordinate());
                            stack.add(this.maze.getMaze()[c.getColumn() + xOffset][c.getRow() + yOffset].getCoordinate());
                            return new Coordinate(xOffset, yOffset);
                        }
                    }
                }

                int colonne = c.getColumn();
                int row = c.getRow();
                stack.pop();
                if (!stack.isEmpty()) {
                    c = stack.peek();
                    return new Coordinate(c.getColumn() - colonne, c.getRow() - row);
                }
            }
        }

        return null;
    }

    /**
     * Méthode qui initialise la pile de coordonnées.
     */
    private void initializeStack() {
        stack.push(this.maze.getEnter());
        marquer.add(this.maze.getEnter());
    }

    /**
     * Méthode qui vérifie si le déplacement à la coordonnée donnée est possible.
     * @param newColumn la colonne de la coordonnée.
     * @param newRow la ligne de la coordonnée.
     * @return true si le déplacement est possible, false sinon.    
     */
    private boolean isValidMove(int newColumn, int newRow) {
        return newColumn >= 0 && newColumn < this.maze.getColumns()
                && newRow >= 0 && newRow < this.maze.getRows()
                && this.maze.getMaze()[newColumn][newRow].getState().getCar() != CellInfo.WALL.getCar()
                && !this.marquer.contains(this.maze.getMaze()[newColumn][newRow].getCoordinate());
    }
    
    /**
     * Méthode qui déplace le monstre en diagonale à la coordonnée donnée si c'est possible
     * @param c la coordonnée à laquelle le monstre doit se déplacer
     * @return si le déplacement est possible
     */
    public boolean moveWithDiagonals(Coordinate c){
        if(canMoveWithDiagonals(c)) {
            Coordinate previousCoordinate = this.coordinate;
            this.coordinate = c;
            this.notifyObservers(previousCoordinate);
            return true;
        }
        return false;
    }

    /**
     * Méthode qui retourne si le monstre est à proximité de la coordonnée donnée
     * @return si le monstre est à proximité de la coordonnée donnée
     */
    public boolean near(int i, int j) {
        Coordinate c = this.getCoordinate();
        if (i<c.getColumn()-2 || i>c.getColumn()+2 || j<c.getRow()-2 || j>c.getRow()+2) return false;
        return true;
    }
}
