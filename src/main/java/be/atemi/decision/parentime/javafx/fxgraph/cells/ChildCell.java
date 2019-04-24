package be.atemi.decision.parentime.javafx.fxgraph.cells;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.graph.Graph;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class ChildCell extends AbstractCell {

    private String text;

    public ChildCell(String text) {
        this.text = text;
    }

    @Override
    public Region getGraphic(Graph graph) {
        Circle circle = new Circle(50.0D);
        Label label = new Label(text);
        label.setFont(new Font(20));
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        StackPane pane = new StackPane(new Node[]{circle, label});
        pane.setPrefSize(50.0D, 50.0D);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        return pane;
    }
}
