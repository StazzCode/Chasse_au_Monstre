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

/**
 * Cette classe représente l'interface graphique du jeu. Elle hérite de la
 * classe Application de JavaFX.
 */
public class IHM extends Application {

    /**
     * Le plateau de jeu représenté sous forme de grille.
     */
    GridPane grid;

    /**
     * Le labyrinthe du jeu.
     */
    Maze maze;

    /**
     * Le bouton sélectionné par le joueur.
     */
    Button selected = new Button("");

    /**
     * Le numéro du tour actuel.
     */
    int turn;

    /**
     * Le label affichant le tour actuel et les instructions pour le joueur.
     */
    Label play;

    /**
     * Le bouton permettant au joueur de tirer sur le monstre.
     */
    Button shoot;

    /**
     * La scène principale de l'interface graphique.
     */
    Scene scene;

    /**
     * Méthode qui affiche le labyrinthe sur la grille.
     */
    public void displayMaze() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                if (maze.getMaze()[i][j].getState().equals(CellInfo.EMPTY)) {
                    b.setText("    ");
                } else {
                    b.setText(Character.toString(maze.getMaze()[i][j].getState().getCar()));
                }
            }
        }
    }

    /**
     * Méthode qui gère le tour du monstre.
     */
    public void monsterPlay() {
        this.displayMaze();
        play.setText("Tour " + turn + " : Chasseur   |   Choisissez un emplacement où tirer en cliquant.");
        shoot.setVisible(true);

        scene.setOnKeyPressed(null);

        for (int i = 0; i < grid.getChildren().size(); i++) {
            Button b = (Button) grid.getChildren().get(i);
            b.setOnMouseClicked(e -> {
                this.selected.setBackground(
                        new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                this.selected = b;
                b.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                Integer col = GridPane.getColumnIndex(b);
                Integer row = GridPane.getRowIndex(b);
                if (col != null && row != null) {
                    int column = col.intValue();
                    int rowValue = row.intValue();
                    maze.getHunter().hit(new Coordinate(column, rowValue));
                }
            });
        }
    }

    /**
     * Méthode qui gère le tour du chasseur.
     */
    public void hunterPlay() {
        this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.selected = new Button("");
        turn++;
        shoot.setVisible(false);
        play.setText("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");

        for (int i = 0; i < grid.getChildren().size(); i++) {
            Button b = (Button) grid.getChildren().get(i);
            b.setOnMouseClicked(null);
        }

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn(),
                        maze.getMonster().getCoordinate().getRow() - 1));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.Q) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn() - 1,
                        maze.getMonster().getCoordinate().getRow()));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.S) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn(),
                        maze.getMonster().getCoordinate().getRow() + 1));
                monsterPlay();
            }
            if (e.getCode() == KeyCode.D) {
                maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn() + 1,
                        maze.getMonster().getCoordinate().getRow()));
                monsterPlay();
            }
        });
    }

    /**
     * Méthode qui initialise l'interface graphique.
     * 
     * @param stage la scène principale de l'interface graphique.
     */
    @Override
    public void start(Stage stage) {
        int columns = 10;
        int rows = 10;
        turn = 0;

        this.maze = new Maze(columns, rows);
        maze.resetMaze();
        maze.generateObstacles();
        maze.generateEnterExit();

        this.grid = new GridPane();
        Button example = new Button("M");
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Button b = new Button(i + ":" + j);
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
        play = new Label("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");
        stackPane.getChildren().add(play);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);

        shoot = new Button("Shoot !");
        stackPane.getChildren().add(shoot);
        StackPane.setAlignment(shoot, Pos.TOP_CENTER);
        shoot.setVisible(false);
        shoot.setOnMouseClicked(e -> {
            if (!maze.getEnd()) {
                hunterPlay();
            } else {
                play.setText("fin du jeu");
            }

        });

        scene = new Scene(stackPane, 400, 400);
        this.displayMaze();
        hunterPlay();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode main qui lance l'application.
     */
    public static void main(String[] args) {
        launch();
    }

}