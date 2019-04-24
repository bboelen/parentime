package be.atemi.decision.parentime.helper;

import java.util.HashMap;
import java.util.Map;

public final class ColorGenerator {

    private static final String[] COLORS = {
            "-fx-background-color: gold;",
            "-fx-background-color: dodgerblue;",
            "-fx-background-color: indianred;",
            "-fx-background-color: cadetblue;",
            "-fx-background-color: lightsalmon;",
            "-fx-background-color: aliceblue;",
            "-fx-background-color: coral;",
            "-fx-background-color: bisque;",
            "-fx-background-color: darkslateblue;",
            "-fx-background-color: darkturquoise;",
            "-fx-background-color: darksalmon;",
            "-fx-background-color: darkorange;",
            "-fx-background-color: darkseagreen;",
            "-fx-background-color: darkcyan;"};

    private static int colorIndex = 0;

    private ColorGenerator() {
    }

    protected static Map<Integer, String> stepfamilyColors = new HashMap<>();

    public static String get() {
        return COLORS[colorIndex++];
    }

    public static void reset() {
        colorIndex = 0;
    }
}
