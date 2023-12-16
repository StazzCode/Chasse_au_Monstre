package game.view;

import game.model.Cell;
import game.model.CellInfo;
import game.model.Coordinate;
import game.model.Maze;
import graphics.PopUpPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import menu.MainMenu;
import java.util.Random;

public class HunterView extends Stage implements IView {

    IHM ihm;

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
     * La StackPane principale contenant l'affichage du labyrinthe et des
     * informations de jeu.
     */
    StackPane stackPane;

    /**
     * La scène principale de l'interface graphique.
     */
    Scene scene;

    /**
     * Le Stage principal de l'interface graphique.
     */
    Stage mainStage;

    int shootColumn;
    int shootRow;

    public HunterView(IHM ihm, Stage mainStage, Maze maze) {
        this.ihm = ihm;
        this.mainStage = mainStage;
        this.maze = maze;
        this.start();
    }

    /**
     * Méthode qui affiche le labyrinthe vu par le chasseur
     */
    public void display() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                b.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                if (maze.getMaze()[i][j].isDiscovered()) {
                    b.setText(displayCellExceptMonster(maze.getMaze()[i][j]));
                } else {
                    b.setText(" ");
                }
            }
        }
    }

    /**
     * Méthode qui retourne le caractère correspondant au type de cellule sauf si
     * c'est une cellule vide avec une dernière apparition du monstre
     * 
     * @param c la cellule choisie
     * @return le caractère correspondant au type de cellule sauf si c'est une
     *         cellule vide avec une dernière apparition du monstre
     */
    public String displayCellExceptMonster(Cell c) {
        if (c.getState() == CellInfo.EMPTY && c.getLastMonsterAppearance() > 0) {
            return String.valueOf(c.getLastMonsterAppearance());
        } else if (c.getState() != CellInfo.MONSTER) {
            return Character.toString(c.getState().getCar());
        } else if (c.getState() == CellInfo.MONSTER) {
            if (c.getPreviousLastMonsterAppearance() < 0) {
                return " ";
            } else {
                return String.valueOf(c.getPreviousLastMonsterAppearance());
            }
        }
        return " ";
    }

    /**
     * Méthode qui gère les interactions du chasseur avec le clavier.
     * 
     * @param active l'intéraction utilisée
     */
    public void setInteractions(boolean active) {
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

    /**
     * Méthode qui fait passer le tour du monstre.
     */
    public void play() {
        this.display();
        turn++;
        play.setText("Tour " + turn + " : Chasseur   |   Choisissez un emplacement où tirer en cliquant.");
        setInteractions(true);
    }

    void gameOverPopUp() {
        PopUpPane gameOverPopUp = PopUpPane.getGameOverPane();
        stackPane.getChildren().add(stackPane.getChildren().size() - 1, gameOverPopUp);
        gameOverPopUp.setOnFinished(e -> {
            stackPane.getChildren().remove(gameOverPopUp);
        });
        gameOverPopUp.play();
    }

    public void endGame() {
        ihm.mView.gameOverPopUp();

        play.setText("Partie terminée. Le chasseur a gagné.");
        this.selected.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.selected = new Button("");
        setInteractions(false);

        Button replay = new Button("Recommencer");
        Button backToMenu = new Button("Retour au menu");
        Button quit = new Button("Quitter");

        endGameButtonsActions(replay, backToMenu, quit);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(replay, backToMenu, quit);
        stackPane.getChildren().add(hbox);

        ihm.mView.play.setText("Partie terminée. Le chasseur a gagné.");
    }

    private void endGameButtonsActions(Button replay, Button backToMenu, Button quit) {
        replay.setOnMouseClicked(e -> {
            ihm.mView.close();
            this.close();
            ihm.start(mainStage);
        });
        backToMenu.setOnMouseClicked(e -> {
            MainMenu main = new MainMenu();
            mainStage.close();
            ihm.mView.close();
            this.close();
            try {
                main.start(mainStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        quit.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    /**
     * Méthode qui initialise la grille du jeu.
     * 
     * @param columns     le nombre de colonnes
     * @param rows        le nombre de lignes
     * @param elementSize la taille des boutons dans chaque case
     */
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

    /**
     * Méthode qui permet d'initialiser le bouton shoot pour que le chasseur puisse
     * tirer dans le labyrinthe
     */
    private void initializeShootButton() {
        shoot = new Button("Shoot !");
        shoot.setOnMouseClicked(e -> {
            if (selected.getText().equals("")) {
                response.setText("Veuillez sélectionner une case.");
            } else {
                this.maze.getHunter().hit(new Coordinate(shootColumn, shootRow));
                if (!maze.getEnd()) {
                    Integer col = GridPane.getColumnIndex(this.selected);
                    Integer row = GridPane.getRowIndex(this.selected);
                    if (col != null && row != null) {
                        int column = col.intValue();
                        int rowValue = row.intValue();
                        maze.getMaze()[column][rowValue].discover();
                        this.response.setText(
                                "Vous avez tiré sur " + maze.getMaze()[column][rowValue].getState().toString() + "!");
                    }
                    this.display();
                    setInteractions(false);
                    play.setText("Tour " + turn + " : Monstre   |   Patience.");
                    ihm.mView.play();
                } else {
                    endGame();
                    return;
                }
            }
        });
    }

    private void stackPaneConfiguration() {
        stackPane = new StackPane(grid);
        play = new Label("Tour " + turn + " : Monstre   |   Patience.");
        play.setFont(new Font(15));
        stackPane.getChildren().add(play);
        StackPane.setAlignment(play, Pos.BOTTOM_CENTER);

        response = new Label("");
        response.setPadding(new Insets(40, 0, 0, 0));
        response.setFont(new Font(18));
        stackPane.getChildren().add(response);
        StackPane.setAlignment(response, Pos.TOP_CENTER);

        stackPane.getChildren().add(shoot);
        StackPane.setAlignment(shoot, Pos.TOP_CENTER);
    }

    public void close() {
        super.close();
    }

    /**
     * Méthode qui génère un tour de jeu pour le chasseur de façon aléatoire :
     * il tire sur une case au hasard.
     */
    public void playAISimple() {
        Random random = new Random();
        int randomColumn = random.nextInt(maze.getColumns());
        int randomRow = random.nextInt(maze.getRows());
        this.maze.getHunter().hit(new Coordinate(randomColumn, randomRow));
        if (!maze.getEnd()) {
            maze.getMaze()[randomColumn][randomRow].discover();
            this.response.setText(
                    "Vous avez tiré sur " + maze.getMaze()[randomColumn][randomRow].getState().toString() + "!");
            this.display();
            setInteractions(false);
            play.setText("Tour " + turn + " : Monstre   |   Patience.");
            ihm.mView.play();
        } else {
            endGame();
            return;
        }
    }

    /**
     * Méthode qui initialise l'interface graphique.
     * 
     * @param stage la scène principale de l'interface graphique.
     */
    private void start() {

        // macOSInputs();

        turn = 0;

        this.grid = new GridPane();
        int elementSize = 40;
        initializeGrid(maze.getColumns(), maze.getRows(), elementSize);
        grid.setAlignment(Pos.CENTER);

        initializeShootButton();

        stackPaneConfiguration();
        scene = new Scene(stackPane, 550, 550);

        setInteractions(false);
        display();

        this.setX(900);
        this.setY(100);
        this.setTitle("HunterView");
        setScene(scene);
        show();
    }
}
