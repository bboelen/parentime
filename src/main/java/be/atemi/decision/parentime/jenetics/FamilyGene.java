package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.model.Family;
import io.jenetics.BoundedGene;

import java.util.Set;

public final class FamilyGene implements BoundedGene<Family, FamilyGene> {

    private final Family _value;
    private final Family[] _families;

    private FamilyGene(final Family value, Set<Family> families) {
        _value = value;
        _families = (Family[]) families.toArray();
    }

    @Override
    public Family getMin() {
        return (Family) _families[0];
    }

    @Override
    public Family getMax() {
        return (Family) _families[_families.length - 1];
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
}
