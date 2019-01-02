package be.atemi.decision.parentime.helper;

import be.atemi.decision.parentime.jenetics.FamilyChromosome;
import be.atemi.decision.parentime.jenetics.FamilyGene;
import io.jenetics.Genotype;

public final class PrettyPrinter {

    public static void print(Genotype<FamilyGene> genotype, int timeslots, int days) {
        genotype.toSeq().forEach(chromosome -> {
            FamilyGene[] sequence = new FamilyGene[timeslots * days];
            chromosome.toSeq().toArray(sequence);
            cr();
            System.out.println(String.format(" * custody schedule for %s", chromosome.as(FamilyChromosome.class).child().getName()));
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
