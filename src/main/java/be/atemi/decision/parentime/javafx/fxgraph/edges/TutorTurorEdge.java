package be.atemi.decision.parentime.javafx.fxgraph.edges;

import com.fxgraph.edges.AbstractEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TutorTurorEdge extends AbstractEdge {

    private final transient StringProperty textProperty = new SimpleStringProperty();

    public TutorTurorEdge(ICell source, ICell target) {
        super(source, target);
    }

    public TutorTurorEdge.EdgeGraphic getGraphic(Graph graph) {
        return new TutorTurorEdge.EdgeGraphic(graph, this, this.textProperty);
    }

    public StringProperty textProperty() {
        return this.textProperty;
    }

    public static class EdgeGraphic extends Pane {
        private final Group group = new Group();
        private final Line line = new Line();
        private final Text text;

        public EdgeGraphic(Graph graph, TutorTurorEdge edge, StringProperty textProperty) {
            DoubleBinding sourceX = edge.getSource().getXAnchor(graph, edge);
            DoubleBinding sourceY = edge.getSource().getYAnchor(graph, edge);
            DoubleBinding targetX = edge.getTarget().getXAnchor(graph, edge);
            DoubleBinding targetY = edge.getTarget().getYAnchor(graph, edge);
            this.line.getStrokeDashArray().addAll(2d, 21d);
            this.line.startXProperty().bind(sourceX);
            this.line.startYProperty().bind(sourceY);
            this.line.endXProperty().bind(targetX);
            this.line.endYProperty().bind(targetY);
            this.group.getChildren().add(this.line);
            DoubleProperty textWidth = new SimpleDoubleProperty();
            DoubleProperty textHeight = new SimpleDoubleProperty();
            this.text = new Text();
            this.text.textProperty().bind(textProperty);
            this.text.getStyleClass().add("edge-text");
            this.text.xProperty().bind(this.line.startXProperty().add(this.line.endXProperty()).divide(2).subtract(textWidth.divide(2)));
            this.text.yProperty().bind(this.line.startYProperty().add(this.line.endYProperty()).divide(2).subtract(textHeight.divide(2)));
            Runnable recalculateWidth = () -> {
                textWidth.set(this.text.getLayoutBounds().getWidth());
                textHeight.set(this.text.getLayoutBounds().getHeight());
            };
            this.text.parentProperty().addListener((obs, oldVal, newVal) -> {
                recalculateWidth.run();
            });
            this.text.textProperty().addListener((obs, oldVal, newVal) -> {
                recalculateWidth.run();
            });
            this.group.getChildren().add(this.text);
            this.getChildren().add(this.group);
        }

        public Group getGroup() {
            return this.group;
        }

        public Line getLine() {
            return this.line;
        }

        public Text getText() {
            return this.text;
        }
    }

}
