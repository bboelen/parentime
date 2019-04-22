package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.model.Stepfamily;
import be.atemi.decision.parentime.model.Timable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class CirclePlanning<T extends Timable> extends AbstractTimeGrid<T> {

    private static final String[] COLORS = {
            "-fx-background-color: gold;",
            "-fx-background-color: dodgerblue;",
            "-fx-background-color: indianred;",
            "-fx-background-color: cadetblue;",
            "-fx-background-color: lightsalmon;",
            "-fx-background-color: aliceblue;",
            "-fx-background-color: coral;"};

    private static int colorIndex = 0;

    protected static Map<Integer, String> stepfamilyColors = new HashMap<>();

    protected static void assignColors(Set<Stepfamily> stepfamilies) {
        try {
            stepfamilies.forEach(stepfamily -> stepfamilyColors.put(stepfamily.getId(), COLORS[colorIndex++]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("There are not enough colors to assign! You must add more.");
        }
    }
}
