package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public class MinTransitionConstraint extends SoftConstraint {

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

            int cost = 0;

            Integer previousFamily = null;

            for (int d = 0; d < chromosome.days(); d++) {
                for (int t = 0; t < chromosome.timeslots(); t++) {

                    if (previousFamily == null) {
                        previousFamily = chromosome.getGene(t + d * chromosome.timeslots()).getAllele().getId();
                    } else if (!chromosome.getGene(t + d * chromosome.timeslots()).getAllele().getId().equals(previousFamily)) {
                        cost++;
                        previousFamily = chromosome.getGene(t + d * chromosome.timeslots()).getAllele().getId();
                    }
                }
            }

            conflicts += cost;
        }

        if (conflicts > MAX) {
            MAX = conflicts;
        }

        return conflicts;
    }

    @Override
    public int max() {
        return 28;
    }

    @Override
    public int weight() {
        return 100;
    }
}
