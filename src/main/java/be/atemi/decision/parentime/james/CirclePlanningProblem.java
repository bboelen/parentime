package be.atemi.decision.parentime.james;

import org.jamesframework.core.problems.GenericProblem;
import org.jamesframework.core.problems.datatypes.IntegerIdentifiedData;
import org.jamesframework.core.problems.objectives.Objective;
import org.jamesframework.core.problems.sol.RandomSolutionGenerator;

public class CirclePlanningProblem<DataType extends IntegerIdentifiedData> extends GenericProblem<CirclePlanningSolution, DataType> {

    public CirclePlanningProblem(DataType data, Objective<? super CirclePlanningSolution, ? super DataType> objective, RandomSolutionGenerator<? extends CirclePlanningSolution, ? super DataType> randomSolutionGenerator) {
        super(data, objective, randomSolutionGenerator);
    }
}
