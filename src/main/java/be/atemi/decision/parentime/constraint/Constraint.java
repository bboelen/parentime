package be.atemi.decision.parentime.constraint;

import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public abstract class Constraint {

    public int cost(final Genotype<StepfamilyGene> genotype) {

        int cost = 0;

        StepfamilyChromosome[] chromosomes = new StepfamilyChromosome[genotype.length()];
        genotype.toSeq().toArray(chromosomes);

        for (int c = 0; c < chromosomes.length; c++) {

            StepfamilyChromosome chromosome = chromosomes[c].as(StepfamilyChromosome.class);
            StepfamilyGene[] genes = new StepfamilyGene[chromosome.timeslots() * chromosome.days()];
            chromosome.toSeq().toArray(genes);

            cost += cost(c, genes, chromosome.timeslots(), chromosome.days());
        }

        return cost * weight();
    }

    public abstract int cost(int chromosomeIndex, StepfamilyGene[] genes, int timeslots, int days);

    public abstract int weight();
}
