package be.atemi.decision.parentime.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Child implements Comparable<Child> {

    private static int cursor = 0;

    private Integer id;

    private String name;

    private Set<Family> families = new HashSet<>();

    private Set<Child> siblings = new HashSet<>();

    private Child(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSibling(Child child) {
        this.siblings.add(child);
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public boolean isMemberOf(Family family) {
        return families.contains(family);
    }

    public boolean isSiblingOf(Child child) {
        return siblings.contains(child);
    }

    public Set<Family> getFamilies() {
        return families;
    }

    @Override
    public int compareTo(Child o) {
        return this.id.compareTo(o.id);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Child newInstance(String name) {
        return new Child(cursor++, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(id, child.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void fraternize(Child... childs) {
        for (int i = 0; i < childs.length; i++) {
            for (int j = 0; j < childs.length; j++) {
                if (i != j) {
                    childs[i].addSibling(childs[j]);
                }
            }
        }
    }
}
