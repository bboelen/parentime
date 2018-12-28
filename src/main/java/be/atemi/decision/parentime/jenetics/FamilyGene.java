package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.model.Child;
import be.atemi.decision.parentime.model.Family;
import io.jenetics.Gene;

public final class FamilyGene implements Gene<Family, FamilyGene> {

    private final Family _value;
    private final Child _child;

    private FamilyGene(final Family value, final Child child) {
        _value = value;
        _child = child;
    }

    @Override
    public Family getAllele() {
        return _value;
    }

    @Override
    public FamilyGene newInstance() {
        return null;
    }

    @Override
    public FamilyGene newInstance(Family value) {
        return null;
    }

    @Override
    public boolean isValid() {
        return _child.isMemberOf(_value);
    }
}
