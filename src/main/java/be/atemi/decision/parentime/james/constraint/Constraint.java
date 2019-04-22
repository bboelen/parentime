package be.atemi.decision.parentime.james.constraint;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import org.jamesframework.core.problems.constraints.PenalizingConstraint;
import org.jamesframework.core.problems.constraints.validations.PenalizingValidation;
import org.jamesframework.core.problems.constraints.validations.SimplePenalizingValidation;

public abstract class Constraint implements PenalizingConstraint<CirclePlanningSolution, CirclePlanningData> {

    @Override
    public PenalizingValidation validate(CirclePlanningSolution solution, CirclePlanningData data) {

        double cost = cost(solution, data);

        if (cost > 0) {
            return SimplePenalizingValidation.FAILED(weightedCost(cost));
        }

        return SimplePenalizingValidation.PASSED;
    }

    public abstract double cost(CirclePlanningSolution solution, CirclePlanningData data);

    public abstract int weight();

    public abstract double weightedCost(double cost);

    public abstract int min();

    public abstract int max();
}
