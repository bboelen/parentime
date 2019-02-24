package be.atemi.decision.parentime.javafx;

import be.atemi.decision.parentime.helper.StringHelper;
import be.atemi.decision.parentime.model.Timable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class AbstractTimeGrid<T extends Timable> extends VBox {

    protected static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

    protected GridPane content(T content) {

        GridPane grid = new GridPane();

        if (content == null) {
            return grid;
        }

        initializeGrid(grid, content.days(), content.timeslots());
        fillGrid(grid, content);

        return grid;
    }

    protected abstract void fillGrid(GridPane grid, T content);

    protected static void initializeGrid(GridPane grid, int days, int timeslots) {
        grid.setMinSize(400, 200);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setAlignment(Pos.TOP_LEFT);

        ColumnConstraints timeslotsColmun = new ColumnConstraints(80);
        grid.getColumnConstraints().add(timeslotsColmun);

        for (int i = 1; i < days; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            grid.getColumnConstraints().add(column);
        }

        grid.add(new Text("T\\D"), 0, 0);
        addDays(grid, days);
        addTimeslots(grid, timeslots);
    }

    private static void addDays(GridPane grid, int days) {
        int idx = 0;
        for (int d = 1; d <= days; d++) {
            Label day = new Label(DAYS[idx]);
            if (idx == 0) day.setStyle("-fx-font-weight: bold;");
            grid.add(day, d, 0);
            idx = idx > 5 ? 0 : idx + 1;
        }
    }

    private static void addTimeslots(GridPane grid, int timeslots) {
        int duration = 24 / timeslots;
        int start = 0;
        for (int t = 1; t <= timeslots; t++) {
            grid.add(new Text(StringHelper.formatTimeslot(start, start + duration)), 0, t);
            start += duration;
        }
    }

}
