package be.atemi.decision.parentime.james.constraint;

public abstract class SoftConstraint extends Constraint {

    @Override
    public int min() {
        return 0;
    }

    public double weightedCost(double cost) {
        return weight() * ((cost - min()) / (max() - min()));
    }
}
