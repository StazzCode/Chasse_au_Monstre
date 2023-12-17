package graphics;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * La classe PopUpPane représente un panneau de pop-up qui affiche une image avec une animation.
 * Elle hérite de la classe StackPane.
 */
public class PopUpPane extends StackPane{
    private SequentialTransition animation;

    /**
     * Constructeur de la classe PopUpPane.
     * Crée un panneau de pop-up avec une durée d'animation donnée et une image spécifiée par son URL.
     *
     * @param millis la durée de l'animation en millisecondes
     * @param img l'URL de l'image à afficher
     */
    private PopUpPane(double millis, String img){
        ImageView image = new ImageView(new Image(getClass().getResource(img).toExternalForm()));
        image.setPreserveRatio(true);

        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(millis/4), image);
        scaleAnimation.setFromX(0.01);
        scaleAnimation.setFromY(0.01);
        scaleAnimation.setToX(1);
        scaleAnimation.setToY(1);

        ScaleTransition pause = new ScaleTransition(Duration.millis((millis/4)*3), image);
        pause.setFromX(1);
        pause.setToX(1.2);
        pause.setFromY(1);
        pause.setToY(1.2);

        animation = new SequentialTransition(scaleAnimation,pause);

        setStyle(
            "-fx-background-color:transparent;"
        );
        getChildren().add(image);
    }

    /**
     * Redéfinition de la méthode setWidth de la classe StackPane.
     * Définit la largeur du panneau et ajuste la largeur de l'image en conséquence.
     *
     * @param w la largeur du panneau
     */
    @Override
    public void setWidth(double w){
        super.setWidth(w);
        ((ImageView)getChildren().get(0)).setFitWidth(w);
    }

    /**
     * Redéfinition de la méthode setHeight de la classe StackPane.
     * Définit la hauteur du panneau et ajuste la hauteur de l'image en conséquence.
     *
     * @param h la hauteur du panneau
     */
    @Override
    public void setHeight(double h){
        super.setHeight(h);
        ((ImageView)getChildren().get(0)).setFitHeight(h);
    }

    /**
     * Définit l'événement à exécuter lorsque l'animation est terminée.
     *
     * @param var1 l'événement à exécuter
     */
    public final void setOnFinished(EventHandler<ActionEvent> var1) {
      animation.setOnFinished(var1);
    }

    /**
     * Lance l'animation du panneau de pop-up.
     */
    public void play(){
        animation.play();
    }

    /**
     * Crée et retourne un panneau de pop-up pour la fin du jeu avec une durée d'animation par défaut.
     *
     * @return le panneau de pop-up pour la fin du jeu
     */
    public static PopUpPane getGameOverPane(){
        return getGameOverPane(1000);
    }

    /**
     * Crée et retourne un panneau de pop-up pour la fin du jeu avec une durée d'animation spécifiée.
     *
     * @param millis la durée de l'animation en millisecondes
     * @return le panneau de pop-up pour la fin du jeu
     */
    public static PopUpPane getGameOverPane(double millis){
        return new PopUpPane(millis, "img/gameOver.png");
    }

}
