package be.atemi.decision.parentime.james.constraint.soft;

import be.atemi.decision.parentime.james.CirclePlanningData;
import org.jamesframework.core.problems.constraints.PenalizingConstraint;
import org.jamesframework.core.problems.constraints.validations.PenalizingValidation;
import org.jamesframework.core.subset.SubsetSolution;

public class MinTransitionConstraint implements PenalizingConstraint<SubsetSolution, CirclePlanningData> {

    @Override
    public PenalizingValidation validate(SubsetSolution solution, CirclePlanningData data) {
        return null;
    }
}
