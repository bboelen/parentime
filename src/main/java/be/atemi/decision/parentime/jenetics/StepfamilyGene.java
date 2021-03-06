package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.helper.RandomStepfamilySelector;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Gene;
import io.jenetics.util.ISeq;
import io.jenetics.util.MSeq;

import java.util.Random;
import java.util.Set;

import static io.jenetics.util.RandomRegistry.getRandom;

public final class StepfamilyGene implements Gene<Stepfamily, StepfamilyGene>, Comparable<StepfamilyGene> {

    private final Stepfamily _stepfamily;
    private final Person _child;

    private StepfamilyGene(final Stepfamily value, final Person child) {
        _stepfamily = value;
        _child = child;
    }

    @Override
    public Stepfamily getAllele() {
        return _stepfamily;
    }

    @Override
    public StepfamilyGene newInstance() {
        return StepfamilyGene.of(RandomStepfamilySelector.nextFamily(getRandom(), _child), _child);
    }

    @Override
    public StepfamilyGene newInstance(Stepfamily stepfamily) {
        return StepfamilyGene.of(stepfamily, _child);
    }

    @Override
    public boolean isValid() {
        return _child.isMemberOf(_stepfamily);
    }

    /* *************************************************************************
     * Static factory methods.
     * ************************************************************************/

    /**
     * Create a new random {@code StepfamilyGene} with the given family and the
     * given child. If the {@code family} isn't related to the child,
     * no exception is thrown. In this case the method
     * {@link StepfamilyGene#isValid()} returns {@code false}.
     *
     * @param family the value of the gene.
     * @param child  the child related to the family.*
     * @return a new {@code FamilyGene} with the given {@code family}
     */
    public static StepfamilyGene of(final Stepfamily family, final Person child) {
        return new StepfamilyGene(family, child);
    }

    static ISeq<StepfamilyGene> seq(final Person child, final int timeslots, final int days) {
        final Random r = getRandom();
        return MSeq.<StepfamilyGene>ofLength(timeslots * days).fill(() -> new StepfamilyGene(RandomStepfamilySelector.nextFamily(r, child), child)).toISeq();
    }

    @Override
    public int compareTo(StepfamilyGene o) {
        return _stepfamily.getId().compareTo(o._stepfamily.getId());
    }
}
