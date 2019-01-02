package be.atemi.decision.parentime.constraint;

public abstract class HardConstraint extends Constraint {

    @Override
    public int weight() { return 1000; }
}
