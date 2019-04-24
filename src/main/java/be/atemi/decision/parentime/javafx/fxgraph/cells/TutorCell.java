package be.atemi.decision.parentime.javafx.fxgraph.cells;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.graph.Graph;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TutorCell extends AbstractCell {

    private String text;

    public TutorCell(String text) {
        this.text = text;
    }

    @Override
    public Region getGraphic(Graph graph) {
        Rectangle rectangle = new Rectangle(50.0D, 50.0D);
        Label label = new Label(text);
        label.setFont(new Font(20));
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.LIGHTGRAY);
        StackPane pane = new StackPane(new Node[]{rectangle, label});
        pane.setPrefSize(50.0D, 50.0D);
        rectangle.widthProperty().bind(pane.prefWidthProperty());
        rectangle.heightProperty().bind(pane.prefHeightProperty());
        return pane;
    }
}
