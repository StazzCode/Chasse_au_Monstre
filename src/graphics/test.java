package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class test extends Application{

    @Override
    public void start(Stage arg0) throws Exception {
        PopUpPane pane = PopUpPane.getGameOverPane();
        pane.setWidth(500);
        HBox root = new HBox(pane);
        Scene sc = new Scene(root, 800, 800);
        arg0.setScene(sc);
        arg0.setOnShowing(e->{pane.play();});
        arg0.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
