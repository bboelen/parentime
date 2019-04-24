package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.helper.ColorGenerator;
import be.atemi.decision.parentime.model.Stepfamily;
import be.atemi.decision.parentime.model.Timable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class CirclePlanning<T extends Timable> extends AbstractTimeGrid<T> {

    protected static Map<Integer, String> stepfamilyColors = new HashMap<>();

    protected static void assignColors(Set<Stepfamily> stepfamilies) {
        stepfamilies.forEach(stepfamily -> stepfamilyColors.put(stepfamily.getId(), ColorGenerator.get()));
        ColorGenerator.reset();
    }
}
