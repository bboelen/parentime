package be.atemi.decision.parentime.javafx.planning;

import be.atemi.decision.parentime.javafx.CirclePlanning;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Genotype;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.Comparator;
import java.util.Set;

public class ChromosomeCirclePlanning extends CirclePlanning<StepfamilyChromosome> {

    public ChromosomeCirclePlanning(Genotype<StepfamilyGene> genotype, Set<Stepfamily> stepfamilies) {

        assignColors(stepfamilies);

        TabPane tabs = new TabPane();

        genotype.toSeq().stream().sorted(Comparator.comparing(c -> c.as(StepfamilyChromosome.class).child().getFirstName() + " " + c.as(StepfamilyChromosome.class).child().getLastName())).forEach(chromosome -> {
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
}
