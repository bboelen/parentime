package be.atemi.decision.parentime.javafx.fxgraph.layout;

import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.layout.Layout;

import java.util.Iterator;
import java.util.List;

public class CircularLayout implements Layout {

    @Override
    public void execute(Graph graph) {
        List<ICell> cells = graph.getModel().getAllCells();
        Iterator var3 = cells.iterator();
        double xOrigin = 450;
        double yOrigin = 300;
        double numCells = cells.size();
        double radius = 280d;
        int cpt = 0;
        while (var3.hasNext()) {
            ICell cell = (ICell) var3.next();
            double angle = Math.toRadians(((double) cpt++ / numCells) * 360d);
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            graph.getGraphic(cell).relocate(x + xOrigin, y + yOrigin);
        }
    }
}
