package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public class MinTransitionConstraint extends SoftConstraint {


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

            for (int i = 0; i < genes.length; i++) {

                if (previousFamily == null) {
                    previousFamily = genes[i].getAllele().getId();
                } else if (!genes[i].getAllele().getId().equals(previousFamily)) {
                    cost++;
                    previousFamily = genes[i].getAllele().getId();
                }
            }

            conflicts += cost;
        }

        return conflicts;
    }

    @Override
    public int weight() {
        return 200;
    }


    //    @Override
//    public int cost(int chromosomeIndex, StepfamilyGene[] genes, int timeslots, int days) {
//
//        int cost = 0;
//
//        Integer previousFamily = null;
//
//        for (int i = 0; i < genes.length; i++) {
//
//            if (!genes[i].getAllele().getId().equals(previousFamily)) {
//                cost++;
//                previousFamily = genes[i].getAllele().getId();
//            }
//        }
//
//        return cost > 0 ? 1 : 0;
//    }
}
