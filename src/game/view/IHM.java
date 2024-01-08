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
    HunterView hViewAI;

    String firstPlayerName;
    String secondPlayerName;
    Difficulty difficulty;
    Integer longueur;
    Integer largeur;
    boolean iaHunter;
    boolean iaMonster;

    /**
     * Constructeur de la classe IHM avec un paramètre.
     * 
     * @param parameter les paramètres du jeu.
     */
    public IHM(GameParameter parameter) {
        difficulty = parameter.getDifficulty();
        firstPlayerName = parameter.getFirstPlayerName();
        firstPlayerName = parameter.getSecondPlayerName();
        longueur = parameter.getLongueur();
        largeur = parameter.getLargeur();
        iaHunter = parameter.getIaHunter();
        iaMonster = parameter.getIaMonster();
    }

    /**
     * Constructeur par défaut de la classe IHM.
     */
    public IHM() {
        this(new GameParameter());
    }
      
    /**
     * Met à jour le nombre d'obstacles en fonction de la difficulté.
     * 
     * @param difficulty la difficulté du jeu.
     * @return le nombre d'obstacles mis à jour.
     */
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
        difficulty = Difficulty.TRES_FACILE;
        int columns = longueur;
        int rows = largeur;
        int nbObstacles = difficulty.getNbObstaclesDifficulty();
        this.maze = new Maze(columns, rows);
        boolean pathExist = false;
        while (!pathExist) {
            maze.resetMaze();
            maze.generateEnterExit();
            maze.generateObstacles(nbObstacles);
            pathExist = maze.checkPathExists();
        }
        
        hView = new HunterView(this, stage,  this.maze, this.iaHunter); 
        mView = new MonsterView(this, stage, this.maze, this.iaMonster);
        mView.setInteractions(true);
    }



    /**
     * Méthode qui permet de lancer le jeu avec le monstre contre l'IA hunter.
     * 
     * @param stage la scène principale de l'interface graphique.
     */
    /*public void startWithAIHunter(Stage stage) {
        // macOSInputs();

        // version de test avec difficultés:
        difficulty = Difficulty.MOYEN;
        int columns = largeur;// difficulty.getColumnsDifficulty();
        int rows = longueur; // difficulty.getRowsDifficulty();
        int nbObstacles = difficulty.getNbObstaclesDifficulty();
        this.maze = new Maze(columns, rows);
        boolean pathExist = false;
        while (!pathExist) {
            maze.resetMaze();
            maze.generateEnterExit();
            maze.generateObstacles(nbObstacles);
            pathExist = maze.checkPathExists();
        }

        hViewAI = new HunterView(this, stage,  this.maze, true);
        mView = new MonsterView(this, stage, this.maze);
    }*/

    /**
     * Méthode qui lance l'interface graphique.
     * 
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

}