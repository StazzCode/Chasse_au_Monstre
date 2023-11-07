package game;
<<<<<<< HEAD
=======

>>>>>>> b4f53993cf02a6d4b008c09f1c25d0c8ec016238
import utils.Subject;

/**
 * La classe Player est une classe abstraite représentant un joueur dans le jeu.
 * Elle hérite de la classe Subject pour pouvoir notifier les observateurs
 * en cas de changement d'état.
 */
public abstract class Player extends Subject {

    /**
     * Coordonnées du joueur.
     */
    protected Coordinate coordinate;

    /**
     * Retourne les coordonnées du joueur.
     * @return les coordonnées du joueur.
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Modifie les coordonnées du joueur.
     * @param coordinate les coordonnées du joueur.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /*
     * public boolean canPlay(){
     * 
     * }
     * 
     * public void play(){
     * 
     * }
     * }
     * 
     * public void update(ICellEvent);
     * 
     * 
     */
}
