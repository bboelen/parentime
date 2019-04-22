package be.atemi.decision.parentime.james;

import be.atemi.decision.parentime.model.Person;
import org.jamesframework.core.search.neigh.Move;

import java.util.ArrayList;
import java.util.List;

public class CirclePlanning2OptMove implements Move<CirclePlanningSolution> {

    private final int c;
    private final int i;
    private final int j;

    public CirclePlanning2OptMove(int c, int i, int j) {
        // check
        if (i == j) {
            throw new IllegalArgumentException("Error: i and j should be distinct positions.");
        }
        // store
        this.c = c;
        this.i = i;
        this.j = j;
    }

    public int getC() {
        return c;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public void apply(CirclePlanningSolution solution) {
        // reverse subpath
        int start = i;
        int stop = j;
        int n = solution.getDeltaStructure().size();
        int reversedLength;
        if (i < j) {
            reversedLength = j - i + 1;
        } else {
            reversedLength = n - (i - j - 1);
        }
        int numSwaps = reversedLength / 2;
        for (int k = 0; k < numSwaps; k++) {
            List<Person> children = new ArrayList<>(solution.getDeltaStructure().keySet());
            solution.swapStepfamilies(children.get(c), start, stop);
            start = (start + 1) % n;
            stop = (stop - 1 + n) % n;
        }
    }

    @Override
    public void undo(CirclePlanningSolution solution) {
        // undo reversing again
        apply(solution);
    }
}
