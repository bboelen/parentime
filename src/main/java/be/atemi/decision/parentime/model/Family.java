package be.atemi.decision.parentime.model;

import java.util.HashSet;
import java.util.Set;

public class Family implements Comparable<Family> {

    private static int cursor = 0;

    private Integer id;

    private String name;

    private Address address;

    private Set<Child> children = new HashSet<>();

    private Set<Unavailability> unavailabilities = new HashSet<>();

    private Family(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addChild(Child child) {
        child.addFamily(this);
        this.children.add(child);
    }

    public void addUnavailability(Unavailability unavailability) {
        this.unavailabilities.add(unavailability);
    }

    public boolean includes(Child child) {
        return children.contains(child);
    }

    @Override
    public int compareTo(Family o) {
        return this.id.compareTo(o.id);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Family newInstance(String name) {
        return new Family(cursor++, name);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
