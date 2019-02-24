package be.atemi.decision.parentime.jenetics;

import io.jenetics.Genotype;
import io.jenetics.engine.EvolutionStatistics;

public class BestCirclePlanningGenotype {

    private Genotype<StepfamilyGene> genotype;
    private EvolutionStatistics<Double, ?> statistics;

    public BestCirclePlanningGenotype(Genotype<StepfamilyGene> genotype, EvolutionStatistics<Double, ?> statistics) {
        this.genotype = genotype;
        this.statistics = statistics;
    }

    public Genotype<StepfamilyGene> getGenotype() {
        return genotype;
    }

    public EvolutionStatistics<Double, ?> getStatistics() {
        return statistics;
    }
}

