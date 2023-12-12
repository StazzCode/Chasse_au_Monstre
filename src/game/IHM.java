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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.jupiter.api.condition.OS;

import java.awt.im.InputContext;
import menu.MainMenu;

/**
 * Cette classe représente l'interface graphique du jeu. Elle hérite de la
 * classe Application de JavaFX.
 */
public class IHM extends Application {

    ////////////////////////////////////////////////////////////
    // Constante des touches par défaut.
    KeyCode keyCodeUp = KeyCode.Z;
    KeyCode keyCodeDown = KeyCode.S;
    KeyCode keyCodeLeft = KeyCode.Q;
    KeyCode keyCodeRight = KeyCode.D;
    ////////////////////////////////////////////////////////////

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

    boolean AIHunter;
    boolean AIMonster;

    String firstPlayerName;
    String secondPlayerName;
    Difficulty difficulty;

    public IHM(GameParameter parameter) {
        difficulty = parameter.getDifficulty();
        firstPlayerName = parameter.getFirstPlayerName();
        firstPlayerName = parameter.getSecondPlayerName();
    }

    public IHM() {
        this(new GameParameter());
    }

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

    /**
     * Méthode qui retourne la dernière apparition du monstre si c'est une cellule
     * vide, sinon le caractère correspondant au type de cellule
     * 
     * @param c la cellule choisie
     * @return la dernière apparition du monstre si c'est une cellule vide, sinon le
     *         caractère correspondant au type de cellule
     */
    public String displayCell(Cell c) {
        if (c.getState() == CellInfo.EMPTY && c.getLastMonsterAppearance() > 0) {
            return String.valueOf(c.getLastMonsterAppearance());
        }
        return Character.toString(c.getState().getCar());
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
     * Méthode qui affiche le labyrinthe vu par le chasseur
     */
    public void displayHunterView() {
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
     * Méthode qui affiche le labyrinthe vu par le monstre
     */
    public void displayMonsterView() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getColumns() + j);
                if (maze.getMonster().near(i, j)) {
                    b.setBackground(
                            new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
                    b.setText(Character.toString(maze.getMaze()[i][j].getState().getCar()));
                } else {
                    b.setText(" ");
                }
            }
        }
    }

    /**
     * Méthode qui permet de mettre en pause le programme pendant un certain temps.
     * 
     * @param time la durée choisie d'arrêt
     */
    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Méthode qui gère le tour du monstre.
     */
    public void monsterPlay() {
        IHM.sleep(500);
        this.displayHunterView();
        if (maze.getEnd()) {
            endGame(false);
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

    private void endGame(boolean hunterWon) {
        if (hunterWon) {
            play.setText("Partie terminée. Le chasseur a gagné.");
            this.selected
                    .setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            this.selected = new Button("");
            setHunterInteractions(false);
        } else {
            play.setText("Partie terminée. Le Monstre a gagné.");
            setMonsterInteractions(false);
        }

        Button replay = new Button("Recommencer");
        Button backToMenu = new Button("Retour au menu");
        Button quit = new Button("Quitter");

        endGameButtonsActions(replay, backToMenu, quit);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(replay, backToMenu, quit);
        stackPane.getChildren().add(hbox);
    }

    private void endGameButtonsActions(Button replay, Button backToMenu, Button quit) {
        replay.setOnMouseClicked(e -> {
            this.start(mainStage);
        });
        backToMenu.setOnMouseClicked(e -> {
            MainMenu main = new MainMenu();
            mainStage.close();
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
     * Méthode qui gère les interactions du monstre avec le clavier.
     * 
     * @param active l'intéraction utilisée
     */
    public void setMonsterInteractions(boolean active) {
        if (active) {
            scene.setOnKeyPressed(e -> {
                if (e.getCode() == keyCodeUp) {
                    setMonsterMovementKeybind(0, -1);
                }
                if (e.getCode() == keyCodeLeft) {
                    setMonsterMovementKeybind(-1, 0);
                }
                if (e.getCode() == keyCodeDown) {
                    setMonsterMovementKeybind(0, 1);
                }
                if (e.getCode() == keyCodeRight) {
                    setMonsterMovementKeybind(1, 0);
                }
            });
        } else {
            scene.setOnKeyPressed(null);
        }
    }

    /**
     * Méthode qui affiche sur le jeu affiche "Mouvement impossible ! Réessayer" si
     * le déplacement choisi pour le monstre est impossible
     * 
     * @param columnMovement le mouvement en ordonnée
     * @param rowMovement    le mouvement en abscisse
     */
    private void setMonsterMovementKeybind(int columnMovement, int rowMovement) {
        if (maze.getMonster().move(new Coordinate(maze.getMonster().getCoordinate().getColumn() + columnMovement,
                maze.getMonster().getCoordinate().getRow() + rowMovement))) {
            // System.out.println(maze.hasNeighbors(maze.getMonster().coordinate));
            monsterPlay();
        } else {
            response.setText("Mouvement impossible ! Réessayez.");
        }
    }

    /**
     * Méthode qui gère les interactions du chasseur avec le clavier.
     * 
     * @param active l'intéraction utilisée
     */
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
                    hunterPlay();
                } else {
                    endGame(true);
                }
            }
        });
    }

    private void stackPaneConfiguration() {
        stackPane = new StackPane(grid);
        play = new Label("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");
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

    /**
     * Gestion des touches pour Macos.
     */
    private void macOSInputs() {
        InputContext context = InputContext.getInstance();
        String loc = context.getLocale().toString();
        if (OS.current() == OS.MAC && (loc.equals("fr"))) {
            keyCodeUp = KeyCode.W;
            keyCodeLeft = KeyCode.A;
        }
    }

    /**
     * Méthode qui initialise l'interface graphique.
     * 
     * @param stage la scène principale de l'interface graphique.
     */
    @Override
    public void start(Stage stage) {
        mainStage = stage;

        // Mettre à jour la valeur avec le choix utilisateur.
        AIHunter = false;
        AIMonster = false;

        // macOSInputs();

        // int columns = 4;
        // int rows = 4;
        turn = 0;
        // version fonctionnelle classique:

        // this.maze = new Maze(columns, rows);
        // maze.resetMaze();
        // maze.generateEnterExit();
        // maze.generateObstacles();
        // maze.genererLabyrinthe(maze.getEnter().column, maze.getEnter().row);

        // version de test avec difficultés:

        difficulty = Difficulty.MOYEN;
        int columns = difficulty.getColumnsDifficulty();
        int rows = difficulty.getRowsDifficulty();
        int nbObstacles = difficulty.getNbObstaclesDifficulty();
        this.maze = new Maze(columns, rows);
        boolean pathExist = false;
        while (!pathExist) { // Disclamer : ne jamais mettre en param de generateObstacle un nombre trop
                             // élevé par rapport a la taille du labyrinthe
            maze.resetMaze();
            maze.generateEnterExit();
            maze.generateObstacles(nbObstacles);
            pathExist = maze.checkPathExists();
        }
        System.out.println(maze.getMaxObstaclePossible());
        this.grid = new GridPane();
        int elementSize = 40;
        initializeGrid(columns, rows, elementSize);
        grid.setAlignment(Pos.CENTER);

        initializeShootButton();
        shoot.setVisible(false);

        stackPaneConfiguration();

        scene = new Scene(stackPane, 550, 550);
        hunterPlay();

        stage.setScene(scene);
        stage.show();
    }

    public int updateNbObstacles(Difficulty difficulty) {
        int ratio = 0;
        if (Difficulty.TRES_FACILE == difficulty) {
            ratio = 4;
        } else if (Difficulty.FACILE == difficulty) {
            ratio = 3;
        } else if (Difficulty.MOYEN == difficulty) {
            ratio = 2;
        } else if (Difficulty.DIFFICILE == difficulty) {
            ratio = 2;
        } else if (Difficulty.TRES_DIFFICILE == difficulty) {
            ratio = 2;
        }

        int nbObstacles = (maze.getColumns() * maze.getRows() / ratio);
        return nbObstacles;
    }

    /**
     * Méthode main qui lance l'application.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}