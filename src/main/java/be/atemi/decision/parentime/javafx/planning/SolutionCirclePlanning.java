package be.atemi.decision.parentime.javafx.planning;

import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.StepfamilyPlanning;
import be.atemi.decision.parentime.javafx.CirclePlanning;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class SolutionCirclePlanning extends CirclePlanning<StepfamilyPlanning> {

    public SolutionCirclePlanning(CirclePlanningSolution solution, Set<Stepfamily> stepfamilies) {

        assignColors(stepfamilies);

        TabPane tabs = new TabPane();

        for (Person child : solution.getDeltaStructure().keySet()) {
            Tab tab = new Tab();
            tab.setText(child.getFirstName() + " " + child.getLastName());
            tab.setContent(content(solution.getDeltaStructure().get(child)));
            tabs.getTabs().add(tab);
        }

        getChildren().add(tabs);
    }

    @Override
    protected void fillGrid(GridPane grid, StepfamilyPlanning content) {
        for (int i = 0; i < content.days(); i++) {
            for (int j = 0; j < content.timeslots(); j++) {
                Stepfamily stepfamily = content.get(j + i * content.timeslots());
                Label label = new Label(stepfamily.getName());
                label.setStyle(stepfamilyColors.get(stepfamily.getId()));
                label.setPrefWidth(50);
                grid.add(label, i + 1, j + 1);
            }
        }
    }
}
