package be.atemi.decision.parentime.jenetics.constraint.hard;

import be.atemi.decision.parentime.jenetics.constraint.HardConstraint;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Agenda;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Genotype;

public class AvailabilityConstraint extends HardConstraint {

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
                for (int t = 0; t < chromosome.timeslots(); t++) {

                    Stepfamily stepfamily = chromosome.getGene(t + d * chromosome.timeslots()).getAllele();

                    boolean isAvailable = true;

                    for (Person person : stepfamily.getTutors()) {

                        if (person.getAgenda().getEntry(d, t).equals(Agenda.Entry.FIXED_UNAVAILABILITY)) {
                            isAvailable = false;
                        }
                    }

                    if (!isAvailable) {
                        conflicts++;
                    }
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
        return 32;
    }

    @Override
    public int weight() {
        return 1000;
    }
}
