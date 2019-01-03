package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;

public class MinTransitionConstraint extends SoftConstraint {

    @Override
    public int cost(int chromosomeIndex, StepfamilyGene[] genes, int timeslots, int days) {

        int cost = 0;

        Integer previousFamily = null;

        for (int i = 0; i < genes.length; i++) {

            if (!genes[i].getAllele().getId().equals(previousFamily)) {
                cost++;
                previousFamily = genes[i].getAllele().getId();
            }
        }

        return cost > 0 ? 1 : 0;
    }
}
