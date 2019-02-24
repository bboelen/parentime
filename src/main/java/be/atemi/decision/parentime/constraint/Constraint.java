package be.atemi.decision.parentime.constraint;

import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public abstract class Constraint {

    public abstract double cost(Genotype<StepfamilyGene> genotype);

    public abstract int weight();
}
