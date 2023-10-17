package menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Logo du jeu
        ImageView logo = new ImageView(new Image(getClass().getResource("logo.png").toExternalForm()));
        VBox.setMargin(logo, new Insets(50, 0, 0, 0));

        // Definition du bouton pour jouer
        Button play = new Button("Jouer");
        VBox.setMargin(play, new Insets(80, 0, 20, 0));
        play.setPrefSize(360, 60);
        play.getStyleClass().add("playButton");
        
        // Definition du bouton pour les options
        Button settings = new Button("Options");
        VBox.setMargin(settings, new Insets(20, 0, 0, 0));
        settings.setPrefWidth(230);
        settings.setOnAction(e->{
            //Faire un menu selon les options que l'on aura besoin de mofifier
            //
            //
            //
        });

        // Definition du bouton pour les règles du jeu
        Button howToPlay = new  Button("Comment jouer ?");
        VBox.setMargin(howToPlay, new Insets(20, 0, 0, 0));
        howToPlay.setPrefWidth(230);
        howToPlay.setOnAction(e->{  // Ce que le bouton fait quand l'on clique dessus
            Map<String,String> paragraphes = new HashMap<>();   // Map contenant tritre en clés avec le paragraphe d"explication pour chaque règles
            paragraphes.put("Titre1", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            paragraphes.put("Titre2", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            paragraphes.put("Titre3", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            paragraphes.put("Titre4", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            howToPlayMenu(primaryStage, paragraphes);   // Passage au menu des règles du jeu affiché dans le Stage d'origine et avec la Map des règles
        });

        // Definition du bouton pour quitter
        Button quit = new Button("Quitter");
        VBox.setMargin(quit, new Insets(20, 0, 0, 0));
        quit.setPrefWidth(230);
        quit.setOnAction(e->{   // Ce que le bouton fait quand l'on clique dessus
            System.exit(0);     // ici, le bouton ferme le processus
        });

        // Definition de la box principale contenant tout les élements
        VBox root = new VBox(logo,play,settings,howToPlay,quit);
        root.getStyleClass().add("root");

        // Definition du fond de menu animé
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("background.gif")));
        background.setFitHeight(520);
        background.setPreserveRatio(true);
        
        // StackPane pour allier le fond animé et les élement de root
        StackPane stackPane = new StackPane(background, root);

        Scene scene = new Scene(stackPane, 820, 520);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Feuille de style contenant toutes les indications pour les élements du menu

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public void howToPlayMenu(Stage baseStage, Map<String,String> paragraphes){
        Scene oldScene = baseStage.getScene();
        Label titre = new Label("Comment jouer ?");
        titre.getStyleClass().add("mainTitre");
        VBox.setMargin(titre, new Insets(20, 0, 10,0));
        Button back = new Button("Retour au menu");
        back.getStyleClass().add("playButton");
        back.setOnAction(e->{baseStage.setScene(oldScene);});
        VBox.setMargin(back, new Insets(20, 0, 20, 0));
        VBox root = new VBox(titre,back);
        HBox.setMargin(root, new Insets(0, 0, 0, 110));
        root.getStyleClass().add("root");
        for(Entry<String,String> s : paragraphes.entrySet()){
            Label secondTitre = new Label(s.getKey());
            secondTitre.getStyleClass().add("secondTitre");
            secondTitre.setWrapText(true);
            secondTitre.setMaxWidth(570);
            VBox.setMargin(secondTitre, new Insets(15, 0, 15, 0));
            Label text = new Label(s.getValue());
            text.setWrapText(true);
            text.setMaxWidth(600);
            VBox.setMargin(text, new Insets(0, 0, 45, 0));
            text.setTextAlignment(TextAlignment.JUSTIFY);
            root.getChildren().add( root.getChildren().size()-1,secondTitre);
            root.getChildren().add( root.getChildren().size()-1,text);
        }
        HBox container = new HBox(root);
        container.minWidth(820);
        container.maxWidth(820);
        container.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(new ScrollPane(container), oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        baseStage.setScene(newScene);
    }

    public void playMenu(Stage baseStage){

    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}