package be.atemi.decision.parentime.javafx.fxgraph.cells;

import com.fxgraph.graph.Graph;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ChildCell extends AbstractLabeledCell {


    public ChildCell(String text) {
        super(text);
    }

    @Override
    public Region getGraphic(Graph graph) {
        Circle circle = new Circle(50.0D);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        StackPane pane = new StackPane(new Node[]{circle, getLabel()});
        pane.setPrefSize(50.0D, 50.0D);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        return pane;
    }
}
