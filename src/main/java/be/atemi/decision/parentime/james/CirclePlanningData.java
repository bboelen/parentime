package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.model.Circle;

public class CirclePlanningData {

    private Circle circle;

    private int days;

    private int timeslots;

    public CirclePlanningData(Circle circle, int days, int timeslots) {
        this.circle = circle;
        this.days = days;
        this.timeslots = timeslots;
    }


    public Circle getCircle() {
        return circle;
    }

    public int days() {
        return this.days;
    }

    public int timeslots() {
        return this.timeslots;
    }
}
