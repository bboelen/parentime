package be.atemi.decision.parentime.jenetics.constraint.hard;

import be.atemi.decision.parentime.jenetics.constraint.HardConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import io.jenetics.Genotype;

public class FullNightMorningConstraint extends HardConstraint {

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

            for (int d = 0; d < chromosome.days(); d++) {

                int mIdx = d * chromosome.timeslots();
                int nIdx = d != 0 ? d * chromosome.timeslots() - 1 : chromosome.days() * chromosome.timeslots() - 1;

                int a = chromosome.getGene(mIdx).getAllele().getId();
                int b = chromosome.getGene(nIdx).getAllele().getId();

                if (a != b) {
                    conflicts++;
                }
            }
        }

        if(conflicts > MAX) {
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
        return 2000;
    }
}
