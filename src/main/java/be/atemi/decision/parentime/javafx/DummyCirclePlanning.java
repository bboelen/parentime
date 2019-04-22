package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.DummyParentime;
import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningBestSolution;
import be.atemi.decision.parentime.james.valuetype.SearchAlgorithm;
import be.atemi.decision.parentime.javafx.planning.ChromosomeCirclePlanning;
import be.atemi.decision.parentime.javafx.planning.SolutionCirclePlanning;
import be.atemi.decision.parentime.jenetics.BestCirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.CirclePlanningGenotype;
import be.atemi.decision.parentime.model.Circle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DummyCirclePlanning extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Dummy Circle Planning [VNS]");

        Circle dummyCircle = DummyParentime.dummyCircle();

//        BestCirclePlanningGenotype resultAG = CirclePlanningGenotype.compute(dummyCircle,
//                DummyParentime.TIME_SLOTS, DummyParentime.DAYS, DummyParentime.geneticAlgorithmConstraints());
//
//        CirclePlanning planningGA = new ChromosomeCirclePlanning(resultAG.getGenotype(), dummyCircle.getStepfamilies());

        BestCirclePlanningSolution resultVNS = CirclePlanningBestSolution.compute(dummyCircle,
                DummyParentime.DAYS, DummyParentime.TIME_SLOTS, DummyParentime.variableNeighbourhoodSearchConstraints(),
                1, SearchAlgorithm.VARIABLE_NEIGHBOURHOOD_SEARCH, 10);

        CirclePlanning planningVNS = new SolutionCirclePlanning(resultVNS.getBestSolution(), dummyCircle.getStepfamilies());

        AgendaGrid agendas = new AgendaGrid(dummyCircle);

        VBox container = new VBox();
        container.getChildren().add(planningVNS);
        container.getChildren().add(agendas);

        primaryStage.setScene(new Scene(container));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
