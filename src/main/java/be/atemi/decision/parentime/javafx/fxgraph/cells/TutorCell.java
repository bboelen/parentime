package be.atemi.decision.parentime.javafx.fxgraph.cells;

import com.fxgraph.graph.Graph;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TutorCell extends AbstractLabeledCell {

    private String color;

    public TutorCell(String text, String color) {
        super(text);
        this.color = color;
    }

    @Override
    public Region getGraphic(Graph graph) {
        Rectangle rectangle = new Rectangle(100.0D, 100.0D);
        Label label = getLabel();
        label.setStyle(color);
        label.setPrefWidth(100);
        label.setPrefHeight(100);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.LIGHTGRAY);
        StackPane pane = new StackPane(new Node[]{rectangle, label});
        pane.setPrefSize(100.0D, 100.0D);
        rectangle.widthProperty().bind(pane.prefWidthProperty().divide(2));
        rectangle.heightProperty().bind(pane.prefHeightProperty().divide(2));
        return pane;
    }
}
