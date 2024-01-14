package game.view;

import java.awt.im.InputContext;

import org.junit.jupiter.api.condition.OS;

import game.model.CellInfo;
import game.model.Coordinate;
import game.model.Maze;
import graphics.ItemsView;
import graphics.PopUpPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
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

/**
 * Cette classe représente l'interface graphique du monstre. Elle hérite de la
 * classe Stage de JavaFX et implémente l'interface IView.
 */
public class MonsterView extends Stage implements IView {

    IHM ihm;

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
    boolean iaMonster;

    /**
     * Constructeur de la classe MonsterView.
     * 
     * @param ihm       l'interface graphique du jeu.
     * @param mainStage le Stage principal de l'interface graphique.
     * @param maze      le labyrinthe du jeu.
     */
    public MonsterView(IHM ihm, Stage mainStage, Maze maze, boolean ia) {
        this.ihm = ihm;
        this.mainStage = mainStage;
        this.maze = maze;
        this.iaMonster = ia;
        this.start();
    }

    /**
     * Méthode qui affiche le labyrinthe vu par le monstre
     */
    public void display() {
        for (int i = 0; i < maze.getColumns(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                Button b = (Button) grid.getChildren().get(i * maze.getRows() + j);
                if (maze.getMonster().near(i, j)) {
                    CellInfo state = maze.getMaze()[i][j].getState();

                    if(state == CellInfo.EMPTY || state == CellInfo.MONSTER) b.setBackground(ItemsView.getVisibleMazeCellBackground());
                    else if(state == CellInfo.WALL) b.setBackground(ItemsView.getWallMazeCellBackground());
                    else if(state == CellInfo.ENTER)b.setBackground(ItemsView.getEnterMazeCellBackground());
                    else if(state == CellInfo.EXIT) b.setBackground(ItemsView.getExitMazeCellBackground());

                    if(state == CellInfo.MONSTER) b.setBackground(ItemsView.getMonsterImageView());
                    b.setText(" ");
                } else {
                    b.setBackground(ItemsView.getHiddenMazeCellBackground());
                    b.setText(" ");
                }
            }
        }
    }

    /**
     * Méthode qui gère les interactions du monstre avec le clavier.
     * 
     * @param active booléen pour activer ou désactiver
     */
    public void setInteractions(boolean active) {

        ////////////////////////////////////////////////////////////
        // Gestion des touches pour Macos.
        ////////////////////////////////////////////////////////////
        /*
        InputContext context = InputContext.getInstance();
        String loc = context.getLocale().toString();
        /*if (OS.current() == OS.MAC && (loc.equals("fr"))){
            keyCodeUp = KeyCode.W;
            keyCodeLeft = KeyCode.A;
        }*/
        ////////////////////////////////////////////////////////////


        if (active) {
            play.setText("Tour " + turn + " : Monstre   |   Utilisez ZQSD pour vous déplacer.");
            if (iaMonster) {
                Coordinate coord = this.maze.getMonster().iaMoveImproved();
                setMonsterMovementKeybind(coord.getColumn(), coord.getRow());
                return;
            }
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
     * Gestion des touches pour MacOS.
     * 
     */
    private void macOSKeybinding() {
        InputContext context = InputContext.getInstance();
        String loc = context.getLocale().toString();
        if (OS.current() == OS.MAC && (loc.equals("fr"))){
            keyCodeUp = KeyCode.W;
            keyCodeLeft = KeyCode.A;
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
            this.display();
            setInteractions(false);
            play.setText("Tour " + turn + " : Chasseur   |   Patience.");
            if (maze.getEnd()) {
                endGame();
                return;
            }
            ihm.hView.play();
        } else {
            response.setText("Mouvement impossible ! Réessayez.");
        }
    }

    /**
     * Méthode qui fait passer le tour du chasseur.
     */
    public void play() {
        turn++;
        maze.setCompteur(turn);
        this.display();
        setInteractions(true);
    }

    /**
     * Méthode qui affiche un pop-up de fin de partie.
     */
    void gameOverPopUp() {
        PopUpPane gameOverPopUp = PopUpPane.getGameOverPane();
        stackPane.getChildren().add(stackPane.getChildren().size() - 1, gameOverPopUp);
        gameOverPopUp.setOnFinished(e -> {
            stackPane.getChildren().remove(gameOverPopUp);
        });
        gameOverPopUp.play();
    }

    /**
     * Méthode qui affiche la fin de la partie.
     */
    public void endGame() {
        ihm.hView.gameOverPopUp();

        play.setText("Partie terminée. Le Monstre a gagné.");

        Button replay = new Button("Recommencer");
        Button backToMenu = new Button("Retour au menu");
        Button quit = new Button("Quitter");

        endGameButtonsActions(replay, backToMenu, quit);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(replay, backToMenu, quit);
        stackPane.getChildren().add(hbox);

        ihm.hView.play.setText("Partie terminée. Le Monstre a gagné.");
    }

    /**
     * Méthode qui gère les actions des boutons de fin de partie.
     */
    private void endGameButtonsActions(Button replay, Button backToMenu, Button quit) {
        replay.setOnMouseClicked(e -> {
            ihm.hView.close();
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
     * Méthode qui configure la StackPane principale.
     */
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
    }

    /**
     * Méthode qui ferme la fenêtre.
     */
    public void close() {
        super.close();
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
        //iaMonster = true;
        initializeGrid(maze.getColumns(), maze.getRows(), elementSize);
        grid.setAlignment(Pos.CENTER);

        stackPaneConfiguration();
        scene = new Scene(stackPane, 550, 550);
        display();
        
       	//setInteractions(true);
        	
        this.setX(100);
        this.setY(100);
        this.setTitle("MonsterView");
        setScene(scene);
        show();
    }
}
