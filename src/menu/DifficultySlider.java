package menu;

import game.model.Difficulty;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;

public class DifficultySlider extends Slider{
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
