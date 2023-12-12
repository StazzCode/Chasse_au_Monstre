package graphics;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PopUpPane extends StackPane{
    private ScaleTransition animation;

    private PopUpPane(Duration duree, Image img){
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

    private PopUpPane(Duration duree, String img){
       ImageView image = new ImageView(new Image(getClass().getResource(img).toExternalForm()));
        image.setPreserveRatio(true);
        //image.setFitWidth(this.getWidth());
        //setHeight(image.getFitHeight());
        animation = new ScaleTransition(duree, image);
        animation.setFromX(0.01);
        animation.setFromY(0.01);
        animation.setToX(1);
        animation.setToY(1);
        
        //image.setStyle("-fx-alignment : center;");

        setStyle(
            "-fx-background-color:transparent;"
        );
        getChildren().add(image);
    }

    @Override
    public void setWidth(double w){
        super.setWidth(w);
        ((ImageView)getChildren().get(0)).setFitWidth(w);
    }

    @Override
    public void setHeight(double h){
        super.setHeight(h);
        ((ImageView)getChildren().get(0)).setFitHeight(h);
    }

    public final void setOnFinished(EventHandler<ActionEvent> var1) {
      animation.setOnFinished(var1);
    }

    public void play(){
        animation.play();
    }

    public static PopUpPane getGameOverPane(){
        return new PopUpPane(Duration.millis(350), "img/gameOver.png");
    }

}
