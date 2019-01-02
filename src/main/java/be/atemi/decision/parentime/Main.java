package be.atemi.decision.parentime;

import be.atemi.decision.parentime.constraint.Constraint;
import be.atemi.decision.parentime.constraint.soft.FullNightMorningConstraint;
import be.atemi.decision.parentime.jenetics.Timetable;
import be.atemi.decision.parentime.model.Child;
import be.atemi.decision.parentime.model.Family;

import java.util.HashSet;
import java.util.Set;

import static be.atemi.decision.parentime.model.Unavailability.from;

public class Main {

    public static void main(String... args) {

        /**
         * ----------------------------------------------------------------
         * Definition of children.
         * ----------------------------------------------------------------
         */

        Set<Child> children = new HashSet<>();

        Child kevin = Child.newInstance("kevin");
        Child brandon = Child.newInstance("brandon");
        Child julien = Child.newInstance("julien");

        children.add(kevin);
        children.add(brandon);
        children.add(julien);

        /**
         * ----------------------------------------------------------------
         * Definition of fraternal links.
         * ----------------------------------------------------------------
         */
        Child.fraternize(kevin, brandon);

        /**
         * ----------------------------------------------------------------
         * Definition of families.
         * ----------------------------------------------------------------
         */

        Family bob = Family.newInstance("bob");
        Family alice_cedric = Family.newInstance("alice - cédric");
        Family pauline = Family.newInstance("pauline");

        /**
         * ----------------------------------------------------------------
         * Definition of family relationships.
         * ----------------------------------------------------------------
         */

        bob.addChild(kevin);
        bob.addChild(brandon);
        alice_cedric.addChild(kevin);
        alice_cedric.addChild(brandon);
        alice_cedric.addChild(julien);
        pauline.addChild(julien);

        /**
         * ----------------------------------------------------------------
         * Definition of availabilities.
         * ----------------------------------------------------------------
         */

        bob.addUnavailability(from(0, 0).to(0, 7).recurrent());

        /**
         * ----------------------------------------------------------------
         * Definition of constraints.
         * ----------------------------------------------------------------
         */

        Set<Constraint> constraints = new HashSet<>();

        //constraints.add(new MinTransitionConstraint());
        //constraints.add(new FiftyFiftyConstraint());
        constraints.add(new FullNightMorningConstraint());

        /**
         * ----------------------------------------------------------------
         * Creation of the timetable.
         * ----------------------------------------------------------------
         */

        Timetable.build(children, 8, 27, constraints);
    }
}
