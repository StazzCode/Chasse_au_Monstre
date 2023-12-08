package graphics;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PopUpPane extends StackPane{
    private ScaleTransition animation;

    public PopUpPane(Duration duree, Image img){
        ImageView image = new ImageView(img);
        image.setPreserveRatio(true);
        image.setFitWidth(1000);
        animation = new ScaleTransition(duree, image);
        animation.setFromX(0.01);
        animation.setFromY(0.01);
        animation.setToX(1);
        animation.setToY(1);
        
        image.setStyle("-fx-alignment : center;");

        setStyle(
            "-fx-alignment : center;"+
            "-fx-background-color:transparent;"
        );
    }

    public void play(){
        animation.play();
    }
}
