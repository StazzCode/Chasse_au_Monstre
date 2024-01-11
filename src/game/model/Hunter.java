package game.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe Hunter représente le chasseur dans le jeu de chasse au monstre.
 * Elle hérite de la classe Player.Elle possède une liste de coordonnées de
 * coups réalisés avec succès.
 * Elle est utilisée pour suivre les coups réussis du chasseur et pour notifier
 * les observateurs lorsqu'un coup est réussi.
 */

public class Hunter extends Player {

    protected Maze maze;
    private ArrayList<Coordinate> hitsList;

    /**
     * Constructeur de la classe Hunter.
     * 
     * @param m le labyrinthe dans lequel le chasseur évolue.
     */
    public Hunter(Maze m) {
        this.attach(m);
        this.maze = m;
        this.hitsList = new ArrayList<>();
    }

    /**
     * Méthode qui ajoute une coordonnée à la liste des coups réussis et notifie les
     * observateurs.
     * 
     * @param c la coordonnée du coup réussi.
     */
    public void hit(Coordinate c) {
        hitsList.add(c);
        notifyObservers();
    }

    /**
     * Méthode qui retourne la liste des coordonnées des coups réussis.
     * 
     * @return la liste des coordonnées des coups réussis.
     */
    public ArrayList<Coordinate> getHitsList() {
        return this.hitsList;
    }

    /**
     * Méthode qui gère un tour de l'IA chasseur.
     * 
     * @return la liste des coordonnées des coups réussis.
     */
    public Coordinate AIplay() {
        Random rd = new Random();
        int i = rd.nextInt(maze.getColumns());
        int j = rd.nextInt(maze.getRows());
        return new Coordinate(i, j);
    }
}
