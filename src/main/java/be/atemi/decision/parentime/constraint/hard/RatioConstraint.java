package be.atemi.decision.parentime.constraint.hard;

import be.atemi.decision.parentime.constraint.HardConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public class RatioConstraint extends HardConstraint {

    @Override
    public double cost(Genotype<StepfamilyGene> genotype) {
        return 0;
    }

    @Override
    public int weight() {
        return 0;
    }
}
