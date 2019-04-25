package be.atemi.decision.parentime.javafx.fxgraph.cells;

import com.fxgraph.cells.AbstractCell;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public abstract class AbstractLabeledCell extends AbstractCell {

    protected String text;

    public AbstractLabeledCell(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    protected Label getLabel() {
        Label label = new Label(text);
        label.setFont(new Font(20));
        return label;
    }
}
