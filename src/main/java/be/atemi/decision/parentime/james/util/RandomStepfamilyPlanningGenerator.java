package be.atemi.decision.parentime.james.util;

import be.atemi.decision.parentime.helper.RandomStepfamilySelector;
import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.StepfamilyPlanning;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;

import java.util.*;

public final class RandomStepfamilyPlanningGenerator {

    public static StepfamilyPlanning generate(Random random, Person child, CirclePlanningData data) {

        List<Stepfamily> planning = new ArrayList(data.days() * data.timeslots());

        int n = data.days() * data.timeslots();
        // We assume that a child has at most two stepfamilies.
        int loop = 0;
        Map<Stepfamily, Integer> counterMap = new HashMap<>();
        for (Stepfamily f : child.stepfamilies()) {
            counterMap.put(f, loop > 0 ? n - (n / 2) : n / 2);
            loop++;
        }

        for (int d = 0; d < data.days(); d++) {
            for (int t = 0; t < data.timeslots(); t++) {
                planning.add(t + d * data.timeslots(), nextFamily(random, child, counterMap));
            }
        }

        return new StepfamilyPlanning(planning, data.days(), data.timeslots());
    }

    private static Stepfamily nextFamily(Random random, Person child, Map<Stepfamily, Integer> counterMap) {

        Stepfamily stepfamily = RandomStepfamilySelector.nextFamily(random, child);

        if (!counterMap.get(stepfamily).equals(0)) {
            counterMap.put(stepfamily, counterMap.get(stepfamily) - 1);
            return stepfamily;
        } else {
            return nextFamily(random, child, counterMap);
        }
    }
}
