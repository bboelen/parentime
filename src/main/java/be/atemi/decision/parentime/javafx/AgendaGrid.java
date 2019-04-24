package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.model.Agenda;
import be.atemi.decision.parentime.model.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.Comparator;

public class AgendaGrid extends AbstractTimeGrid<Agenda> {

    private static final String[] COLORS = {
            "-fx-background-color: lightgreen;",
            "-fx-background-color: mediumslateblue;",
            "-fx-background-color: indianred;"};

    public AgendaGrid(Circle circle) {

        TabPane tabs = new TabPane();

        circle.tutors().stream().sorted(Comparator.comparing(t -> t.getFirstName() + " " + t.getLastName())).forEach(p -> {
            Tab tab = new Tab();
            tab.setText(p.getFirstName() + " " + p.getLastName());
            tab.setContent(content(p.getAgenda()));
            tabs.getTabs().add(tab);
        });

        getChildren().add(tabs);
    }

    @Override
    protected void fillGrid(GridPane grid, Agenda agenda) {
        for (int i = 0; i < agenda.days(); i++) {
            for (int j = 0; j < agenda.timeslots(); j++) {
                Agenda.Entry entry = agenda.getEntry(i, j);
                Label label = new Label(entry.value() + "");
                label.setStyle(COLORS[entry.value()]);
                label.setPrefWidth(50);
                grid.add(label, i + 1, j + 1);

            }
        }
    }
}
