package be.atemi.decision.parentime.james.constraint.hard;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.constraint.HardConstraint;
import be.atemi.decision.parentime.model.Person;

public class FullNightMorningConstraint extends HardConstraint {

    public static int MAX = 0;

    @Override
    public double cost(CirclePlanningSolution solution, CirclePlanningData data) {

        int conflicts = 0;

        for (Person child : solution.getDeltaStructure().keySet()) {

            for (int d = 0; d < data.days(); d++) {

                int mIdx = d * data.timeslots();
                int nIdx = d != 0 ? d * data.timeslots() - 1 : data.days() * data.timeslots() - 1;

                int a = solution.getDeltaStructure().get(child).get(mIdx).getId();
                int b = solution.getDeltaStructure().get(child).get(nIdx).getId();

                if (a != b) {
                    conflicts++;
                }
            }
        }

        if (conflicts > MAX) {
            MAX = conflicts;
        }

        return conflicts;
    }

    @Override
    public int max() {
        return 28;
    }

    @Override
    public int weight() {
        return 2000;
    }
}
