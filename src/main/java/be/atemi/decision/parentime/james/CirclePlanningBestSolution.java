package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.james.generator.RandomCirclePlanningSolutionGenerator;
import be.atemi.decision.parentime.james.neighbourhood.CirclePlanning2OptNeighbourhood;
import be.atemi.decision.parentime.james.neighbourhood.CirclePlanningRandomNeighbourhood;
import be.atemi.decision.parentime.james.valuetype.SearchAlgorithm;
import be.atemi.decision.parentime.model.Circle;
import org.jamesframework.core.problems.GenericProblem;
import org.jamesframework.core.problems.Problem;
import org.jamesframework.core.problems.constraints.Constraint;
import org.jamesframework.core.problems.constraints.PenalizingConstraint;
import org.jamesframework.core.search.LocalSearch;
import org.jamesframework.core.search.algo.RandomDescent;
import org.jamesframework.core.search.algo.vns.VariableNeighbourhoodSearch;
import org.jamesframework.core.search.neigh.Neighbourhood;
import org.jamesframework.core.search.stopcriteria.MaxTimeWithoutImprovement;
import org.jamesframework.ext.analysis.Analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class CirclePlanningBestSolution {

    public static BestCirclePlanningSolution compute(Circle circle, Set<Constraint> constraints, int timeWithoutImprovementLimit, SearchAlgorithm algorithm, int maxShake) {

        System.out.println("# PROBLEM SPECIFICATION");

        // create data object
        CirclePlanningData data = new CirclePlanningData(circle, circle.config().days(), circle.config().timeslots());
        // create objective
        CirclePlanningObjective objective = new CirclePlanningObjective();
        // wrap in generic problem
        Problem<CirclePlanningSolution> problem = new GenericProblem<>(data, objective, RandomCirclePlanningSolutionGenerator.getInstance());
        // initialize analysis object
        Analysis<CirclePlanningSolution> analysis = new Analysis<>();
        // adding problem to analysis
        analysis.addProblem("CFE", problem);

        System.out.println("Number of days: " + circle.config().days());
        System.out.println("Number of timeslots: " + circle.config().timeslots());
        System.out.println("Number of children: " + circle.children().size());

        // add constraints
        for (Constraint c : constraints) {
            if (c instanceof PenalizingConstraint) {
                ((GenericProblem) problem).addPenalizingConstraint((PenalizingConstraint) c);
            } else if (c instanceof Constraint) {
                ((GenericProblem) problem).addMandatoryConstraint(c);
            }
        }

        LocalSearch<CirclePlanningSolution> search = getSearchAlgorithm(problem, data, timeWithoutImprovementLimit, algorithm, maxShake);

        if (search == null) {
            return null;
        }

        // set maximum runtime
        search.addStopCriterion(new MaxTimeWithoutImprovement(timeWithoutImprovementLimit, TimeUnit.SECONDS));
        // attach listener
        search.addSearchListener(new ProgressSearchListener("GLOBAL"));
        // start with random circle planning solution
        search.setCurrentSolution(RandomCirclePlanningSolutionGenerator.getInstance().create(new Random(), data));
        // start search
        search.start();
        // get best solution
        BestCirclePlanningSolution bestSolution = new BestCirclePlanningSolution(search.getBestSolution(), problem);
        // dispose search
        search.dispose();

        return bestSolution;
    }

    private static LocalSearch getSearchAlgorithm(Problem<CirclePlanningSolution> problem, CirclePlanningData data, int timeWithoutImprovementLimit, SearchAlgorithm algorithm, int maxShake) {

        switch (algorithm) {
            case RANDOM_DECENT:
                return randomDescent(problem, data, timeWithoutImprovementLimit);
            case VARIABLE_NEIGHBOURHOOD_SEARCH:
                return vns(problem, data, timeWithoutImprovementLimit, maxShake);
            default:
                return null;
        }
    }

    private static final LocalSearch randomDescent(Problem<CirclePlanningSolution> problem, CirclePlanningData data, int timeWithoutImprovementLimit) {

        System.out.println("# RANDOM DESCENT");

        // create random descent with optimized neighbourhood
        LocalSearch<CirclePlanningSolution> randomDescent = new RandomDescent<>(problem, new CirclePlanning2OptNeighbourhood());

        return randomDescent;
    }

    private static final LocalSearch vns(Problem<CirclePlanningSolution> problem, CirclePlanningData data, int timeWithoutImprovementLimit, int maxShake) {

        System.out.println("# VARIABLE NEIGHBOURHOOD SEARCH");

        // create shaking neighbourhoods
        List<Neighbourhood<CirclePlanningSolution>> shakingNeighs = new ArrayList<>();
        for (int s = 1; s <= maxShake; s++) {
            shakingNeighs.add(new CirclePlanningRandomNeighbourhood(data));
        }

        // create variable neighbourhood search
        LocalSearch<CirclePlanningSolution> vns = new VariableNeighbourhoodSearch(
                problem,
                shakingNeighs,
                // use random descent with 2-opt neighbourhood
                pbm -> {
                    LocalSearch search = new RandomDescent<>(pbm, new CirclePlanning2OptNeighbourhood());
                    search.addSearchListener(new ProgressSearchListener("LOCAL"));
                    return search;
                }
        );

        return vns;
    }
}
