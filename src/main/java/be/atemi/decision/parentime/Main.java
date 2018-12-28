package be.atemi.decision.parentime;

import be.atemi.decision.parentime.jenetics.FamilyChromosome;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

public class Main {

    private static int D = 2;
    private static int T = 2;
    private static int[] C = {1};

    private static int eval(final Genotype<BitGene> gt) {
        return gt.getChromosome()
                .as(BitChromosome.class)
                .bitCount();
    }

    private static int evalGenotype(final Genotype<IntegerGene> genotype) {
        int[] chromosome = genotype.getChromosome().as(IntegerChromosome.class).toArray();
        return 0;
    }


    public static void main(String... args) {



        final Factory<Genotype<IntegerGene>> genotypeFactory = Genotype.of(FamilyChromosome.of(2, 2));
        final Engine<IntegerGene, Integer> engine = Engine.builder(Main::evalGenotype, genotypeFactory).build();
        final Genotype<IntegerGene> result = engine.stream().limit(1000).collect(EvolutionResult.toBestGenotype());

//        final Factory<Genotype<BitGene>> gtf =
//                Genotype.of(BitChromosome.of(10, 0.5));
//
//        final Engine<BitGene, Integer> engine = Engine
//                .builder(Main::eval, gtf)
//                .build();
//
//        final Genotype<BitGene> result = engine.stream()
//                .limit(1000)
//                .collect(EvolutionResult.toBestGenotype());

        System.out.println(result);
    }
}
