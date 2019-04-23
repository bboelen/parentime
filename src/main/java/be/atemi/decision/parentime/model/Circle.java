package be.atemi.decision.parentime.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Circle implements Serializable {

    private static int cursor = 0;

    private Integer id;

    private String name;

    private Set<Stepfamily> stepfamilies = new HashSet<>();

    private Config config;

    private Circle(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addStepfamily(Stepfamily stepfamily) {
        this.stepfamilies.add(stepfamily);
    }

    public Set<Stepfamily> getStepfamilies() {
        return stepfamilies;
    }

    public static Circle newInstance(String name) {
        return new Circle(cursor++, name);
    }

    public Set<Person> children() {
        Set<Person> children = new HashSet<>();
        stepfamilies.forEach(stepfamily -> stepfamily.getTutors().forEach(person -> children.addAll(person.getChildren())));
        return children;
    }

    public Set<Person> tutors() {
        Set<Person> tutors = new HashSet<>();
        stepfamilies.forEach(stepfamily -> stepfamily.getTutors().forEach(person -> tutors.add(person)));
        return tutors;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config config() {
        return config;
    }
}
