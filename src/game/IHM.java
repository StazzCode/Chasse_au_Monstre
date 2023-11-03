package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    GridPane grid;
    Maze maze;
    Button selected = new Button("");
    int turn;
    Label play;
    Button shoot;
    Scene scene;

    public void displayMaze() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i*maze.getColumns()+j);
                if (maze.getMaze()[i][j].getState().equals(CellInfo.EMPTY)) {
                    b.setText("    ");
                } else {
                    b.setText(Character.toString(maze.getMaze()[i][j].getState().getCar()));
                }
            }
        }
    }

    public void monsterPlay() {
        this.displayMaze();
        play.setText("Tour "+turn+" : Chasseur   |   Choisissez un emplacement où tirer en cliquant.");
        shoot.setVisible(true);

        scene.setOnKeyPressed(null);

        for (int i=0; i<grid.getChildren().size(); i++) {
            Button b = (Button) grid.getChildren().get(i);
            b.setOnMouseClicked(e -> {
                this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                this.selected = b;
                b.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            });
        }
    }

    public void hunterPlay() {
        this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.selected = new Button("");
        turn++;
        shoot.setVisible(false);
        play.setText("Tour "+turn+" : Monstre   |   Utilisez ZQSD pour vous déplacer.");

        for (int i=0; i<grid.getChildren().size(); i++) {
            Button b = (Button) grid.getChildren().get(i);
            b.setOnMouseClicked(null);
        }

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn(), maze.getMonster().getCoordinate().getRow()-1));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.Q) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn()-1, maze.getMonster().getCoordinate().getRow()));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.S) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn(), maze.getMonster().getCoordinate().getRow()+1));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.D) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn()+1, maze.getMonster().getCoordinate().getRow()));
                monsterPlay();
            }
        });
    }

    @Override
    public void start(Stage stage) {
        int columns = 10; int rows = 10;
        turn = 0;

        this.maze = new Maze(columns, rows);
        maze.resetMaze();
        maze.generateObstacles();
        maze.generateEnterExit();
        
        this.grid = new GridPane();
        Button example = new Button("M");
        for(int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                Button b = new Button(i+":"+j);
                b.setMinWidth(example.getWidth());
                b.setMinHeight(example.getHeight());
                b.setBackground(Background.EMPTY);
                b.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                grid.add(b, i, j);
            }
        }
        grid.setAlignment(Pos.CENTER);
        
        StackPane stackPane = new StackPane(grid);
        play = new Label("Tour "+turn+" : Monstre   |   Utilisez ZQSD pour vous déplacer.");
        stackPane.getChildren().add(play);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);

        shoot = new Button("Shoot !");
        stackPane.getChildren().add(shoot);
        StackPane.setAlignment(shoot, Pos.TOP_CENTER);
        shoot.setVisible(false);
        shoot.setOnMouseClicked(e -> {
            hunterPlay();
        });

        
        scene = new Scene(stackPane, 400, 400);
        
        this.displayMaze();
        hunterPlay();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}