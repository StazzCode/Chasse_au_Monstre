package game;

import javafx.animation.PauseTransition;
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
     * Le label affichant des retours en fonction des actions des joueurs.
     */
    Label response;

    /**
     * Le bouton permettant au joueur de tirer sur le monstre.
     */
    Button shoot;

    /**
     * La scène principale de l'interface graphique.
     */
    Scene scene;
    
    int shootColumn;
    int shootRow;

    /**
     * Méthode qui affiche le labyrinthe sur la grille.
     */
    public void displayMaze() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                b.setText(Character.toString(maze.getMaze()[i][j].getState().getCar()));
            }
        }
    }

    public String displayCell(Cell c){
        if (c.getState() == CellInfo.EMPTY && c.getLastMonsterAppearance() > 0){
            return String.valueOf(c.getLastMonsterAppearance());
        }
        return Character.toString(c.getState().getCar());
    }

    public void displayHunterView() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                b.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                if (maze.getMaze()[i][j].isDiscovered()) {
                    b.setText(displayCell(maze.getMaze()[i][j]));
                } else {
                    b.setText(" ");
                }
            }
        }
    }

    public void displayMonsterView() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                if (maze.getMonster().near(i, j)) {
                    b.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
                    b.setText(Character.toString(maze.getMaze()[i][j].getState().getCar()));
                } else {
                    b.setText(" ");
                }
            }
        }
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }

    /**
     * Méthode qui gère le tour du monstre.
     */
    public void monsterPlay() {
        IHM.sleep(500);
    	this.displayHunterView();
    	if(maze.getEnd()) {
    		play.setText("Partie terminée. Le Monstre a gagné.");
    		setMonsterInteractions(false);
            return;
    	}
        
        play.setText("Tour " + turn + " : Chasseur   |   Choisissez un emplacement où tirer en cliquant.");
        
        setMonsterInteractions(false);

        setHunterInteractions(true);
    }

    /**
     * Méthode qui gère le tour du chasseur.
     */
    public void hunterPlay() {
        IHM.sleep(500);
        this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.selected = new Button("");
        turn++;
        maze.setCompteur(turn);
        this.displayMonsterView();
        
        play.setText("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");

        setHunterInteractions(false);

        setMonsterInteractions(true);
    }

    public void setMonsterInteractions(boolean active) {
        if (active) {
            scene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.Z) {
                    setMonsterMovementKeybind(0, -1);
                }
                if (e.getCode() == KeyCode.Q) {
                    setMonsterMovementKeybind(-1, 0);
                }
                if (e.getCode() == KeyCode.S) {
                    setMonsterMovementKeybind(0, 1);
                }
                if (e.getCode() == KeyCode.D) {
                    setMonsterMovementKeybind(1, 0);
                }
            });
        } else {
            scene.setOnKeyPressed(null);
        }
    }

    private void setMonsterMovementKeybind(int columnMovement, int rowMovement) {
        if (maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn() + columnMovement,
                maze.getMonster().getCoordinate().getRow() + rowMovement))) {
                    monsterPlay();
                } else {
                    response.setText("Mouvement impossible ! Réessayez.");
                }
    }

    public void setHunterInteractions(boolean active) {
        if (active) {
            response.setText("");
            shoot.setVisible(true);
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
                    	shootColumn = col.intValue();
                        shootRow = row.intValue();
                        //maze.getHunter().hit(new Coordinate(column, rowValue));
                    }
                });
            }
        } else {
            shoot.setVisible(false);
            for (int i = 0; i < grid.getChildren().size(); i++) {
                Button b = (Button) grid.getChildren().get(i);
                b.setOnMouseClicked(null);
            }
        }
    }
    
    private void initializeGrid(int columns, int rows, int elementSize) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Button b = new Button(i + ":" + j);
                b.setMinWidth(elementSize);
                b.setMaxWidth(elementSize);
                b.setMinHeight(elementSize);
                b.setMaxHeight(elementSize);
                b.setBackground(Background.EMPTY);
                b.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                grid.add(b, i, j);
            }
        }
    }
    
    private void initializeShootButton() {
        shoot = new Button("Shoot !");
        shoot.setOnMouseClicked(e -> {
            this.maze.getHunter().hit(new Coordinate(shootColumn, shootRow));
            if (!maze.getEnd()) {
                Integer col = GridPane.getColumnIndex(this.selected);
                Integer row = GridPane.getRowIndex(this.selected);
                if (col != null && row != null) {
                    int column = col.intValue();
                    int rowValue = row.intValue();
                    maze.getMaze()[column][rowValue].discover();
                    this.response.setText("Vous avez tiré sur " + maze.getMaze()[column][rowValue].getState().toString() + "!");
                }
                hunterPlay();
            } else {
                play.setText("Partie terminée. Le chasseur a gagné.");
                shoot.setVisible(false);
                this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                this.selected = new Button("");
                for (int i = 0; i < grid.getChildren().size(); i++) {
                    Button b = (Button) grid.getChildren().get(i);
                    b.setOnMouseClicked(null);
                }
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
        int elementSize = 40;
        initializeGrid(columns, rows, elementSize);
        grid.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(grid);
        play = new Label("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");
        stackPane.getChildren().add(play);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);

        response = new Label("");
        stackPane.getChildren().add(response);
        StackPane.setAlignment(response, Pos.TOP_CENTER);

        initializeShootButton();
        stackPane.getChildren().add(shoot);
        StackPane.setAlignment(shoot, Pos.TOP_CENTER);
        shoot.setVisible(false);

        scene = new Scene(stackPane, 500, 500);
        //this.displayMonsterView();
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