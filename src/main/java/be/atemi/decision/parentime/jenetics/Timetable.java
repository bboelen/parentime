package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.constraint.Constraint;
import be.atemi.decision.parentime.helper.PrettyPrinter;
import be.atemi.decision.parentime.model.Child;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.Factory;

import java.util.Set;
import java.util.stream.Collectors;

public final class Timetable {

    private static Set<Constraint> constraintsSet = null;

    private static int fitness(final Genotype<FamilyGene> genotype) {
        int cost = constraintsSet.stream().mapToInt(constraint -> constraint.cost(genotype)).sum();
        //System.out.println(cost);
        return cost;
    }

    public static void build(Set<Child> children, int timeslots, int days, Set<Constraint> constraints) {

        constraintsSet = constraints;

        final Set<FamilyChromosome> chromosomes = children.stream()
                .map(child -> FamilyChromosome.of(child, timeslots, days)).collect(Collectors.toSet());

        final Factory<Genotype<FamilyGene>> factory = Genotype.of(chromosomes);

        final Engine<FamilyGene, Integer> engine = Engine
                .builder(Timetable::fitness, factory)
                .populationSize(1000)
                .offspringSelector(new TournamentSelector<>(10))
                .survivorsSelector(new EliteSelector<>(2))
                .alterers(
                        new SwapMutator<>(0.02),
                        new MultiPointCrossover<>(0.50, 6)
                )
                .minimizing()
                .build();

        final EvolutionStatistics<Integer, ?> statistics = EvolutionStatistics.ofNumber();

        final Genotype<FamilyGene> result = engine.stream()
                .limit(100)
                .peek(statistics)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println(statistics);
        PrettyPrinter.print(result, timeslots, days);
    }
}
