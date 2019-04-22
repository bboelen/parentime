package be.atemi.decision.parentime.helper;

import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;

import java.util.Random;
import java.util.Set;

public final class RandomStepfamilySelector {

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
    public static Stepfamily nextFamily(final Random random, final Person child) {
        Set<Stepfamily> stepfamilies = child.stepfamilies();
        Stepfamily[] families = new Stepfamily[stepfamilies.size()];
        stepfamilies.toArray(families);
        int index = random.nextInt(families.length);
        return families[index];
    }
}
