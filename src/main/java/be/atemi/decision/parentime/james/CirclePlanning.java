package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.james.generator.RandomCirclePlanningSolutionGenerator;
import be.atemi.decision.parentime.james.neighbourhood.CirclePlanning2OptNeighbourhood;
import be.atemi.decision.parentime.model.Circle;
import org.jamesframework.core.problems.GenericProblem;
import org.jamesframework.core.problems.Problem;
import org.jamesframework.core.problems.constraints.Constraint;
import org.jamesframework.core.problems.constraints.PenalizingConstraint;
import org.jamesframework.core.search.LocalSearch;
import org.jamesframework.core.search.algo.RandomDescent;
import org.jamesframework.core.search.stopcriteria.MaxTimeWithoutImprovement;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class CirclePlanning {

    public static BestCirclePlanningSolution compute(Circle circle, int days, int timeslots, Set<Constraint> constraints, int maxShake, int timeWithoutImprovementLimit) {

        System.out.println("# PROBLEM SPECIFICATION");

        // create data object
        CirclePlanningData data = new CirclePlanningData(circle, days, timeslots);
        // create objective
        CirclePlanningObjective objective = new CirclePlanningObjective();
        // wrap in generic problem
        Problem<CirclePlanningSolution> problem = new GenericProblem<>(data, objective, RandomCirclePlanningSolutionGenerator.getInstance());

        System.out.println("Number of days: " + days);
        System.out.println("Number of timeslots: " + timeslots);
        System.out.println("Number of children: " + circle.children().size());

        // create subset problem (all sizes allowed)
        //SubsetProblem<CirclePlanningData> circlePlanningProblem = new SubsetProblem(data, objective);
        // add mandatory geneticAlgorithmConstraints
        for (Constraint c : constraints) {
            if (c instanceof PenalizingConstraint) {
                ((GenericProblem) problem).addPenalizingConstraint((PenalizingConstraint) c);
            } else if (c instanceof Constraint) {
                ((GenericProblem) problem).addMandatoryConstraint(c);
            }
        }

        System.out.println("# RANDOM DESCENT");

        // create random descent with optimized neighbourhood
        LocalSearch<CirclePlanningSolution> randomDescent = new RandomDescent<>(problem, new CirclePlanning2OptNeighbourhood());

        // set maximum runtime
        randomDescent.addStopCriterion(new MaxTimeWithoutImprovement(timeWithoutImprovementLimit, TimeUnit.SECONDS));
        // attach listener
        randomDescent.addSearchListener(new ProgressSearchListener());
        // start with random circle planning solution
        randomDescent.setCurrentSolution(RandomCirclePlanningSolutionGenerator.getInstance().create(new Random(), data));
        // start search
        randomDescent.start();
        // get best solution
        BestCirclePlanningSolution bestSolution = new BestCirclePlanningSolution(randomDescent.getBestSolution(), problem);
        // dispose search
        randomDescent.dispose();

        return bestSolution;

        // System.out.println("# VARIABLE NEIGHBOURHOOD SEARCH");


        // create variable neighbourhood search
    }
}
