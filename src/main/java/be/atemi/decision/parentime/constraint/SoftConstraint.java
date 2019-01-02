package be.atemi.decision.parentime.constraint;

public abstract class SoftConstraint extends Constraint {

    @Override
    public int weight() {
        return 1;
    }
}
