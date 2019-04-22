package be.atemi.decision.parentime.james.constraint.hard;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.constraint.HardConstraint;
import be.atemi.decision.parentime.model.Agenda;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;

public class AvailabilityConstraint extends HardConstraint {

    public static int MAX = 0;

    @Override
    public double cost(CirclePlanningSolution solution, CirclePlanningData data) {

        int conflicts = 0;

        for (Person child : solution.getDeltaStructure().keySet()) {

            for (int d = 0; d < data.days(); d++) {
                for (int t = 0; t < data.timeslots(); t++) {

                    Stepfamily stepfamily = solution.getDeltaStructure().get(child).get(t + d * data.timeslots());

                    boolean isAvailable = true;

                    for (Person person : stepfamily.getTutors()) {

                        if (person.getAgenda().getEntry(d, t).equals(Agenda.Entry.FIXED_UNAVAILABILITY)) {
                            isAvailable = false;
                        }
                    }

                    if (!isAvailable) {
                        conflicts++;
                    }
                }
            }
        }

        if (conflicts > MAX) {
            MAX = conflicts;
        }

        return conflicts;

    }

    @Override
    public int weight() {
        return 32;
    }

    @Override
    public int max() {
        return 1000;
    }
}
