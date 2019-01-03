package be.atemi.decision.parentime;

import be.atemi.decision.parentime.constraint.Constraint;
import be.atemi.decision.parentime.constraint.soft.FiftyFiftyConstraint;
import be.atemi.decision.parentime.constraint.soft.FullNightMorningConstraint;
import be.atemi.decision.parentime.helper.PrettyPrinter;
import be.atemi.decision.parentime.jenetics.BestCirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.CirclePlanningGenotype;
import be.atemi.decision.parentime.model.Circle;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class DummyParentime {

    public static final int TIME_SLOTS = 8;
    public static final int DAYS = 28;

    public static Circle dummyCircle() {

        /**
         * ----------------------------------------------------------------
         * Definition of children.
         * ----------------------------------------------------------------
         */
        Person kev = Person.newInstance("Kevin", "Léponge", LocalDate.parse("2010-12-01"));
        Person bra = Person.newInstance("Brandon", "Léponge", LocalDate.parse("2011-12-01"));
        Person jul = Person.newInstance("Julien", "Lepetit", LocalDate.parse("2012-01-01"));


        /**
         * ----------------------------------------------------------------
         * Definition of tutors.
         * ----------------------------------------------------------------
         */
        Person bob = Person.newInstance("Bob", "Léponge", LocalDate.parse("1982-12-03"));
        Person ali = Person.newInstance("Alice", "Carroll", LocalDate.parse("1983-10-02"));
        Person ced = Person.newInstance("Cédric", "Lepetit", LocalDate.parse("1981-11-05"));
        Person pau = Person.newInstance("Pauline", "Leclerc", LocalDate.parse("1981-02-01"));

        /**
         * ----------------------------------------------------------------
         * Definition of parental links.
         * ----------------------------------------------------------------
         */
        bob.addChildren(kev, bra);
        ali.addChildren(kev, bra);
        ced.addChildren(jul);
        pau.addChildren(jul);

        /**
         * ----------------------------------------------------------------
         * Definition of stepfamilies.
         * ----------------------------------------------------------------
         */
        Stepfamily bobMonoFam = Stepfamily.newInstance("A");
        Stepfamily aliCedrFam = Stepfamily.newInstance("B");
        Stepfamily pauMonoFam = Stepfamily.newInstance("C");

        bobMonoFam.addTutor(bob);
        aliCedrFam.addTutors(ali, ced);
        pauMonoFam.addTutor(pau);

        /**
         * ----------------------------------------------------------------
         * Definition of a circle.
         * ----------------------------------------------------------------
         */
        Circle circle = Circle.newInstance("crop circle");
        circle.addStepfamily(bobMonoFam);
        circle.addStepfamily(aliCedrFam);
        circle.addStepfamily(pauMonoFam);

        return circle;
    }

    public static Set<Constraint> constraints() {

        /**
         * ----------------------------------------------------------------
         * Definition of constraints.
         * ----------------------------------------------------------------
         */
        Set<Constraint> constraints = new HashSet<>();

        //constraints.add(new MinTransitionConstraint());
        constraints.add(new FiftyFiftyConstraint());
        constraints.add(new FullNightMorningConstraint());

        return constraints;
    }

    public static void main(String... args) {

        /**
         * ----------------------------------------------------------------
         * Computation of the circle planning.
         * ----------------------------------------------------------------
         */
        BestCirclePlanningGenotype result = CirclePlanningGenotype.compute(dummyCircle(), TIME_SLOTS, DAYS, constraints());

        System.out.println(result.getStatistics());

        PrettyPrinter.print(result.getGenotype(), TIME_SLOTS, DAYS);
    }
}
