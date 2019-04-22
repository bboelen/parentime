package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import org.jamesframework.core.problems.objectives.Objective;
import org.jamesframework.core.problems.objectives.evaluations.Evaluation;
import org.jamesframework.core.problems.objectives.evaluations.SimpleEvaluation;

import java.util.List;
import java.util.Map;

/**
 * Minimizing transitions Objective
 */
public class CirclePlanningObjective implements Objective<CirclePlanningSolution, CirclePlanningData> {

    @Override
    public Evaluation evaluate(CirclePlanningSolution solution, CirclePlanningData data) {

        double value = 0;

        for (Map.Entry<Person, List<Stepfamily>> entry : solution.getDeltaStructure().entrySet()) {

            Stepfamily stepfamily = null;

            for (Stepfamily sf : entry.getValue()) {

                if (stepfamily == null) {
                    stepfamily = sf;
                } else if (!sf.getId().equals(stepfamily.getId())) {
                    value++;
                    stepfamily = sf;
                }
            }
        }

        return SimpleEvaluation.WITH_VALUE(value);
    }

    @Override
    public boolean isMinimizing() {
        return true;
    }
}
