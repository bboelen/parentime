package be.atemi.decision.parentime.model;

import java.util.HashSet;
import java.util.Set;

public class Child implements Comparable<Child> {

    private static int cursor = 0;

    private Integer id;

    private String name;

    private Set<Family> families = new HashSet<>();

    private Child(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public boolean isMemberOf(Family family) {
        return families.contains(family);
    }

    public Set<Family> getFamilies() { return families; }

    @Override
    public int compareTo(Child o) {
        return this.id.compareTo(o.id);
    }

    public Integer getId() { return id; }

    public String getName() {
        return name;
    }

    public static Child newInstance(String name) {
        return new Child(cursor++, name);
    }
}
