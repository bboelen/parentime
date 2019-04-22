package be.atemi.decision.parentime.james;

import org.jamesframework.core.search.neigh.Move;
import org.jamesframework.core.search.neigh.Neighbourhood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CirclePlanning2OptNeighbourhood implements Neighbourhood<CirclePlanningSolution> {

    @Override
    public Move<? super CirclePlanningSolution> getRandomMove(CirclePlanningSolution solution, Random random) {

        int n = solution.getDeltaStructure().size();
        int c = random.nextInt(solution.children().size());
        int i = random.nextInt(n);
        int j = random.nextInt(n - 1);
        if (j >= i) {
            j++;
        }
        // return 2-opt Circle planning that reverse path from position i to j
        return new CirclePlanning2OptMove(c, i, j);
    }

    @Override
    public List<? extends Move<? super CirclePlanningSolution>> getAllMoves(CirclePlanningSolution solution) {
        // generate a 2-opt Circle planning move for every tuple of positions c, i, j
        int n = solution.getDeltaStructure().size();
        List<CirclePlanning2OptMove> moves = new ArrayList<>();
        for (int c = 0; c < solution.getDeltaStructure().keySet().size(); c++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        moves.add(new CirclePlanning2OptMove(c, i, j));
                    }
                }
            }
        }

        return moves;
    }
}
