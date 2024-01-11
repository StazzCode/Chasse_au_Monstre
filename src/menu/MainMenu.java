package menu;

import java.net.URL;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

import graphics.PopUpMazeSize;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import game.model.Difficulty;
import game.model.GameParameter;
import game.view.IHM;
import javafx.util.Duration;

/**
 * La classe MainMenu représente le menu principal du jeu.
 * Il hérite de la classe Application de JavaFX.
 * Il contient les boutons pour jouer, accéder aux options, aux règles du jeu et
 * pour quitter le jeu.
 * Il contient également le logo du jeu et un fond animé.
 */
public class MainMenu extends Application {

    private static final boolean SQUAREONLY = false;
    private boolean enableCustom = false;
    private boolean enableIA = false;
    private static final int MINSIZE = 4;
    private static final int MAXSIZE = 10;
    private static final int DEFAULTSIZE = 7;
    private static final double DEFAULTOBS = 25.0;
    private static final int DEFAULTFOG = 2;
    private static final String DEFAULTCONFIG = "Défaut";
    private static final String CUSTOMCONFIG = "Personnalisée";

    /**
     * La méthode start permet d'initialiser le début du jeu.
     * 
     * @param primaryStage le stage principal du jeu.
     * @throws Exception l'exception levée par l'application
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
        primaryStage.setOnShown(e -> animation.play());

        // Definition du bouton pour jouer
        Button play = new Button("Jouer");
        VBox.setMargin(play, new Insets(60, 0, 20, 0));
        play.setPrefSize(380, 60);
        play.getStyleClass().add("playButton");
        play.setOnAction(e -> playMenu(primaryStage, animation));

        // Definition du bouton pour les Crédits
        Button credit = new Button("Crédits");
        VBox.setMargin(credit, new Insets(30, 0, 0, 0));
        credit.setPrefWidth(245);
        credit.setMaxHeight(45);
        credit.setOnAction(e -> {
        });

        // Definition du bouton pour les règles du jeu
        Button howToPlay = new Button("Comment jouer ?");
        VBox.setMargin(howToPlay, new Insets(20, 0, 0, 0));
        howToPlay.setPrefWidth(245);
        howToPlay.setMaxHeight(45);
        howToPlay.setOnAction(e -> { // Ce que le bouton fait quand l'on clique dessus
            ArrayList<String> titres = new ArrayList<>(); // Liste contenant les titres des paragraphes
            ArrayList<String> paragraphes = new ArrayList<>(); // Liste contenant les paragraphe
                                                               // d"explication pour chaque règles
            titres.add("Explications");
            paragraphes.add(
                    "Le déroulement se passe au tour par tour alternativement le monstre puis le chasseur et ainsi de suite. Le monstre apparaît à l’entrée d’un labyrinthe et va pouvoir à chacun de ses tours se déplacer d’une case, découvrant ainsi les obstacles s’offrant à lui tout en étant à la recherche de la sortie qui représente sa condition de victoire. Parallèlement, entre chacun des tours du monstre, le chasseur possède une vision d’ensemble du labyrinthe et va pouvoir tirer sur une case à chaque tour. Tirer sur une case sera source d’informations, cela révélera si la case visée était vide, un mur du labyrinthe ou une case par laquelle le monstre est déjà passé révélant au passage le numéro du tour durant lequel le monstre est passé par là. Enfin, tirer sur la case où le monstre est présent est la condition de victoire du chasseur.");
            titres.add("Commandes du monstre");
            paragraphes.add(
                    "Pour le joueur incarnant le monstre, les seules commandes à utiliser seront les touches du clavier affichées lors de la partie afin de pouvoir se déplacer.");
            titres.add("Commandes du chasseur");
            paragraphes.add(
                    "En ce qui concerne le chasseur, ce dernier utilisera la souris afin de d'abord sélectionner une case sur laquelle sur souhaite tirer, puis appuyer sur le bouton pour valider le tir.");
            titres.add("Jouer avec des IA");
            paragraphes.add(
                    "Il est également possible de jouer un des deux rôles et d'affronter une IA ou bien d'observer deux IA s'affronter.");
            howToPlayMenu(primaryStage, titres, paragraphes, animation); // Passage au menu des règles du jeu affiché
                                                                         // dans le
            // Stage d'origine et avec la Map des règles
        });

        // Definition du bouton pour quitter
        Button quit = new Button("Quitter");
        VBox.setMargin(quit, new Insets(20, 0, 0, 0));
        quit.setPrefWidth(245);
        quit.setMaxHeight(45);
        quit.setOnAction(e -> { // Ce que le bouton fait quand l'on clique dessus
            System.exit(0); // ici, le bouton ferme le processus
        });

        // Definition de la box principale contenant tous les élements
        VBox root = new VBox(logo, play, howToPlay, credit, quit);
        root.getStyleClass().add("root");

        // Definition du fond de menu animé
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("img/background.png")));
        background.setFitHeight(624);
        background.setPreserveRatio(true);

        StackPane stackPane = new StackPane(background, animation.getStackPane(), root);

        Scene scene = new Scene(stackPane, 984, 624);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm()); // Feuille de style
                                                                                              // contenant toutes les
                                                                                              // indications pour les
                                                                                              // élements du menu
        Image cursorImage = new Image(getClass().getResource("img/cursor.png").toExternalForm());
        scene.setCursor(new ImageCursor(cursorImage));
        for (Node n : root.getChildren().subList(1, root.getChildren().size())) {
            n.setOnMouseEntered(e -> {
                scene.setCursor(new ImageCursor(new Image(getClass().getResource("img/hand.png").toExternalForm())));
            });
            n.setOnMouseExited(e -> {
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
     * 
     * @param baseStage   le stage de base.
     * @param titres      les titres des paragraphes
     * @param paragraphes les règles du jeu.
     * @param animation   l'animation du menu.
     */
    public void howToPlayMenu(Stage baseStage, ArrayList<String> titres, ArrayList<String> paragraphes,
            MenuAnimation animation) {
        Scene oldScene = baseStage.getScene();
        Label titre = new Label("Comment jouer ?");
        titre.getStyleClass().add("mainTitre");
        VBox.setMargin(titre, new Insets(20, 0, 10, 0));
        Button back = new Button("Retour au menu");
        back.getStyleClass().add("playButton");
        back.setOnAction(e -> {
            baseStage.setScene(oldScene);
            animation.play();
            ;
        });

        VBox.setMargin(back, new Insets(20, 0, 20, 0));
        VBox root = new VBox(titre);
        HBox.setMargin(root, new Insets(0, 0, 0, 190));
        root.getStyleClass().add("root");
        for (int i = 0; i < 4; i++) {
            Label secondTitre = new Label(titres.get(i));
            secondTitre.getStyleClass().add("secondTitre");
            secondTitre.setWrapText(true);
            secondTitre.setMaxWidth(570);
            VBox.setMargin(secondTitre, new Insets(15, 0, 15, 0));
            Label text = new Label(paragraphes.get(i));
            text.setWrapText(true);
            text.setMaxWidth(600);
            VBox.setMargin(text, new Insets(0, 0, 45, 0));
            text.setTextAlignment(TextAlignment.JUSTIFY);
            root.getChildren().add(root.getChildren().size(), secondTitre);
            root.getChildren().add(root.getChildren().size(), text);
        }
        HBox container = new HBox(root);
        container.minWidth(980);
        container.maxWidth(980);
        container.setAlignment(Pos.CENTER);
        ScrollPane pane = new ScrollPane(container);
        pane.setVvalue(0);
        VBox tot = new VBox(pane, back);
        Scene newScene = new Scene(tot, oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        baseStage.setScene(newScene);
    }

    /**
     * Méthode qui permet de lancer le menu de jeu.
     * 
     * @param baseStage le stage de base.
     */
    public void playMenu(Stage baseStage, MenuAnimation menuAnimation) {
        Scene oldScene = baseStage.getScene();

        //////////////////////////////////////////////////
        // Menu de configuration des options (NE PAS RETIRER LES '/' DE SÉPARATION SVP)
        //////////////////////////////////////////////////
        // Option Taille Labyrinthe
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            /* MAKE NUMERIC ONLY */
            String digitsOnlyFrom1To10 = "[1-9]|" + MAXSIZE;

            // Check if the new text is empty or matches the allowed pattern
            if (newText.isEmpty() || newText.matches(digitsOnlyFrom1To10)) {
                return change;
            }
            // Reject the change if it doesn't meet the criteria
            return null;
        };

        TextField largeurField = new TextField(); // TextField contenant uniquement des chiffres pour définir la taille
                                                  // du labyrinthe
        largeurField.setTextFormatter(
                new TextFormatter<>(integerFilter));
        largeurField.setText(String.valueOf(DEFAULTSIZE));
        largeurField.setMaxWidth(50);
        largeurField.setMinWidth(50);

        TextField longueurField = new TextField(); // TextField contenant uniquement des chiffres pour définir la taille
                                                   // du labyrinthe
        longueurField.setTextFormatter(
                new TextFormatter<>(integerFilter));
        longueurField.setText(String.valueOf(DEFAULTSIZE));
        longueurField.setMaxWidth(50);
        longueurField.setMinWidth(50);

        if (SQUAREONLY) {
            // Ajout d'un écouteur de changement à longueurField
            longueurField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                // Met à jour la valeur du largeurField avec la nouvelle valeur du longueurField
                largeurField.setText(newValue);
            });

            // Ajout d'un écouteur de changement à largeurField
            largeurField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                // Met à jour la valeur du longueurField avec la nouvelle valeur du largeurField
                longueurField.setText(newValue);
            });
        }

        Label multiplicationSymbol = new Label(" X "); // Format Taille : 10 X 10
        multiplicationSymbol.setMinHeight(25);
        HBox mazeSizeInputs = new HBox();
        mazeSizeInputs.getStyleClass().add("center");
        mazeSizeInputs.getChildren().addAll(longueurField, multiplicationSymbol, largeurField);

        Label sizeLabel = new Label("Taille du Labyrinthe");
        sizeLabel.setWrapText(true);

        // Bouton pour repasser la taille par défaut
        Button resetSizeButton = new Button("Réinitialiser");
        resetSizeButton.getStyleClass().add("optionMenuButton");
        resetSizeButton.setMinWidth(115);
        resetSizeButton.setMaxWidth(115);
        resetSizeButton.setOnAction(e -> {
            longueurField.setText(String.valueOf(DEFAULTSIZE));
            largeurField.setText(String.valueOf(DEFAULTSIZE));
        });

        VBox optionMazeSize = new VBox(); // Colonne d'option de configuration de la taille du labyrinthe
        VBox containerSize = new VBox();
        containerSize.getStyleClass().add("optionContainerSize");
        // Menu regroupant l'ensemble des options configurables de la partie
        VBox options = new VBox();
        HBox optionsMenu = new HBox();
        options.getChildren().addAll(optionsMenu);
        // Ajout des éléments de la configuration de la taille du labyrinthe à la
        // colonne
        containerSize.getChildren().addAll(sizeLabel, mazeSizeInputs, resetSizeButton);
        optionMazeSize.getChildren().add(containerSize);
        optionMazeSize.setSpacing(7.5);

        //////////////////////////////////////////////////
        // Option Pourcentage Labyrinthe
        VBox optionPercentageObs = new VBox();
        VBox containerPercent = new VBox();
        containerPercent.getStyleClass().add("optionContainerPercent");
        Label percentLabel = new Label("Pourcentage d'obstacles");
        percentLabel.setWrapText(true);
        Slider percentSlider = new Slider(25, 75, 5);
        percentSlider.setShowTickLabels(true);
        percentSlider.setShowTickMarks(true);
        percentSlider.setSnapToTicks(true);

        // Bouton pour repasser le pourcentage par défaut
        Button resetObsButton = new Button("Réinitialiser");
        resetObsButton.getStyleClass().add("optionMenuButton");
        resetObsButton.setMinWidth(115);
        resetObsButton.setMaxWidth(115);
        resetObsButton.setOnAction(e -> {
            percentSlider.setValue(DEFAULTOBS);
        });

        containerPercent.getChildren().addAll(percentLabel, percentSlider, resetObsButton);
        optionPercentageObs.getChildren().add(containerPercent);
        //////////////////////////////////////////////////
        // Option Rayon du champ de vision du Monstre
        VBox optionFog = new VBox();
        VBox containerFog = new VBox();
        containerFog.getStyleClass().add("optionContainerFog");
        Label fogLabel = new Label("Champ de vision du monstre");
        fogLabel.setWrapText(true);
        Slider fogSlider = new Slider(1, 5, 1);

        fogSlider.setShowTickLabels(true);
        fogSlider.setShowTickMarks(true);
        fogSlider.setSnapToTicks(true);
        fogSlider.setBlockIncrement(1);

        // Bouton pour repasser le rayon par défaut
        Button resetFogButton = new Button("Réinitialiser");
        resetFogButton.getStyleClass().add("optionMenuButton");
        resetFogButton.setMinWidth(115);
        resetFogButton.setMaxWidth(115);
        resetFogButton.setOnAction(e -> {
            fogSlider.setValue(DEFAULTFOG);
        });

        containerFog.getChildren().addAll(fogLabel, fogSlider, resetFogButton);
        optionFog.getChildren().add(containerFog);
        //////////////////////////////////////////////////
        optionMazeSize.getStyleClass().add("optionBox");
        optionPercentageObs.getStyleClass().add("optionBox");
        optionFog.getStyleClass().add("optionBox");
        //////////////////////////////////////////////////
        // Ajout des différents blocs d'options au bloc général
        optionsMenu.setSpacing(50);
        optionsMenu.getStyleClass().add("center");
        optionsMenu.getChildren().addAll(optionMazeSize, optionPercentageObs, optionFog);
        //////////////////////////////////////////////////

        DifficultySlider difficulty = new DifficultySlider();
        difficulty.setPrefWidth(350);
        difficulty.setMaxWidth(350);
        difficulty.getStyleClass().add("center");

        TextField firstPlayerLabel = new TextField("Joueur 1");// Faire les fonctionnalitées de changement de nom plus
                                                               // tard
        TextField secondPlayerLabel = new TextField("Joueur 2");// Faire les fonctionnalitées de changement de nom plus
                                                                // tard

        VBox root = new VBox();
        HBox bottom = new HBox();

        HBox top = new HBox(50);
        top.setPrefSize(oldScene.getWidth(), oldScene.getHeight() / 8);
        top.getStyleClass().add("top");

        VBox localBox = new VBox();
        localBox.setSpacing(80);
        localBox.getStyleClass().add("defaultConfigBox");

        VBox iaBox = new VBox();
        iaBox.setSpacing(80);
        iaBox.getStyleClass().add("defaultConfigBox");

        localBox.setPrefSize(oldScene.getWidth(), (oldScene.getHeight() / 8) * 6);
        localBox.getStyleClass().add("center");
        iaBox.setPrefSize(oldScene.getWidth(), (oldScene.getHeight() / 8) * 6);
        iaBox.getStyleClass().add("center");

        HBox playerInput = new HBox(firstPlayerLabel, new Label("VS"), secondPlayerLabel);
        playerInput.getStyleClass().add("center");
        localBox.getChildren().addAll(playerInput, difficulty);

        // Onglet de sélection entre partie par défaut et partie custom
        Tooltip defaultConfig = new Tooltip("Configure la partie avec des niveaux de difficultés par défaut.");
        defaultConfig.setShowDelay(Duration.millis(50));
        defaultConfig.getStyleClass().add("tooltip");
        defaultConfig.setWrapText(true);
        Tooltip customConfig = new Tooltip("Configure la partie avec des options avancées personnalisables");
        customConfig.setShowDelay(Duration.millis(50));
        customConfig.getStyleClass().add("tooltip");
        customConfig.setWrapText(true);
        ToggleGroup configRadioGroup = new ToggleGroup();
        RadioButton defaults = new RadioButton(DEFAULTCONFIG);
        defaults.setToggleGroup(configRadioGroup);
        defaults.getStyleClass().remove("radio-button");
        defaults.getStyleClass().add("configRadioButton");
        defaults.getStyleClass().add("defaultConfigRadioButton");
        defaults.setSelected(true);
        HBox defaultsContainer = new HBox();
        Image defaultsTooltipImg = new Image(getClass().getResource("img/tooltipIcon.png").toExternalForm());
        ImageView defaultsTooltipImgView = new ImageView(defaultsTooltipImg);
        VBox defaultsTooltipImgViewContainer = new VBox();
        defaultsTooltipImgViewContainer.getChildren().add(defaultsTooltipImgView);
        defaultsTooltipImgViewContainer.getStyleClass().add("center");
        defaultsTooltipImgView.setFitHeight(30);
        defaultsTooltipImgView.setFitWidth(30);
        Tooltip.install(defaultsTooltipImgViewContainer, defaultConfig);
        defaultsContainer.getChildren().addAll(defaultsTooltipImgViewContainer, defaults);
        RadioButton custom = new RadioButton(CUSTOMCONFIG);
        custom.setToggleGroup(configRadioGroup);
        custom.getStyleClass().remove("radio-button");
        custom.getStyleClass().add("configRadioButton");
        custom.getStyleClass().add("customConfigRadioButton");
        HBox customContainer = new HBox();
        Image customTooltipImg = new Image("menu/img/tooltipIcon.png");
        ImageView customTooltipImgView = new ImageView(customTooltipImg);
        VBox customTooltipImgViewContainer = new VBox();
        customTooltipImgViewContainer.getChildren().add(customTooltipImgView);
        customTooltipImgViewContainer.getStyleClass().add("center");
        customTooltipImgView.setFitHeight(30);
        customTooltipImgView.setFitWidth(30);
        Tooltip.install(customTooltipImgViewContainer, customConfig);
        customContainer.getChildren().addAll(custom, customTooltipImgViewContainer);
        HBox radioButtons = new HBox();
        radioButtons.setSpacing(100);
        radioButtons.getStyleClass().add("center");
        VBox selectMode = new VBox();
        VBox space = new VBox();
        space.setMaxHeight(10);
        space.setMinHeight(10);
        radioButtons.getChildren().addAll(defaultsContainer, customContainer);
        selectMode.getChildren().addAll(space, radioButtons);
        selectMode.getStyleClass().add("background");
        //////////////////////////////////////////////////

        Button joueurVsJoueurOnglet = new Button("Local 1V1");
        joueurVsJoueurOnglet.setOnAction(e -> {
            enableIA = false;
            defaults.setSelected(true); // Repasse le radio button en par défaut
            root.getChildren().clear();
            localBox.getChildren().clear();
            localBox.getChildren().addAll(playerInput, difficulty);
            root.getChildren().addAll(top, selectMode, localBox, bottom);
        });
        Button iaVsIaOnglet = new Button("IA");

        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("IA Chasseur VS Monstre");
        rb1.getStyleClass().remove("radio-button");
        rb1.setWrapText(true);
        rb1.setTextAlignment(TextAlignment.CENTER);
        RadioButton rb2 = new RadioButton("IA Monstre VS Chasseur");
        rb2.getStyleClass().remove("radio-button");
        rb2.setWrapText(true);
        rb2.setTextAlignment(TextAlignment.CENTER);
        RadioButton rb3 = new RadioButton("IA Chasseur VS IA Monstre");
        rb3.getStyleClass().remove("radio-button");
        rb3.setWrapText(true);
        rb3.setTextAlignment(TextAlignment.CENTER);

        VBox rb1Container = new VBox();
        rb1Container.getChildren().add(rb1);
        rb1Container.getStyleClass().add("radioButtonContainer");
        VBox rb2Container = new VBox();
        rb2Container.getChildren().add(rb2);
        rb2Container.getStyleClass().add("radioButtonContainer");
        VBox rb3Container = new VBox();
        rb3Container.getChildren().add(rb3);
        rb3Container.getStyleClass().add("radioButtonContainer");

        group.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton)group.getSelectedToggle();

            if (rb != null && rb.getText().equals("IA Chasseur VS Monstre")){
                rb1.getStyleClass().add("radioButtonContainerToggle");
                rb2.getStyleClass().remove("radioButtonContainerToggle");
                rb3.getStyleClass().remove("radioButtonContainerToggle");
            } else if (rb != null && rb.getText().equals("IA Monstre VS Chasseur")) {
                rb1.getStyleClass().remove("radioButtonContainerToggle");
                rb2.getStyleClass().add("radioButtonContainerToggle");
                rb3.getStyleClass().remove("radioButtonContainerToggle");
            } else if (rb != null && rb.getText().equals("IA Chasseur VS IA Monstre")) {
                rb1.getStyleClass().remove("radioButtonContainerToggle");
                rb2.getStyleClass().remove("radioButtonContainerToggle");
                rb3.getStyleClass().add("radioButtonContainerToggle");
            }
        });

        HBox gameModeField = new HBox(rb1Container, rb2Container, rb3Container);
        gameModeField.setSpacing(50);
        gameModeField.getStyleClass().add("center");

        iaVsIaOnglet.setOnAction(e -> {
            enableIA = true;
            defaults.setSelected(true); // Repasse le radio button en par défaut
            root.getChildren().clear();

            rb1.setToggleGroup(group);
            rb1.setSelected(true);

            rb2.setToggleGroup(group);

            rb3.setToggleGroup(group);

            iaBox.getChildren().clear();
            iaBox.getChildren().addAll(gameModeField, difficulty);
            root.getChildren().addAll(top, selectMode, iaBox, bottom);

        });

        top.getChildren().addAll(joueurVsJoueurOnglet, iaVsIaOnglet);
        for (Node n : top.getChildren())
            ((Button) n).setPrefWidth(oldScene.getWidth() / (top.getChildren().size() + 1));

        Button back = new Button("Menu");
        back.setOnAction(e -> {
            baseStage.setScene(oldScene);
            menuAnimation.play();
        });

        // Gestion du Bouton d'activation des options personnalisées de la partie
        configRadioGroup.selectedToggleProperty().addListener((ob, o, n) -> {

            RadioButton rb = (RadioButton) configRadioGroup.getSelectedToggle();

            String defaultStyle = "defaultConfigBox";
            String customStyle = "customConfigBox";

            if (rb != null && rb.getText().equals(DEFAULTCONFIG)) {
                enableCustom = false;
                if (root.getChildren().get(2) == localBox) {
                    localBox.getChildren().clear();
                    localBox.getChildren().addAll(playerInput, difficulty);
                    localBox.getStyleClass().remove(customStyle);
                    localBox.getStyleClass().add(defaultStyle);
                }
                if (root.getChildren().get(2) == iaBox) {
                    iaBox.getChildren().clear();
                    iaBox.getChildren().addAll(gameModeField, difficulty);
                    iaBox.getStyleClass().remove(customStyle);
                    iaBox.getStyleClass().add(defaultStyle);
                }
            } else if (rb != null && rb.getText().equals(CUSTOMCONFIG)) {
                enableCustom = true;
                if (root.getChildren().get(2) == localBox) {
                    localBox.getChildren().clear();
                    localBox.getChildren().addAll(playerInput, options);
                    localBox.getStyleClass().remove(defaultStyle);
                    localBox.getStyleClass().add(customStyle);
                }
                if (root.getChildren().get(2) == iaBox) {
                    iaBox.getChildren().clear();
                    iaBox.getChildren().addAll(gameModeField, options);
                    iaBox.getStyleClass().remove(defaultStyle);
                    iaBox.getStyleClass().add(customStyle);
                }
            }
        });

        Button play = new Button("Lancer");
        play.setOnAction(e -> {
            if (!belowMinSize(longueurField)) {
                GameParameter parameters = new GameParameter();
                parameters.setDifficulty(Difficulty.fromInt((int) difficulty.getValue()));
                parameters.setFirstPlayerName(firstPlayerLabel.getText());
                parameters.setSecondPlayerName(secondPlayerLabel.getText());

                if (enableCustom) {
                    parameters.setLongueur(Integer.parseInt(longueurField.getText()));
                    parameters.setLargeur(Integer.parseInt(largeurField.getText()));
                    parameters.setPourcentageObs((int) percentSlider.getValue());
                    parameters.setFogRange((int) fogSlider.getValue());
                } else {
                    parameters.setLongueur(parameters.getDifficulty().getColumnsDifficulty());
                    parameters.setLargeur(parameters.getDifficulty().getRowsDifficulty());
                    parameters.setPourcentageObs(parameters.getDifficulty().getNbObstaclesDifficulty());
                    parameters.setFogRange(parameters.getDifficulty().getFogRange());
                }
                if (enableIA) {
                    if (rb1.isSelected()) {
                        parameters.setIaHunter(true);
                        parameters.setIaMonster(false);
                    } else if (rb2.isSelected()) {
                        parameters.setIaHunter(false);
                        parameters.setIaMonster(true);
                    } else if (rb3.isSelected()) {
                        parameters.setIaHunter(true);
                        parameters.setIaMonster(true);
                    }
                }

                IHM ihm = new IHM(parameters);
                baseStage.close();
                ihm.start(baseStage);
            } else {
                PopUpMazeSize p = new PopUpMazeSize();
                p.setMINSIZE(MINSIZE);
                try {
                    p.start(baseStage);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        bottom.getChildren().addAll(back, play);
        bottom.setPrefSize(oldScene.getWidth(), oldScene.getHeight() / 8);
        bottom.getStyleClass().add("bottom");
        for (Node n : bottom.getChildren())
            ((Button) n).setPrefWidth(oldScene.getWidth() / (bottom.getChildren().size() + 2));
        HBox.setMargin(back, new Insets(0, oldScene.getWidth() - 2 * 200 - 140, 0, 0));

        HBox.setMargin(firstPlayerLabel, new Insets(0, 30, 0, 0));
        HBox.setMargin(secondPlayerLabel, new Insets(0, 0, 0, 30));

        root.getChildren().addAll(top, selectMode, localBox, bottom);
        Scene newScene = new Scene(root, oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        baseStage.setScene(newScene);
    }

    /**
     * Méthode qui permet de vérifier si la taille du labyrinthe saisie est
     * inférieure à la taille du labyrinthe minimale.
     *
     * @param tf le champ de saisie personnalisé pour la taille du labyrinthe
     * @return true si la taille du labyrinthe saisie est inférieure à la taille du
     *         labyrinthe minimale, false sinon
     */
    boolean belowMinSize(TextField tf) {
        return Integer.parseInt(tf.getText()) < MINSIZE;
    }

    /**
     * Méthode qui permet de lancer le jeu.
     * 
     * @param args les arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
