package be.atemi.decision.parentime.model;

public class Config {

    private int days;
    private int timeslots;

    public Config(int days, int timeslots) {
        this.days = days;
        this.timeslots = timeslots;
    }

    public int days() {
        return this.days;
    }

    public int timeslots() {
        return this.timeslots;
    }
}