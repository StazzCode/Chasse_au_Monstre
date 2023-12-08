package menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import game.*;
/**
 * La classe MainMenu représente le menu principal du jeu.
 * Il hérite de la classe Application de JavaFX.
 * Il contient les boutons pour jouer, accéder aux options, aux règles du jeu et pour quitter le jeu.
 * Il contient également le logo du jeu et un fond animé.
 */
public class MainMenu extends Application{


    /**
     * La méthode start permet d'initialiser le début du jeu.
     * @param primaryStage le stage principal du jeu.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Logo du jeu
        ImageView logo = new ImageView(new Image(getClass().getResource("img/logo.png").toExternalForm()));
        VBox.setMargin(logo, new Insets(50, 0, 0, 0));
        logo.setPreserveRatio(true);
        logo.setFitHeight(160);

        // Definition de l'animation avec les brique lego et les figurines
        MenuAnimation animation = new MenuAnimation();
        primaryStage.setOnShown(e->animation.play());

        // Definition du bouton pour jouer
        Button play = new Button("Jouer");
        VBox.setMargin(play, new Insets(60, 0, 20, 0));
        play.setPrefSize(380, 60);
        play.getStyleClass().add("playButton");
        play.setOnAction(e-> playMenu(primaryStage,animation));
        
        // Definition du bouton pour les Crédits
        Button credit = new Button("Crédits");
        VBox.setMargin(credit, new Insets(30, 0, 0, 0));
        credit.setPrefWidth(245);
        credit.setMaxHeight(45);
        credit.setOnAction(e->{});

        // Definition du bouton pour les règles du jeu
        Button howToPlay = new  Button("Comment jouer ?");
        VBox.setMargin(howToPlay, new Insets(20, 0, 0, 0));
        howToPlay.setPrefWidth(245);
        howToPlay.setMaxHeight(45);
        howToPlay.setOnAction(e->{  // Ce que le bouton fait quand l'on clique dessus
            Map<String,String> paragraphes = new HashMap<>();   // Map contenant tritre en clés avec le paragraphe d"explication pour chaque règles
            paragraphes.put("Explications", "Le déroulement se passe au tour par tour alternativement le monstre puis le chasseur et ainsi de suite. Le monstre apparaît à l’entrée d’un labyrinthe et va pouvoir à chacun de ses tours se déplacer d’une case, découvrant ainsi les obstacles s’offrant à lui tout en étant à la recherche de la sortie qui représente sa condition de victoire. Parallèlement, entre chacun des tours du monstre, le chasseur possède une vision d’ensemble du labyrinthe et va pouvoir tirer sur une case à chaque tour. Tirer sur une case sera source d’informations, cela révélera si la case visée était vide, un mur du labyrinthe ou une case par laquelle le monstre est déjà passé révélant au passage le numéro du tour durant lequel le monstre est passé par là. Enfin, tirer sur la case où le monstre est présent est la condition de victoire du chasseur.");
            paragraphes.put("Titre2", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            paragraphes.put("Titre3", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            paragraphes.put("Titre4", "Integer semper semper egestas. Aenean congue enim lacus, eget pretium magna ultrices vel. Fusce at nunc facilisis, feugiat est et, aliquet urna. Donec suscipit elit arcu, eget mattis neque suscipit ac. Nunc egestas leo non rhoncus ornare. Vestibulum semper arcu id pharetra consectetur. Vestibulum ut posuere sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam purus ligula, maximus eget mauris id, consequat feugiat risus. Vestibulum laoreet arcu vitae enim tincidunt vulputate. ");
            howToPlayMenu(primaryStage, paragraphes, animation);   // Passage au menu des règles du jeu affiché dans le Stage d'origine et avec la Map des règles
        });

        // Definition du bouton pour quitter
        Button quit = new Button("Quitter");
        VBox.setMargin(quit, new Insets(20, 0, 0, 0));
        quit.setPrefWidth(245);
        quit.setMaxHeight(45);
        quit.setOnAction(e->{   // Ce que le bouton fait quand l'on clique dessus
            System.exit(0);     // ici, le bouton ferme le processus
        });

        // Definition de la box principale contenant tous les élements
        VBox root = new VBox(logo,play,howToPlay,credit,quit);
        root.getStyleClass().add("root");

        // Definition du fond de menu animé
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("img/background.png")));
        background.setFitHeight(624);
        background.setPreserveRatio(true);

        StackPane stackPane = new StackPane(background,animation.getStackPane(), root);

        Scene scene = new Scene(stackPane, 984, 624);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()); // Feuille de style contenant toutes les indications pour les élements du menu
        Image cursorImage = new Image(getClass().getResource("img/cursor.png").toExternalForm());  
        scene.setCursor(new ImageCursor(cursorImage));
        for(Node n : root.getChildren().subList(1, root.getChildren().size())){
            n.setOnMouseEntered(e->{
                scene.setCursor(new ImageCursor(new Image(getClass().getResource("img/hand.png").toExternalForm())));
            });
            n.setOnMouseExited(e->{
                scene.setCursor(new ImageCursor(new Image(getClass().getResource("img/cursor.png").toExternalForm())));
            });
        }

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }


    /**
     * Méthode qui permet d'expliquer comment jouer au jeu de la chasse au monstre.
     * @param baseStage le stage de base.
     * @param paragraphes les règles du jeu.
     */
    public void howToPlayMenu(Stage baseStage, Map<String,String> paragraphes, MenuAnimation animation){
        Scene oldScene = baseStage.getScene();
        Label titre = new Label("Comment jouer ?");
        titre.getStyleClass().add("mainTitre");
        VBox.setMargin(titre, new Insets(20, 0, 10,0));
        Button back = new Button("Retour au menu");
        back.getStyleClass().add("playButton");
        back.setOnAction(e->{
            baseStage.setScene(oldScene);
            animation.play();
        ;});

        VBox.setMargin(back, new Insets(20, 0, 20, 0));
        VBox root = new VBox(titre);
        HBox.setMargin(root, new Insets(0, 0, 0, 190));
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
            root.getChildren().add( root.getChildren().size(),secondTitre);
            root.getChildren().add( root.getChildren().size(),text);
        }
        HBox container = new HBox(root);
        container.minWidth(980);
        container.maxWidth(980);
        container.setAlignment(Pos.CENTER);
        ScrollPane pane = new ScrollPane(container);
        pane.setVvalue(0);
        VBox tot = new VBox(pane,back);
        Scene newScene = new Scene(tot, oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        baseStage.setScene(newScene);
    }

    /**
     * Méthode qui permet de lancer le menu de jeu.
     * @param baseStage le stage de base.
     */
    public void playMenu(Stage baseStage, MenuAnimation menuAnimation){
        Scene oldScene = baseStage.getScene();

        DifficultySlider difficulty = new DifficultySlider();
        difficulty.setPrefWidth(350);
        difficulty.setMaxWidth(350);
        difficulty.getStyleClass().add("center");

        TextField firstPlayerLabel = new TextField("Joueur 1");// Faire les fonctionnalitées de changement de nom plus tard
        TextField secondPlayerLabel = new TextField("Joueur 2");// Faire les fonctionnalitées de changement de nom plus tard

        VBox root = new VBox();
        HBox bottom=new HBox();

        HBox top=new HBox(50);
        top.setPrefSize(oldScene.getWidth(), oldScene.getHeight()/8);
        top.getStyleClass().add("top");

        VBox localBox=new VBox();

        HBox iaBox=new HBox(new Label("A VENIR !")); // A FAIRE PLUS TARD

        HBox onlineBox=new HBox(new Label("A VENIR !"));// A FAIRE PLUS TARD

        Button joueurVsJoueurOnglet = new Button("Local 1V1");
        localBox.setPrefSize(oldScene.getWidth(), (oldScene.getHeight()/8)*6);
        localBox.getStyleClass().add("center");
        iaBox.setPrefSize(oldScene.getWidth(), (oldScene.getHeight()/8)*6);
        iaBox.getStyleClass().add("center");
        onlineBox.setPrefSize(oldScene.getWidth(), (oldScene.getHeight()/8)*6);
        onlineBox.getStyleClass().add("center");
        joueurVsJoueurOnglet.setOnAction(e->{
            root.getChildren().clear();
            root.getChildren().addAll(top,localBox,bottom);
        });
        Button iaVsIaOnglet = new Button("IA");
        iaVsIaOnglet.setOnAction(e->{
            root.getChildren().clear();
            root.getChildren().addAll(top,iaBox,bottom);
        });
        Button joueurVsIaOnglet = new Button("En ligne");
        joueurVsIaOnglet.setOnAction(e->{
            root.getChildren().clear();
            root.getChildren().addAll(top,onlineBox,bottom);
        });
        top.getChildren().addAll(joueurVsJoueurOnglet,joueurVsIaOnglet,iaVsIaOnglet);
        for(Node n : top.getChildren()) ((Button)n).setPrefWidth(oldScene.getWidth()/(top.getChildren().size()+1));

        Button back = new Button("Menu");
        back.setOnAction(e->{
            baseStage.setScene(oldScene);
            menuAnimation.play();
        });

        Button play = new Button("Lancer");
        play.setOnAction(e->{
            GameParameter parameters = new GameParameter();
            parameters.setDifficulty(Difficulty.fromInt((int) difficulty.getValue()));
            parameters.setFirstPlayerName(firstPlayerLabel.getText());
            parameters.setSecondPlayerName(secondPlayerLabel.getText());
            IHM ihm = new IHM(parameters);
            baseStage.close();
            ihm.start(baseStage);
        });

        bottom.getChildren().addAll(back, play);
        bottom.setPrefSize(oldScene.getWidth(), oldScene.getHeight()/8);
        bottom.getStyleClass().add("bottom");
        for(Node n : bottom.getChildren()) ((Button)n).setPrefWidth(oldScene.getWidth()/(bottom.getChildren().size()+2));
        HBox.setMargin(back, new Insets(0, oldScene.getWidth()-2*200-140, 0, 0));

        localBox.getChildren().add(new HBox(firstPlayerLabel,new Label("VS"),secondPlayerLabel));
        localBox.getChildren().get(0).getStyleClass().add("center");
        localBox.getChildren().add(difficulty);

        HBox.setMargin(firstPlayerLabel, new Insets(0, 30, 0, 0));
        HBox.setMargin(secondPlayerLabel, new Insets(0, 0, 0, 30));

        root.getChildren().addAll(top,localBox,bottom);
        Scene newScene = new Scene(root, oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        baseStage.setScene(newScene);
    }
    
    /**
     * Méthode qui permet de lancer le jeu.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
