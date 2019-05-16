package be.atemi.decision.parentime.james.analysis;

import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import mjson.Json;
import org.jamesframework.ext.analysis.JsonConverter;

public final class CirclePlanningSolution2JsonConverter implements JsonConverter<CirclePlanningSolution> {

    private static final CirclePlanningSolution2JsonConverter INSTANCE = new CirclePlanningSolution2JsonConverter();

    private CirclePlanningSolution2JsonConverter() {
    }

    @Override
    public Json toJson(CirclePlanningSolution solution) {
        Json json = Json.object();
        for (Person child : solution.getDeltaStructure().keySet()) {
            Json planning = Json.array();
            for (Stepfamily stepfamily : solution.getDeltaStructure().get(child)) {
                Json sp = Json.make(stepfamily.getName());
                planning.add(sp);
            }
            json.set(child.getFirstName() + " " + child.getLastName(), planning);
        }
        return json;
    }

    public static CirclePlanningSolution2JsonConverter getInstance() {
        return INSTANCE;
    }
}
