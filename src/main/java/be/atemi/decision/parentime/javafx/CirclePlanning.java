package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Genotype;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CirclePlanning extends AbstractTimeGrid<StepfamilyChromosome> {

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
            tab.setContent(content(c));
            tabs.getTabs().add(tab);
        });

        getChildren().add(tabs);
    }

    @Override
    protected void fillGrid(GridPane grid, StepfamilyChromosome chromosome) {
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

    private static void assignColors(Set<Stepfamily> stepfamilies) {
        try {
            stepfamilies.forEach(stepfamily -> stepfamilyColors.put(stepfamily.getId(), COLORS[colorIndex++]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("There are not enough colors to assign! You must add more.");
        }
    }
}
