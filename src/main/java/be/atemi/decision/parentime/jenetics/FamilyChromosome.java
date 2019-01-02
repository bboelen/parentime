package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.model.Child;
import io.jenetics.AbstractChromosome;
import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;

public class FamilyChromosome extends AbstractChromosome<FamilyGene> {

    private final Child _child;
    private final int _timeslots;
    private final int _days;

    protected FamilyChromosome(final ISeq<FamilyGene> genes, final Child child, final int timeslots, final int days) {
        super(genes);
        _child = child;
        _timeslots = timeslots;
        _days = days;
    }

    @Override
    public Chromosome<FamilyGene> newInstance(ISeq<FamilyGene> genes) {
        return new FamilyChromosome(genes, _child, _timeslots, _days);
    }

    @Override
    public Chromosome<FamilyGene> newInstance() {
        return of(_child, _timeslots, _days);
    }

    /* *************************************************************************
     * Static factory methods.
     * ************************************************************************/

    public static FamilyChromosome of(final Child child, final int timeslots, final int days) {
        final ISeq<FamilyGene> values = FamilyGene.seq(child, timeslots, days);
        return new FamilyChromosome(values, child, timeslots, days);
    }

    public Child child() {
        return _child;
    }

    public int timeslots() { return _timeslots; }

    public int days() { return _days; }
}
