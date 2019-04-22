package be.atemi.decision.parentime.james.neighbourhood;

import be.atemi.decision.parentime.james.CirclePlanningData;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.james.move.CirclePlanningRandomMove;
import be.atemi.decision.parentime.model.Person;
import org.jamesframework.core.search.neigh.Move;
import org.jamesframework.core.search.neigh.Neighbourhood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CirclePlanningRandomNeighbourhood implements Neighbourhood<CirclePlanningSolution> {

    private CirclePlanningData data;

    public CirclePlanningRandomNeighbourhood(CirclePlanningData data) {
        this.data = data;
    }

    @Override
    public Move<? super CirclePlanningSolution> getRandomMove(CirclePlanningSolution solution, Random random) {
        int c = random.nextInt(solution.children().size());
        List<Person> children = new ArrayList<>(solution.getDeltaStructure().keySet());
        Person child = children.get(c);
        return new CirclePlanningRandomMove(child, data);
    }

    @Override
    public List<? extends Move<? super CirclePlanningSolution>> getAllMoves(CirclePlanningSolution solution) {
        List<CirclePlanningRandomMove> moves = new ArrayList<>();
        for (Person child : solution.getDeltaStructure().keySet()) {
            moves.add(new CirclePlanningRandomMove(child, data));
        }
        return moves;
    }
}
