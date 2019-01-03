package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.helper.StringHelper;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Genotype;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CirclePlanning extends VBox {

    private static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

    private static final String[] COLORS = {
            "-fx-background-color: gold;",
            "-fx-background-color: dodgerblue;",
            "-fx-background-color: indianred;",
            "-fx-background-color: cadetblue;",
            "-fx-background-color: lightsalmon;",
            "-fx-background-color: aliceblue;",
            "-fx-background-color: coral;"};

    private static int colorIndex = 0;

    private static Map<Integer, String> stepfamilyColors = new HashMap<>();

    public CirclePlanning(Genotype<StepfamilyGene> genotype, Set<Stepfamily> stepfamilies) {

        assignColors(stepfamilies);

        TabPane tabs = new TabPane();

        genotype.forEach(chromosome -> {
            StepfamilyChromosome c = chromosome.as(StepfamilyChromosome.class);
            Tab tab = new Tab();
            tab.setText(c.child().getFirstName() + " " + c.child().getLastName());
            tab.setContent(custodyPlanning(c));
            tabs.getTabs().add(tab);
        });

        getChildren().add(tabs);
    }

    private GridPane custodyPlanning(StepfamilyChromosome chromosome) {

        GridPane grid = new GridPane();

        if (chromosome == null) {
            return grid;
        }

        initializeGrid(grid, chromosome);
        fillGrid(grid, chromosome);

        return grid;
    }

    private static void fillGrid(GridPane grid, StepfamilyChromosome chromosome) {
        for (int i = 0; i < chromosome.days(); i++) {
            for (int j = 0; j < chromosome.timeslots(); j++) {
                Stepfamily stepfamily = chromosome.getGene(j + i * chromosome.timeslots()).getAllele();
                Label label = new Label(stepfamily.getName());
                label.setStyle(stepfamilyColors.get(stepfamily.getId()));
                label.setPrefWidth(50);
                grid.add(label, i + 1, j + 1);

            }
        }
    }

    private static void initializeGrid(GridPane grid, StepfamilyChromosome chromosome) {
        grid.setMinSize(400, 200);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setAlignment(Pos.TOP_LEFT);

        ColumnConstraints timeslotsColmun = new ColumnConstraints(80);
        grid.getColumnConstraints().add(timeslotsColmun);

        for (int i = 1; i < chromosome.days(); i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            grid.getColumnConstraints().add(column);
        }

        grid.add(new Text("T\\D"), 0, 0);
        addDays(grid, chromosome.days());
        addTimeslots(grid, chromosome.timeslots());
    }

    private static void addDays(GridPane grid, int days) {
        int idx = 0;
        for (int d = 1; d <= days; d++) {
            Label day = new Label(DAYS[idx]);
            if(idx == 0) day.setStyle("-fx-font-weight: bold;");
            grid.add(day, d, 0);
            idx = idx > 5 ? 0 : idx + 1;
        }
    }

    private static void addTimeslots(GridPane grid, int timeslots) {
        int duration = 24 / timeslots;
        int start = 0;
        for (int t = 1; t <= timeslots; t++) {
            grid.add(new Text(StringHelper.formatTimeslot(start, start + duration)), 0, t);
            start += duration;
        }
    }

    private static void assignColors(Set<Stepfamily> stepfamilies) {
        try {
            stepfamilies.forEach(stepfamily -> stepfamilyColors.put(stepfamily.getId(), COLORS[colorIndex++]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("There are not enough colors to assign! You must add more.");
        }
    }
}
