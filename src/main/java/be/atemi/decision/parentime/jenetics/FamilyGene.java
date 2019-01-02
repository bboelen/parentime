package be.atemi.decision.parentime.jenetics;

import be.atemi.decision.parentime.model.Child;
import be.atemi.decision.parentime.model.Family;
import io.jenetics.Gene;
import io.jenetics.util.ISeq;
import io.jenetics.util.MSeq;

import java.util.Random;

import static io.jenetics.util.RandomRegistry.getRandom;

public final class FamilyGene implements Gene<Family, FamilyGene> {

    private final Family _family;
    private final Child _child;

    private FamilyGene(final Family value, final Child child) {
        _family = value;
        _child = child;
    }

    @Override
    public Family getAllele() {
        return _family;
    }

    @Override
    public FamilyGene newInstance() {
        return FamilyGene.of(nextFamily(getRandom(), _child), _child);
    }

    @Override
    public FamilyGene newInstance(Family family) {
        return FamilyGene.of(family, _child);
    }

    @Override
    public boolean isValid() {
        return _child.isMemberOf(_family);
    }

    /* *************************************************************************
     * Static factory methods.
     * ************************************************************************/

    /**
     * Create a new random {@code FamilyGene} with the given family and the
     * given child. If the {@code family} isn't related to the child,
     * no exception is thrown. In this case the method
     * {@link FamilyGene#isValid()} returns {@code false}.
     *
     * @param family the value of the gene.
     * @param child  the child related to the family.*
     * @return a new {@code FamilyGene} with the given {@code family}
     */
    public static FamilyGene of(final Family family, final Child child) {
        return new FamilyGene(family, child);
    }

    static ISeq<FamilyGene> seq(final Child child, final int timeslots, final int days) {
        final Random r = getRandom();
        return MSeq.<FamilyGene>ofLength(timeslots * days).fill(() -> new FamilyGene(nextFamily(r, child), child)).toISeq();
    }

    /**
     * Returns a pseudo-random, uniformly distributed family a family of which the child is a member..
     *
     * @param random the random engine to use for calculating the random int
     *               value
     * @param child  the child concerned by the family*
     * @return a random family responsible for the {@code child}
     * @throws NullPointerException if the given {@code random}
     *                              engine is {@code null} or if the given {@code child} is {@code null}.
     */
    static Family nextFamily(final Random random, final Child child) {

        Family[] families = new Family[child.getFamilies().size()];
        child.getFamilies().toArray(families);

        int index = random.nextInt(families.length);

        return families[index];
    }
}
