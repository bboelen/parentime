package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.FamilyGene;

public class FullNightMorningConstraint extends SoftConstraint {

    @Override
    public int cost(int chromosomeIndex, FamilyGene[] genes, int timeslots, int days) {

        int cost = 0;

        for (int d = 1; d <= days; d++) {

            int evening_1 = genes[(d - 1) * timeslots + 6].getAllele().getId();
            int evening_2 = genes[(d - 1) * timeslots + 7].getAllele().getId();

            int nextDay = (d < days) ? d * timeslots : 0;

            int night_1 = genes[nextDay + 0].getAllele().getId();
            int night_2 = genes[nextDay + 1].getAllele().getId();

            if (evening_1 != evening_2) {
                cost++;
            }
            if (evening_1 != night_1) {
                cost++;
            }
            if (evening_1 != night_2) {
                cost++;
            }


           // System.out.println("fullNightMorning ( day " + d + "-" + (d + 1) + ") : " + evening_1 + " - " + evening_2 + " - " + night_1 + " - " + night_2 + " ---- cost : " + cost);
        }

        // System.out.println("overall cost : " + (cost));

        return cost ;
    }
}
