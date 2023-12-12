package game.view;

import javafx.application.Application;
import javafx.stage.Stage;

import game.model.Difficulty;
import game.model.GameParameter;
import game.model.Maze;

/**
 * Cette classe représente l'interface graphique du jeu. Elle hérite de la
 * classe Application de JavaFX.
 */
public class IHM extends Application {

    /**
     * Le labyrinthe du jeu.
     */
    Maze maze;

    MonsterView mView;
    HunterView hView;

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
     * Méthode qui initialise l'interface graphique.
     * 
     * @param stage la scène principale de l'interface graphique.
     */
    @Override
    public void start(Stage stage) {

        // macOSInputs();

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

        hView = new HunterView(this, stage,  this.maze);
        mView = new MonsterView(this, stage, this.maze);
    }


    /**
     * Méthode main qui lance l'application.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}