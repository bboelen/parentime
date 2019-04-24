package be.atemi.decision.parentime.javafx.fxgraph;

import be.atemi.decision.parentime.helper.ColorGenerator;
import be.atemi.decision.parentime.javafx.fxgraph.cells.ChildCell;
import be.atemi.decision.parentime.javafx.fxgraph.cells.TutorCell;
import be.atemi.decision.parentime.javafx.fxgraph.edges.TutorChildEdge;
import be.atemi.decision.parentime.javafx.fxgraph.edges.TutorTurorEdge;
import be.atemi.decision.parentime.model.Circle;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;

import java.util.HashMap;
import java.util.Map;

public class CircleGraph extends Graph {

    private static Map<Stepfamily, String> stepfamilyColors = new HashMap<>();

    public CircleGraph(Circle circle) {

        circle.getStepfamilies().forEach(stepfamily -> {
            if (!stepfamilyColors.containsKey(stepfamily)) {
                stepfamilyColors.put(stepfamily, ColorGenerator.get());
            }
        });
        ColorGenerator.reset();

        final Model model = getModel();

        beginUpdate();

        Map<Person, ICell> tutorNodes = new HashMap<>();

        circle.children().stream().forEach(child -> {

            ICell c = new ChildCell(child.getFirstName());
            model.addCell(c);

            child.getParents().stream().forEach(parent -> {

                if (!tutorNodes.containsKey(parent)) {
                    tutorNodes.put(parent, new TutorCell(parent.getFirstName() + " " + parent.getLastName(), stepfamilyColors.get(parent.getStepfamily())));
                    model.addCell(tutorNodes.get(parent));
                }

                TutorChildEdge edge = new TutorChildEdge(c, tutorNodes.get(parent));
                model.addEdge(edge);
            });
        });

        circle.getStepfamilies().stream().forEach(stepfamily -> {

            ICell first = null;
            ICell current = null;

            for (Person tutor : stepfamily.getTutors()) {

                if (first == null) {
                    first = tutorNodes.get(tutor);
                    current = tutorNodes.get(tutor);
                } else if (first != null) {
                    TutorTurorEdge edge = new TutorTurorEdge(current, tutorNodes.get(tutor));
                    current = tutorNodes.get(tutor);
                    model.addEdge(edge);
                }
            }

            TutorTurorEdge edge = new TutorTurorEdge(current, first);
            model.addEdge(edge);
        });

        endUpdate();

        layout(new RandomLayout());
    }
}
