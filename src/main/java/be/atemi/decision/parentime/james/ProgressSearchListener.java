package be.atemi.decision.parentime.james;

import org.jamesframework.core.problems.constraints.validations.Validation;
import org.jamesframework.core.problems.objectives.evaluations.Evaluation;
import org.jamesframework.core.problems.sol.Solution;
import org.jamesframework.core.search.Search;
import org.jamesframework.core.search.listeners.SearchListener;

public class ProgressSearchListener implements SearchListener<Solution> {

    public void searchStarted(Search search) {
        System.out.println(" >>> Search started");
    }

    public void searchStopped(Search search) {
        System.out.println(" >>> Search stopped (" + search.getRuntime() / 1000 + " sec, " + search.getSteps() + "  steps)");
    }

    public void newBestSolution(Search search,
                                Solution newBestSolution,
                                Evaluation newBestSolutionEvaluation,
                                Validation newBestSolutionValidation) {
        System.out.println(" >>> New best solution: " + newBestSolutionEvaluation);
    }
}
