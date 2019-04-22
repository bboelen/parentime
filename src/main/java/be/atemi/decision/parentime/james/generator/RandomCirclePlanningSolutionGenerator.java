package be.atemi.decision.parentime.james.generator;

import be.atemi.decision.parentime.helper.RandomStepfamilySelector;
import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import org.jamesframework.core.problems.sol.RandomSolutionGenerator;

import java.util.*;

/**
 * PPGAC Random solution generator.
 */
public final class RandomCirclePlanningSolutionGenerator implements RandomSolutionGenerator<CirclePlanningSolution, CirclePlanningData> {

    private static final RandomCirclePlanningSolutionGenerator INSTANCE = new RandomCirclePlanningSolutionGenerator();

    private RandomCirclePlanningSolutionGenerator() {}

    @Override
    public CirclePlanningSolution create(Random random, CirclePlanningData data) {

        Map<Person, List<Stepfamily>> deltaStructure = new HashMap<>();

        for (Person child : data.getCircle().children()) {

            List<Stepfamily> planning = new ArrayList(data.days() * data.timeslots());

            for (int d = 0; d < data.days(); d++) {
                for (int t = 0; t < data.timeslots(); t++) {
                    planning.add(t + d * data.timeslots(), RandomStepfamilySelector.nextFamily(random, child));
                }
            }

            deltaStructure.put(child, planning);
        }

        return new CirclePlanningSolution(deltaStructure);
    }

    public static RandomCirclePlanningSolutionGenerator getInstance() {
        return INSTANCE;
    }
}
