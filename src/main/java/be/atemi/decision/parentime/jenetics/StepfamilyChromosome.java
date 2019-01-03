package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.model.Person;
import io.jenetics.AbstractChromosome;
import io.jenetics.Chromosome;
import io.jenetics.util.ISeq;

public class StepfamilyChromosome extends AbstractChromosome<StepfamilyGene> {

    private final Person _child;
    private final int _timeslots;
    private final int _days;

    protected StepfamilyChromosome(final ISeq<StepfamilyGene> genes, final Person child, final int timeslots, final int days) {
        super(genes);
        _child = child;
        _timeslots = timeslots;
        _days = days;
    }

    @Override
    public Chromosome<StepfamilyGene> newInstance(ISeq<StepfamilyGene> genes) {
        return new StepfamilyChromosome(genes, _child, _timeslots, _days);
    }

    @Override
    public Chromosome<StepfamilyGene> newInstance() {
        return of(_child, _timeslots, _days);
    }

    /* *************************************************************************
     * Static factory methods.
     * ************************************************************************/

    public static StepfamilyChromosome of(final Person child, final int timeslots, final int days) {
        final ISeq<StepfamilyGene> values = StepfamilyGene.seq(child, timeslots, days);
        return new StepfamilyChromosome(values, child, timeslots, days);
    }

    public Person child() {
        return _child;
    }

    public int timeslots() {
        return _timeslots;
    }

    public int days() {
        return _days;
    }
}
