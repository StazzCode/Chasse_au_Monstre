package moteur;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHM extends Application {

    Button selected = new Button("");

    @Override
    public void start(Stage stage) {
        int columns = 5; int rows = 5;
        GridPane grid = new GridPane();
        for(int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                Button b = new Button(i+":"+j);
                b.setBackground(Background.EMPTY);
                b.setBorder(new Border(new BorderStroke(Color.BLACK, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                b.setOnMouseClicked(e -> {
                    this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    this.selected = b;
                    b.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                });
                grid.add(b, i, j);
            }
        }
        grid.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(grid);
        Button play = new Button("play");
        stackPane.getChildren().add(play);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(stackPane, 280, 240);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                selected.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}