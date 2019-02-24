package be.atemi.decision.parentime.model;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.HashSet;
import java.util.Set;

public class Person {

    private static int cursor = 0;

    private Integer id;

    private String firstName;

    private String lastName;

    /**
     * A parent is part of a family. This field is empty if it is a child.
     */
    private Stepfamily stepfamily;

    private Set<Person> parents = new HashSet<>();

    private Set<Person> children = new HashSet<>();

    private LocalDate dateOfBirth;

    private Agenda agenda;

    private Person(Integer id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void addChild(Person child) {
        this.children.add(child);
        child.parents.add(this);
    }

    public void addChildren(Person... children) {
        for (Person child : children) {
            addChild(child);
        }
    }

    public int getAge() {
        return new Period(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public Set<Person> getChildren() {
        return children;
    }

    public Set<Person> getParents() {
        return parents;
    }

    public void setStepfamily(Stepfamily stepfamily) {
        this.stepfamily = stepfamily;
    }

    public Set<Stepfamily> stepfamilies() {
        Set<Stepfamily> stepfamilies = new HashSet<>();
        getParents().forEach(person -> {
            if (person.getStepfamily() != null) {
                stepfamilies.add(person.getStepfamily());
            }
        });
        return stepfamilies;
    }

    public boolean isMemberOf(Stepfamily stepfamily) {
        return stepfamilies().contains(stepfamily);
    }

    public Stepfamily getStepfamily() {
        return stepfamily;
    }

    public static Person newInstance(String firstName, String lastName, LocalDate dateOfBirth) {
        return new Person(cursor++, firstName, lastName, dateOfBirth);
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
