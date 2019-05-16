package be.atemi.decision.parentime.james.analysis;

import be.atemi.decision.parentime.helper.CircleFileReader;
import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningObjective;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.ProgressSearchListener;
import be.atemi.decision.parentime.james.constraint.hard.AvailabilityConstraint;
import be.atemi.decision.parentime.james.constraint.hard.FullNightMorningConstraint;
import be.atemi.decision.parentime.james.generator.RandomCirclePlanningSolutionGenerator;
import be.atemi.decision.parentime.james.neighbourhood.CirclePlanning2OptNeighbourhood;
import be.atemi.decision.parentime.model.Circle;
import org.jamesframework.core.problems.GenericProblem;
import org.jamesframework.core.problems.Problem;
import org.jamesframework.core.search.Search;
import org.jamesframework.core.search.algo.RandomDescent;
import org.jamesframework.core.search.stopcriteria.MaxRuntime;
import org.jamesframework.core.search.stopcriteria.StopCriterion;
import org.jamesframework.ext.analysis.Analysis;
import org.jamesframework.ext.analysis.AnalysisResults;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public final class EFCAnalyzer {

    private static final int timeLimit = 10;
    private static final int numRuns = 1;

    private static Problem<CirclePlanningSolution> problem(Circle circle, boolean isFullNight, boolean isAvailability) {

        // create data object
        CirclePlanningData data = new CirclePlanningData(circle, circle.config().days(), circle.config().timeslots());
        // create objective
        CirclePlanningObjective objective = new CirclePlanningObjective();
        // wrap in generic problem
        Problem<CirclePlanningSolution> problem = new GenericProblem<>(data, objective, RandomCirclePlanningSolutionGenerator.getInstance());
        // adding constraints
        if (isFullNight) {
            ((GenericProblem) problem).addPenalizingConstraint(new FullNightMorningConstraint());
        }
        if (isAvailability) {
            ((GenericProblem) problem).addPenalizingConstraint(new AvailabilityConstraint());
        }
        return problem;
    }

    public static void main(String... args) {

        System.out.println("#############################################");
        System.out.println("# ANALYSIS: COMPARE CONSTRAINTS PERFORMANCE #");
        System.out.println("#############################################");

        Circle circle = CircleFileReader.getInstance().read("efc.xml");

        // initialize analysis object
        Analysis<CirclePlanningSolution> analysis = new Analysis<>();
        // adding problem to analysis
//        analysis.addProblem("EFC - fullNight", problem(circle, true, false));
//        analysis.addProblem("EFC - availability", problem(circle, false, true));
        analysis.addProblem("EFC - all", problem(circle, true, true));

        // ADD SEARCHES
        System.out.println("# ADDING SEARCHES TO ANALYSIS");

        // create stop criterion
        // StopCriterion stopCriterion = new MaxTimeWithoutImprovement(1, TimeUnit.SECONDS);
        StopCriterion stopCriterion = new MaxRuntime(timeLimit, TimeUnit.SECONDS);

        // add vns
        System.out.println("Add vns");
        analysis.addSearch("VNS", pbm -> {
            Search search = new RandomDescent(pbm, new CirclePlanning2OptNeighbourhood());
            search.addSearchListener(new ProgressSearchListener("LOCAL"));
            search.addStopCriterion(stopCriterion);
            return search;
        });

        // set number  of search runs
        analysis.setNumRuns(numRuns);

        ////////////////////////////////////////
        // start loader
        Timer loaderTimer = new Timer();
        TimerTask loaderTask = new TimerTask() {
            private char[] loader = new char[40];
            private int l = 6;
            private int p = 0;

            @Override
            public void run() {
                printLoader(loader);
                p = (p + 1) % loader.length;
                updateLoader(loader, p, l);
            }
        };
        loaderTimer.schedule(loaderTask, 0, 100);

        // run analysis
        System.out.format("# RUNNING ANALYSIS (runs per search: %d)\n", numRuns);

        AnalysisResults<CirclePlanningSolution> results = analysis.run();

        // stop loader
        loaderTask.cancel();
        loaderTimer.cancel();
        System.out.println("# Done!");

        // write to JSON
        System.out.println("# WRITING JSON FILE");
        String jsonFile = "efc.json";
        try {
            results.writeJSON(jsonFile, solution -> CirclePlanningSolution2JsonConverter.getInstance().toJson(solution));
        } catch (IOException ex) {
            System.err.println("Failed to write JSON file: " + jsonFile);
            System.exit(3);
        }
        System.out.println("# Wrote \"" + jsonFile + "\"");

    }

    private static void updateLoader(char[] loader, int start, int loaderLength) {
        Arrays.fill(loader, ' ');
        for (int t = 0; t < loaderLength; t++) {
            int pos = (start + t) % (loader.length);
            loader[pos] = '-';
        }
    }

    private static void printLoader(char[] loader) {
        for (char c : loader) {
            System.out.print(c);
        }
        System.out.print("\r");
    }
}
