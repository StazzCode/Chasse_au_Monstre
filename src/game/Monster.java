package game;

import java.util.ArrayList;
/**
 * Cette classe représente le monstre dans le jeu. Elle contient des méthodes pour vérifier si le monstre peut se déplacer à une coordonnée donnée, pour déplacer le monstre à une coordonnée aléatoire parmi les coordonnées possibles et pour déplacer le monstre à la coordonnée donnée si c'est possible.
 * Elle étend la classe Player.
 */
public class Monster extends Player {

    protected Maze maze;
    ArrayList<Coordinate> deplacementPossible = new ArrayList<Coordinate>();

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
        if (c.getColumn()<0 || c.getColumn()>= maze.getColumns() || c.getRow()<0 || c.getRow()>= maze.getRows()) {
            return false;
        }
        return maze.maze[c.getColumn()][c.getRow()].getState() != CellInfo.WALL;
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
    public void move(Coordinate c){
    	if(canMove(c)) {
    		Coordinate previousCoordinate = this.coordinate;
            this.coordinate = c;
            this.notifyObservers(previousCoordinate);
    	}
    }

    
}
