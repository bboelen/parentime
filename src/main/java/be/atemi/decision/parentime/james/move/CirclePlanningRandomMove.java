package be.atemi.decision.parentime.james.move;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.util.RandomStepfamilyPlanningGenerator;
import be.atemi.decision.parentime.model.Person;
import org.jamesframework.core.search.neigh.Move;

import java.util.Random;

public class CirclePlanningRandomMove implements Move<CirclePlanningSolution> {

    private final Person child;

    private final CirclePlanningData data;

    private CirclePlanningSolution undo;

    public CirclePlanningRandomMove(Person child, CirclePlanningData data) {
        this.child = child;
        this.data = data;
    }

    public Person getChild() {
        return child;
    }

    public CirclePlanningData getData() {
        return data;
    }

    @Override
    public void apply(CirclePlanningSolution solution) {

        // cache current solution for possible future undo
        this.undo = solution;
        solution.getDeltaStructure().put(child, RandomStepfamilyPlanningGenerator.generate(new Random(), child, data));
    }

    @Override
    public void undo(CirclePlanningSolution solution) {
        solution.setDeltaStructure(undo.getDeltaStructure());
    }
}
