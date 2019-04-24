package be.atemi.decision.parentime.javafx.fxgraph;

import be.atemi.decision.parentime.javafx.fxgraph.cells.ChildCell;
import be.atemi.decision.parentime.javafx.fxgraph.cells.TutorCell;
import be.atemi.decision.parentime.model.Circle;
import be.atemi.decision.parentime.model.Person;
import com.fxgraph.edges.CorneredEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.RandomLayout;
import javafx.geometry.Orientation;

import java.util.HashMap;
import java.util.Map;

public class CircleGraph extends Graph {

    public CircleGraph(Circle circle) {

        final Model model = getModel();

        beginUpdate();

        Map<Person, ICell> tutorNodes = new HashMap<>();

        circle.children().stream().forEach(child -> {

            ICell c = new ChildCell(child.getLabel());
            model.addCell(c);

            child.getParents().stream().forEach(parent -> {
                if (!tutorNodes.containsKey(parent)) {
                    tutorNodes.put(parent, new TutorCell(parent.getLabel()));
                }

                System.out.println(child.getLabel() + " -- " + parent.getLabel());
                CorneredEdge edge = new CorneredEdge(c, tutorNodes.get(parent), Orientation.HORIZONTAL);
                model.addEdge(edge);
            });
        });

//        final ICell cellA = new TutorCell("B");
//        final ICell cellB = new TutorCell("B");
//        final ICell cellC = new TutorCell("B");
//        final ICell cellD = new ChildCell("A");
//        final ICell cellE = new TutorCell("B");
//        final ICell cellF = new TutorCell("B");
//        final ICell cellG = new TutorCell("B");

//        model.addCell(cellA);
//        model.addCell(cellB);
//        model.addCell(cellC);
//        model.addCell(cellD);
//        model.addCell(cellE);
//        model.addCell(cellF);
//        model.addCell(cellG);

//        final Edge edgeAB = new Edge(cellA, cellB);
//
//        edgeAB.textProperty().set("Edges can have text too!");
//        model.addEdge(edgeAB);
//        final CorneredEdge edgeAC = new CorneredEdge(cellA, cellC, Orientation.HORIZONTAL);
//        edgeAC.textProperty().set("Edges can have corners too!");
//        model.addEdge(edgeAC);
//        model.addEdge(cellB, cellD);
//        final DoubleCorneredEdge edgeBE = new DoubleCorneredEdge(cellB, cellE, Orientation.HORIZONTAL);
//        edgeBE.textProperty().set("You can implement custom edges and nodes too!");
//        model.addEdge(edgeBE);
//        model.addEdge(cellC, cellF);
//        model.addEdge(cellC, cellG);

        endUpdate();

        layout(new RandomLayout());


    }
}
