package be.atemi.decision.parentime.helper;

import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Person;
import io.jenetics.Genotype;

public final class PrettyPrinter {

    public static void print(Genotype<StepfamilyGene> genotype, int timeslots, int days) {
        genotype.toSeq().forEach(chromosome -> {
            StepfamilyGene[] sequence = new StepfamilyGene[timeslots * days];
            chromosome.toSeq().toArray(sequence);
            cr();
            Person child = chromosome.as(StepfamilyChromosome.class).child();
            System.out.println(String.format(" * custody schedule for %s %s", child.getFirstName(), child.getLastName()));
            cr();
            print("");
            for (int i = 0; i < days; i++) {
                print(String.format("[    day %s    ]", i + 1));
            }
            cr();
            cr();
            for (int i = 0; i < timeslots; i++) {
                print(String.format("timeslot %s    |", String.format("%02d", i)));
                for (int j = 0; j < days; j++) {
                    print(sequence[i + j * timeslots].getAllele().getName());
                }
                cr();
            }
        });
    }

    private static void print(Object o) {
        System.out.print(String.format("%20s", o));
    }

    private static void cr() { System.out.println(); }
}
