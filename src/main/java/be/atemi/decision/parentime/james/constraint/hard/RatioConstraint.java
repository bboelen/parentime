package be.atemi.decision.parentime.james.constraint.hard;

import be.atemi.decision.parentime.james.CirclePlanningData;
import org.jamesframework.core.problems.constraints.Constraint;
import org.jamesframework.core.problems.constraints.validations.Validation;
import org.jamesframework.core.subset.SubsetSolution;

public class RatioConstraint implements Constraint<SubsetSolution, CirclePlanningData> {

    @Override
    public Validation validate(SubsetSolution solution, CirclePlanningData data) {
        return null;
    }
}
