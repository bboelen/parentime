package be.atemi.decision.parentime.jenetics.constraint.hard;

import be.atemi.decision.parentime.jenetics.constraint.HardConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

import java.util.HashMap;
import java.util.Map;

public class RatioConstraint extends HardConstraint {

    public static int MAX = 0;

    @Override
    public double cost(Genotype<StepfamilyGene> genotype) {

        StepfamilyChromosome[] chromosomes = new StepfamilyChromosome[genotype.length()];
        genotype.toSeq().toArray(chromosomes);

        int conflicts = 0;

        for (int c = 0; c < chromosomes.length; c++) {

            StepfamilyChromosome chromosome = chromosomes[c].as(StepfamilyChromosome.class);
            StepfamilyGene[] genes = new StepfamilyGene[chromosome.timeslots() * chromosome.days()];
            chromosome.toSeq().toArray(genes);

            Map<Integer, Integer> ratios = new HashMap<>();

            for (int i = 0; i < genes.length; i++) {

                int id = genes[i].getAllele().getId();

                if (!ratios.containsKey(id)) {
                    ratios.put(id, 1);
                }

                ratios.put(id, ratios.get(id) + 1);
            }

            int cost = -1;

            for (Map.Entry<Integer, Integer> entry : ratios.entrySet()) {
                cost = (cost == -1) ? entry.getValue() : (cost - entry.getValue());

            }

            conflicts += Math.abs(cost);
        }

        if(conflicts > MAX) {
            MAX = conflicts;
        }

        return conflicts;
    }

    @Override
    public int max() {
        return 112;
    }

    @Override
    public int weight() {
        return 1000;
    }
}
