package be.atemi.decision.parentime.james.constraint.hard;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import org.jamesframework.core.problems.constraints.Constraint;
import org.jamesframework.core.problems.constraints.validations.Validation;
import org.jamesframework.core.subset.SubsetSolution;

public class FullNightMorningConstraint implements Constraint<CirclePlanningSolution, CirclePlanningData> {

    @Override
    public Validation validate(CirclePlanningSolution solution, CirclePlanningData data) {
        return null;
    }
}
