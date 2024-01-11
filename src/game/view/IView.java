package game.view;

/**
 * Interface IView dont vont hériter les vues du chasseur et du monstre.
 */
public interface IView {
    /**
     * Méthode qui affiche le labyrinthe par la vue en question.
     */
    public void display();

    /**
     * Méthode qui active et désactive les interactions du type de joueur en question.
     * 
     * @param active booléen pour activer ou désactiver
     */
    public void setInteractions(boolean active);

    /**
     * Méthode qui gère le tour du type de joueur en question.
     */
    public void play();

    /**
     * Méthode qui définit sur la partie est terminée.
     */
    public void endGame();

    /**
     * Méthode qui permet de fermer la fenêtre
     */
    public void close();
}
