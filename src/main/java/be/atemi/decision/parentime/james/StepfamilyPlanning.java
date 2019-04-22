package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.model.Stepfamily;
import be.atemi.decision.parentime.model.Timable;

import java.util.ArrayList;
import java.util.List;

public class StepfamilyPlanning extends ArrayList<Stepfamily> implements Timable {

    private int days;

    private int timeslots;

    public StepfamilyPlanning(List<Stepfamily> stepfamilies, int days, int timeslots) {
        super(stepfamilies);
        this.days = days;
        this.timeslots = timeslots;
    }

    @Override
    public int days() {
        return days;
    }

    @Override
    public int timeslots() {
        return timeslots;
    }
}
