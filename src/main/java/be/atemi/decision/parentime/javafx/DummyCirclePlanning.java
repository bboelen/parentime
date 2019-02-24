package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.DummyParentime;
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

        primaryStage.setTitle("Dummy Circle Planning");

        Circle dummyCircle = DummyParentime.dummyCircle();

        BestCirclePlanningGenotype result = CirclePlanningGenotype.compute(dummyCircle,
                DummyParentime.TIME_SLOTS, DummyParentime.DAYS, DummyParentime.constraints());

        CirclePlanning planning = new CirclePlanning(result.getGenotype(), dummyCircle.getStepfamilies());
        AgendaGrid agendas = new AgendaGrid(dummyCircle);

        VBox container = new VBox();
        container.getChildren().add(planning);
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
