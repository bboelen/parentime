package be.atemi.decision.parentime.constraint.soft;

import be.atemi.decision.parentime.constraint.SoftConstraint;
import be.atemi.decision.parentime.jenetics.FamilyGene;

import java.util.HashMap;
import java.util.Map;

public class FiftyFiftyConstraint extends SoftConstraint {

    @Override
    public int cost(int chromosomeIndex, FamilyGene[] genes, int timeslots, int days) {

        Map<String, Integer> ratios = new HashMap();

        for (int i = 0; i < genes.length; i++) {

            String name = genes[i].getAllele().getName();

            if (!ratios.containsKey(name)) {
                ratios.put(name, 1);
            }

            ratios.put(name, ratios.get(name) + 1);
        }

        int cost = -1;

        for (Map.Entry<String, Integer> entry : ratios.entrySet()) {
            cost = (cost == -1) ? entry.getValue() : (cost - entry.getValue() == 0 ? 0 : 1);

        }

        return Math.abs(cost);
    }
}
