package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import org.jamesframework.core.problems.sol.Solution;

import java.util.*;

public class CirclePlanningSolution extends Solution {

    private Map<Person, List<Stepfamily>> deltaStructure;

    public CirclePlanningSolution(Map<Person, List<Stepfamily>> deltaStructure) {
        this.deltaStructure = deltaStructure;
    }

    @Override
    public Solution copy() {
        return new CirclePlanningSolution(new HashMap<>(deltaStructure));
    }

    public Set<Person> children() {
        return deltaStructure.keySet();
    }

    /**
     * @return days * timeslots size.
     */
    public int size() {
        Person first = deltaStructure.keySet().iterator().next();
        return deltaStructure.get(first) != null ? deltaStructure.get(first).size() : 0;
    }

    public void swapStepfamilies(Person child, int i, int j) {
        Collections.swap(deltaStructure.get(child), i, j);
    }

    public Map<Person, List<Stepfamily>> getDeltaStructure() {
        return deltaStructure;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final CirclePlanningSolution other = (CirclePlanningSolution) o;
        return Objects.equals(this.deltaStructure, other.deltaStructure);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deltaStructure);
    }
}
