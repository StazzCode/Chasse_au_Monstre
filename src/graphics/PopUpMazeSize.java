package graphics;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

public class PopUpMazeSize extends Application {

    private static int MINSIZE;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        // Créer une étiquette avec le message
        Label messageLabel = new Label("Taille inférieur à : " + MINSIZE);

        // Créer un conteneur StackPane pour la mise en page
        StackPane layout = new StackPane();
        layout.getChildren().add(messageLabel);

        // Créer une nouvelle scène
        Scene scene = new Scene(layout, 300, 200);

        // Créer une nouvelle fenêtre modale (popup)
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("");

        int ratio = 6;
        popupStage.setMinWidth(primaryStage.getWidth()/ratio);
        popupStage.setMaxWidth(primaryStage.getWidth()/ratio);
        popupStage.setMinHeight(primaryStage.getHeight()/ratio);
        popupStage.setMaxHeight(primaryStage.getHeight()/ratio);

        // Affecter la scène à la fenêtre popup
        popupStage.setScene(scene);

        // Afficher la fenêtre popup
        popupStage.show();

        // Centrer la fenêtre popup par rapport à la fenêtre principale
        popupStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - popupStage.getWidth() / 2);
        popupStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - popupStage.getHeight() / 2);

        // Créer une transition de pause de 2 secondes
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            // Ferme la popup après la pause
            popupStage.close();
        });

        // Démarrer la transition de pause
        pause.play();

    }

    public void setMINSIZE(int MINSIZE) {
        PopUpMazeSize.MINSIZE = MINSIZE;
    }
}
