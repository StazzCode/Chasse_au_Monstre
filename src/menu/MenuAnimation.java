package menu;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * La classe MenuAnimation représente l'animation du menu principal.
 * Elle contient les briques lego et les figurines qui bougent.
 */

public class MenuAnimation {
    private StackPane pane;
    private List<Transition> animations;
    private HBox animeBriksBox;
    private HBox animePersoBox;

    /**
     * Constructeur de MenuAnimation
     */
    public MenuAnimation() {
        animeBriksBox = new HBox();
        animePersoBox = new HBox();
        Duration duree = Duration.millis(300);
        animations = new ArrayList<>();

        animations.add(initTopRight(duree));
        animations.add(initTopLeft(duree));
        animations.add(initBottomLeft(duree));
        animations.add(initBottomRight(duree));

        duree = Duration.millis(duree.toMillis() + 200);
        animations.add(initHunter(duree));
        animations.add(initMonster(duree));

        pane = new StackPane(animePersoBox, animeBriksBox);
    }

    /**
     * Initialise la brique en haut à gauche
     * 
     * @param duree
     * @return
     */
    private TranslateTransition initTopLeft(Duration duree) {
        ImageView topLeftImage = new ImageView(new Image(getClass().getResource("img/topLeft.png").toExternalForm()));
        topLeftImage.setPreserveRatio(true);
        topLeftImage.setFitWidth(160);
        TranslateTransition animationTopLeft = new TranslateTransition(duree, topLeftImage);

        animationTopLeft.setFromX(-220 - topLeftImage.getFitWidth());
        animationTopLeft.setToX(-220);
        animationTopLeft.setFromY(-210);
        animationTopLeft.setToY(-10);
        animeBriksBox.getChildren().add(topLeftImage);
        return animationTopLeft;
    }

    /**
     * Initialise la brique en haut à droite
     * 
     * @param duree
     * @return
     */
    private TranslateTransition initTopRight(Duration duree) {
        ImageView topRightImage = new ImageView(new Image(getClass().getResource("img/topRight.png").toExternalForm()));
        topRightImage.setPreserveRatio(true);
        topRightImage.setFitWidth(200);
        TranslateTransition animationTopRight = new TranslateTransition(duree, topRightImage);
        animationTopRight.setFromX(800 + topRightImage.getFitWidth());
        animationTopRight.setToX(800);
        animationTopRight.setFromY(0 - 200);
        animationTopRight.setToY(0);
        animeBriksBox.getChildren().add(topRightImage);
        return animationTopRight;
    }

    /**
     * Initialise la brique en bas à gauche
     * 
     * @param duree
     * @return
     */
    private TranslateTransition initBottomLeft(Duration duree) {
        ImageView bottomLeftImage = new ImageView(
                new Image(getClass().getResource("img/bottomLeft.png").toExternalForm()));
        bottomLeftImage.setPreserveRatio(true);
        bottomLeftImage.setFitWidth(150);
        TranslateTransition animationBottomLeft = new TranslateTransition(duree, bottomLeftImage);
        animationBottomLeft.setFromX(-365 - bottomLeftImage.getFitWidth());
        animationBottomLeft.setToX(-365);
        animationBottomLeft.setFromY(650);
        animationBottomLeft.setToY(470);
        animeBriksBox.getChildren().add(bottomLeftImage);
        return animationBottomLeft;
    }

    /**
     * Initialise la brique en bas à droite
     * 
     * @param duree
     * @return
     */
    private TranslateTransition initBottomRight(Duration duree) {
        ImageView bottomRightImage = new ImageView(
                new Image(getClass().getResource("img/bottomRight.png").toExternalForm()));
        bottomRightImage.setPreserveRatio(true);
        bottomRightImage.setFitWidth(272);
        TranslateTransition animationBottomRight = new TranslateTransition(duree, bottomRightImage);
        animationBottomRight.setFromX(200 + bottomRightImage.getFitWidth());
        animationBottomRight.setToX(200);
        animationBottomRight.setFromY(650);
        animationBottomRight.setToY(475);
        animeBriksBox.getChildren().add(bottomRightImage);
        return animationBottomRight;
    }

    /**
     * Initialise le chasseur
     * 
     * @param duree
     * @return
     */
    private ParallelTransition initHunter(Duration duree) {
        ImageView hunterImage = new ImageView(new Image(getClass().getResource("img/hunter.png").toExternalForm()));
        hunterImage.setPreserveRatio(true);
        hunterImage.setFitWidth(250);
        TranslateTransition translateHunter = new TranslateTransition(duree, hunterImage);
        RotateTransition rotateHunter = new RotateTransition(duree, hunterImage);
        rotateHunter.setToAngle(-17);
        translateHunter.setFromX(800 + hunterImage.getFitWidth());
        translateHunter.setToX(700);
        translateHunter.setFromY(200);
        translateHunter.setToY(200);
        ParallelTransition animationHunter = new ParallelTransition(translateHunter, rotateHunter);
        animePersoBox.getChildren().add(hunterImage);
        return animationHunter;
    }

    /**
     * Initialise le monstre
     * 
     * @param duree
     * @return
     */
    private ParallelTransition initMonster(Duration duree) {
        ImageView monsterImage = new ImageView(new Image(getClass().getResource("img/monster.png").toExternalForm()));
        monsterImage.setPreserveRatio(true);
        monsterImage.setFitWidth(350);
        TranslateTransition translateMonster = new TranslateTransition(duree, monsterImage);
        RotateTransition rotateMonster = new RotateTransition(duree, monsterImage);
        rotateMonster.setToAngle(8);
        translateMonster.setFromX(-200 - monsterImage.getFitWidth());
        translateMonster.setToX(-250);
        translateMonster.setFromY(280);
        translateMonster.setToY(280);
        ParallelTransition animationMonster = new ParallelTransition(translateMonster, rotateMonster);
        animePersoBox.getChildren().add(monsterImage);
        return animationMonster;
    }

    /**
     * Méthode qui permet de lancer le menu avec les animations
     */
    public void play() {
        for (Transition t : animations)
            t.play();
    }

    /**
     * Méthode qui permet de stopper le menu avec les animations
     * 
     * @return
     */
    public StackPane getStackPane() {
        return pane;
    }
}