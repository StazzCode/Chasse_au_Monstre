package graphics;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class GameOverPane{

    private final static Duration DUREE = Duration.millis(500);

    public GameOverPane(){
        Image image = new Image(getClass().getResource("img/gameOver.png").toExternalForm());
    }
}
