package be.atemi.decision.parentime.james;

import org.jamesframework.core.problems.Problem;

public class BestCirclePlanningSolution {

    private CirclePlanningSolution bestSolution;

    private Problem problem;

    public BestCirclePlanningSolution(CirclePlanningSolution bestSolution, Problem problem) {
        this.bestSolution = bestSolution;
        this.problem = problem;
    }

    public CirclePlanningSolution getBestSolution() {
        return bestSolution;
    }

    public Problem getProblem() {
        return problem;
    }
}
