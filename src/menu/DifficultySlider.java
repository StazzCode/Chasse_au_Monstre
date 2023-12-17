package menu;

import javafx.scene.control.Slider;
import javafx.util.StringConverter;

/**
 * La classe DifficultySlider représente un curseur de difficulté personnalisé pour un menu.
 * Il hérite de la classe Slider de JavaFX.
 */
public class DifficultySlider extends Slider{
    /**
     * Constructeur par défaut de la classe DifficultySlider.
     * Initialise le curseur avec une plage de valeurs de 0 à 4 et une valeur initiale de 0.
     * Configure les propriétés du curseur pour afficher des marques de graduation et des étiquettes correspondantes.
     * Définit également un convertisseur de chaîne personnalisé pour afficher les étiquettes de difficulté en français.
     */
    public DifficultySlider(){
        super(0, 4, 0);

        setMin(0);
        setMax(4);
        setValue(1);

        setMajorTickUnit(1);
        setSnapToTicks(true);
        setShowTickMarks(true);
        setShowTickLabels(true);

        setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "Très facile";
                if (n < 1.5) return "Facile";
                if (n < 2.5) return "Moyen";
                if (n < 3.5) return "Difficile";

                return "Très difficile";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Très facile":
                        return 0d;
                    case "Facile":
                        return 1d;
                    case "Moyen":
                        return 2d;
                    case "Difficile":
                        return 3d;
                    case "Très difficile":
                        return 4d;
                    default:
                        return 3d;
                }
            }
        });

    }
}
