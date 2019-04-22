package be.atemi.decision.parentime.james.generator;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.StepfamilyPlanning;
import be.atemi.decision.parentime.james.util.RandomStepfamilyPlanningGenerator;
import be.atemi.decision.parentime.model.Person;
import org.jamesframework.core.problems.sol.RandomSolutionGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * PPGAC Random solution generator (with 50/50 ratio constraint included).
 */
public final class RandomCirclePlanningSolutionGenerator implements RandomSolutionGenerator<CirclePlanningSolution, CirclePlanningData> {

    private static final RandomCirclePlanningSolutionGenerator INSTANCE = new RandomCirclePlanningSolutionGenerator();

    private RandomCirclePlanningSolutionGenerator() {
    }

    @Override
    public CirclePlanningSolution create(Random random, CirclePlanningData data) {

        Map<Person, StepfamilyPlanning> deltaStructure = new HashMap<>();

        for (Person child : data.getCircle().children()) {

            StepfamilyPlanning planning = RandomStepfamilyPlanningGenerator.generate(random, child, data);

            deltaStructure.put(child, planning);
        }

        return new CirclePlanningSolution(deltaStructure);
    }

    public static RandomCirclePlanningSolutionGenerator getInstance() {
        return INSTANCE;
    }
}
