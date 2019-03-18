package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.constraint.Constraint;
import be.atemi.decision.parentime.model.Circle;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.Limits;
import io.jenetics.util.Factory;

import java.util.Set;
import java.util.stream.Collectors;

public final class CirclePlanningGenotype {

    private static Set<Constraint> constraintsSet = null;

    private static double MAX_COST = 0;

    private static double fitness(final Genotype<StepfamilyGene> genotype) {
        double cost = constraintsSet.stream().mapToDouble(constraint -> constraint.weightedCost(genotype)).sum();
        //System.out.println(cost);

        //if(cost > MAX_COST) MAX_COST = cost;

        return cost;
    }

    public static BestCirclePlanningGenotype compute(Circle circle, int timeslots, int days, Set<Constraint> constraints) {

        constraintsSet = constraints;

        final Set<StepfamilyChromosome> chromosomes = circle.children().stream()
                .map(child -> StepfamilyChromosome.of(child, timeslots, days)).collect(Collectors.toSet());

        final Factory<Genotype<StepfamilyGene>> factory = Genotype.of(chromosomes);

        final Engine<StepfamilyGene, Double> engine = Engine
                .builder(CirclePlanningGenotype::fitness, factory)
                .populationSize(10)
                .offspringSelector(new StochasticUniversalSelector<>())
                .survivorsSelector(new StochasticUniversalSelector<>())
                .alterers(
                        new SwapMutator<>(0.1),
                        new MultiPointCrossover<>(0.50, 3)
                )
                .minimizing()
                .build();

        final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

        final Genotype<StepfamilyGene> result = engine.stream()
                .limit(Limits.bySteadyFitness(100))
                .peek(statistics)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("-------------- MAX COST = " + MAX_COST);

        return new BestCirclePlanningGenotype(result, statistics);
    }
}
