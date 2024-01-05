package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ItemsView {
    private Background initBackground(String s){
        Image image = new Image(getClass().getResource("img/"+s).toExternalForm());
        BackgroundSize size = new BackgroundSize(1, 1, true, true, false, false);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size );
        Background res = new Background(bgImage);
        return res;
    }

    private ImageView initImage(String s){
        Image image = new Image(getClass().getResource("img/"+s).toExternalForm());
        return new ImageView(image);
    }

    private ItemsView(){}

    public static Background getVisibleMazeCellBackground(){return new ItemsView().initBackground("grass.png");}
    public static Background getWallMazeCellBackground(){return new ItemsView().initBackground("wall.png");}
    public static Background getHiddenMazeCellBackground(){return new ItemsView().initBackground("fog.png");}
    public static Background getEnterMazeCellBackground(){return new ItemsView().initBackground("enter.png");}
    public static Background getExitMazeCellBackground(){return new ItemsView().initBackground("exit.png");}
    public static Background getMonsterImageView(){return new ItemsView().initBackground("monster.png");}
}
