package graphics;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
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
    private SequentialTransition animation;

    private PopUpPane(double millis, Image img){
        this(millis, img.getUrl());
    }

    private PopUpPane(double millis, String img){
       ImageView image = new ImageView(new Image(getClass().getResource(img).toExternalForm()));
        image.setPreserveRatio(true);

        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(millis/4), image);
        scaleAnimation.setFromX(0.01);
        scaleAnimation.setFromY(0.01);
        scaleAnimation.setToX(1);
        scaleAnimation.setToY(1);

        ScaleTransition pause = new ScaleTransition(Duration.millis((millis/4)*3), image);
        pause.setFromX(1);
        pause.setToX(1.2);
        pause.setFromY(1);
        pause.setToY(1.2);

        animation = new SequentialTransition(scaleAnimation,pause);

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
        return getGameOverPane(1000);
    }

    public static PopUpPane getGameOverPane(double millis){
        return new PopUpPane(millis, "img/gameOver.png");
    }

}
