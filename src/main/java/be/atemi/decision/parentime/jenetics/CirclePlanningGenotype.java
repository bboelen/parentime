package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.constraint.Constraint;
import be.atemi.decision.parentime.model.Circle;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.Factory;

import java.util.Set;
import java.util.stream.Collectors;

public final class CirclePlanningGenotype {

    private static Set<Constraint> constraintsSet = null;

    private static int fitness(final Genotype<StepfamilyGene> genotype) {
        int cost = constraintsSet.stream().mapToInt(constraint -> constraint.cost(genotype)).sum();
        //System.out.println(cost);
        return cost;
    }

    public static BestCirclePlanningGenotype compute(Circle circle, int timeslots, int days, Set<Constraint> constraints) {

        constraintsSet = constraints;

        final Set<StepfamilyChromosome> chromosomes = circle.children().stream()
                .map(child -> StepfamilyChromosome.of(child, timeslots, days)).collect(Collectors.toSet());

        final Factory<Genotype<StepfamilyGene>> factory = Genotype.of(chromosomes);

        final Engine<StepfamilyGene, Integer> engine = Engine
                .builder(CirclePlanningGenotype::fitness, factory)
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

        final Genotype<StepfamilyGene> result = engine.stream()
                .limit(100)
                .peek(statistics)
                .collect(EvolutionResult.toBestGenotype());

        return new BestCirclePlanningGenotype(result, statistics);
    }
}