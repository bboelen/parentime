package be.atemi.decision.parentime.constraint;

import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public abstract class HardConstraint extends Constraint {


    @Override
    public int min() {
        return 0;
    }

    public double weightedCost(Genotype<StepfamilyGene> genotype) {
        return weight() * ((cost(genotype) - min()) / (max() - min()));
    }
}
