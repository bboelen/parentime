package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.FamilyGene;

public class MinTransitionConstraint extends SoftConstraint {

    @Override
    public int cost(int chromosomeIndex, FamilyGene[] genes, int timeslots, int days) {

        int cost = 0;

        String previousFamily = "";

        for (int i = 0; i < genes.length; i++) {

            if (!genes[i].getAllele().getName().equals(previousFamily)) {
                cost++;
                previousFamily = genes[i].getAllele().getName();
            }
        }

        return cost > 0 ? 1 : 0;
    }
}
