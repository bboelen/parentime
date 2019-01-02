package be.atemi.decision.parentime.constraint.hard;

import be.atemi.decision.parentime.constraint.HardConstraint;
import be.atemi.decision.parentime.jenetics.FamilyGene;

public class AvailabilityConstraint extends HardConstraint {



    @Override
    public int cost(int chromosomeIndex, FamilyGene[] genes, int timeslots, int days) {
        return 0;
    }
}
