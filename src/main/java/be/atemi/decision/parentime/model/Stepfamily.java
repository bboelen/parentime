package be.atemi.decision.parentime.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Stepfamily implements Comparable<Stepfamily>, Serializable {

    private static int cursor = 0;

    private Integer id;

    private Set<Person> tutors = new HashSet<>();

    private String name;

    private Address address;

    private Stepfamily(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addTutor(Person tutor) {
        this.tutors.add(tutor);
        tutor.setStepfamily(this);
    }

    public void addTutors(Person... tutors) {
        for (Person tutor : tutors) {
            addTutor(tutor);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Person> getTutors() {
        return tutors;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Person tutor : tutors) {
            buffer.append(tutor.getLastName()).append(" - ");
        }
        buffer.replace(buffer.length() - 3, buffer.length(), "");
        return buffer.toString();
    }

    @Override
    public int compareTo(Stepfamily o) {
        return getId().compareTo(o.getId());
    }

    public static Stepfamily newInstance(String name) {
        return new Stepfamily(cursor++, name);
    }

    public boolean isSingleTutor() {
        return tutors.size() == 1;
    }
}
