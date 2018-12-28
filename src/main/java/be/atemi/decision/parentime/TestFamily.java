package be.atemi.decision.parentime;

import be.atemi.decision.parentime.model.Child;
import be.atemi.decision.parentime.model.Family;

import java.util.HashSet;
import java.util.Set;

public class TestFamily {

    public static void main(String... args) {

        Child kevin = Child.newInstance("kevin");

        Family bob = Family.newInstance("bob family");
        Family alice = Family.newInstance("alice family");

        Set<Family> families = new HashSet<>();
        Set<Child> children = new HashSet<>();

        families.add(bob);
        families.add(alice);
        children.add(kevin);

        bob.addChild(kevin);
        alice.addChild(kevin);

        Timetable.build(families, children, 24, 28);
    }
}
