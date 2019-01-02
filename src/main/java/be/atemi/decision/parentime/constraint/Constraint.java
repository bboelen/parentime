package be.atemi.decision.parentime.constraint;

import be.atemi.decision.parentime.jenetics.FamilyChromosome;
import be.atemi.decision.parentime.jenetics.FamilyGene;
import io.jenetics.Genotype;

public abstract class Constraint {

    public int cost(final Genotype<FamilyGene> genotype) {

        int cost = 0;

        FamilyChromosome[] chromosomes = new FamilyChromosome[genotype.length()];
        genotype.toSeq().toArray(chromosomes);

        for (int c = 0; c < chromosomes.length; c++) {

            FamilyChromosome chromosome = chromosomes[c].as(FamilyChromosome.class);
            FamilyGene[] genes = new FamilyGene[chromosome.timeslots() * chromosome.days()];
            chromosome.toSeq().toArray(genes);

            cost += cost(c, genes, chromosome.timeslots(), chromosome.days());
        }

        return cost * weight();
    }

    public abstract int cost(int chromosomeIndex, FamilyGene[] genes, int timeslots, int days);

    public abstract int weight();
}
