package be.atemi.decision.parentime.constraint;

import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public abstract class SoftConstraint extends Constraint {

    protected static int MIN_VALUE = 0;
    protected static int MAX_VALUE = 80;

    public double weightedCost(Genotype<StepfamilyGene> genotype) {
        return weight() * ((cost(genotype) - MIN_VALUE) / (MAX_VALUE - MIN_VALUE));
    }

    @Override
    public int min() {
        return MIN_VALUE;
    }

    @Override
    public int max() {
        return MAX_VALUE;
    }

}
