package be.atemi.decision.parentime.jenetics;

import io.jenetics.Genotype;
import io.jenetics.engine.EvolutionStatistics;

public class BestCirclePlanningGenotype {

    private Genotype<StepfamilyGene> genotype;
    private EvolutionStatistics<Integer, ?> statistics;

    public BestCirclePlanningGenotype(Genotype<StepfamilyGene> genotype, EvolutionStatistics<Integer, ?> statistics) {
        this.genotype = genotype;
        this.statistics = statistics;
    }

    public Genotype<StepfamilyGene> getGenotype() {
        return genotype;
    }

    public EvolutionStatistics<Integer, ?> getStatistics() {
        return statistics;
    }
}

