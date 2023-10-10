package menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageView logo = new ImageView(new Image(getClass().getResource("logo.png").toExternalForm()));
        VBox.setMargin(logo, new Insets(50, 0, 0, 0));

        Button play = new Button("Jouer");
        VBox.setMargin(play, new Insets(80, 0, 20, 0));
        play.setPrefSize(360, 60);
        play.getStyleClass().add("playButton");
        
        Button settings = new Button("Options");
        VBox.setMargin(settings, new Insets(20, 0, 0, 0));
        settings.setPrefWidth(230);

        Button howToPlay = new  Button("Comment jouer ?");
        VBox.setMargin(howToPlay, new Insets(20, 0, 0, 0));
        howToPlay.setPrefWidth(230);

        VBox root = new VBox(logo,play,settings,howToPlay);
        root.getStyleClass().add("root");

        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("background.gif")));
        background.setFitHeight(500);
        background.setPreserveRatio(true);
        
        StackPane stackPane = new StackPane(background, root);

        Scene scene = new Scene(stackPane, 820, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}