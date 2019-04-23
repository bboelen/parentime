package be.atemi.decision.parentime;

import be.atemi.decision.parentime.helper.CircleFileReader;
import be.atemi.decision.parentime.helper.PrettyPrinter;
import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningBestSolution;
import be.atemi.decision.parentime.james.valuetype.SearchAlgorithm;
import be.atemi.decision.parentime.jenetics.BestCirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.CirclePlanningGenotype;
import be.atemi.decision.parentime.jenetics.constraint.Constraint;
import be.atemi.decision.parentime.jenetics.constraint.hard.AvailabilityConstraint;
import be.atemi.decision.parentime.jenetics.constraint.hard.FullNightMorningConstraint;
import be.atemi.decision.parentime.jenetics.constraint.hard.RatioConstraint;
import be.atemi.decision.parentime.jenetics.constraint.soft.MinTransitionConstraint;
import be.atemi.decision.parentime.model.Agenda;
import be.atemi.decision.parentime.model.Circle;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class DummyParentime {

    public static final int TIME_SLOTS = 4;
    public static final int DAYS = 7;

    public static Circle dummyCircle() {

        /**
         * ----------------------------------------------------------------
         * Definition of children.
         * ----------------------------------------------------------------
         */
        Person d = Person.newInstance("Diomède", "Spuckler", LocalDate.parse("2010-12-01"));
        Person e = Person.newInstance("Ezéchiel", "Spuckler", LocalDate.parse("2011-12-01"));
        Person g = Person.newInstance("Gary", "Melia", LocalDate.parse("2012-01-01"));
        Person h = Person.newInstance("Hadrien", "Melia", LocalDate.parse("2014-01-03"));


        /**
         * ----------------------------------------------------------------
         * Definition of tutors.
         * ----------------------------------------------------------------
         */
        Person a = Person.newInstance("Ariane", "Field", LocalDate.parse("1983-10-02"));
        Person b = Person.newInstance("Bakari", "Constant", LocalDate.parse("1982-12-03"));
        Person c = Person.newInstance("Cleitus", "Spuckler", LocalDate.parse("1981-11-05"));
        Person f = Person.newInstance("Fulvio", "Melia", LocalDate.parse("1981-02-01"));

        /**
         * ----------------------------------------------------------------
         * Definition of agendas.
         * ----------------------------------------------------------------
         */
        Agenda flexible = new Agenda(DAYS, TIME_SLOTS);
        flexible.setEntry(0, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(1, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(2, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(3, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(4, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(5, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(6, 1, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        flexible.setEntry(6, 2, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);

        Agenda mixed = new Agenda(DAYS, TIME_SLOTS);
        mixed.setEntry(0, 1, Agenda.Entry.FIXED_UNAVAILABILITY);
        mixed.setEntry(2, 3, Agenda.Entry.FIXED_UNAVAILABILITY);
        mixed.setEntry(3, 2, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        mixed.setEntry(3, 3, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);
        mixed.setEntry(6, 2, Agenda.Entry.FLEXIBLE_UNAVAILABILITY);

        Agenda empty = new Agenda(DAYS, TIME_SLOTS);

        Agenda fixed = new Agenda(DAYS, TIME_SLOTS);
        fixed.setEntry(0, 1, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(0, 2, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(0, 3, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(1, 0, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(1, 1, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(1, 2, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(1, 3, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(2, 0, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(2, 1, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(2, 2, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(2, 3, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(3, 0, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(3, 1, Agenda.Entry.FIXED_UNAVAILABILITY);
        fixed.setEntry(3, 2, Agenda.Entry.FIXED_UNAVAILABILITY);

        a.setAgenda(flexible);
        b.setAgenda(mixed);
        c.setAgenda(empty);
        f.setAgenda(fixed);

        /**
         * ----------------------------------------------------------------
         * Definition of parental links.
         * ----------------------------------------------------------------
         */
        a.addChildren(d, e);
        b.addChildren(d, e);
        c.addChildren(g, h);
        f.addChildren(g, h);

        /**
         * ----------------------------------------------------------------
         * Definition of stepfamilies.
         * ----------------------------------------------------------------
         */
        Stepfamily phi_1 = Stepfamily.newInstance("phi 1");
        Stepfamily phi_2 = Stepfamily.newInstance("phi 2");
        Stepfamily phi_3 = Stepfamily.newInstance("phi 3");

        phi_1.addTutors(a, c);
        phi_2.addTutor(b);
        phi_3.addTutor(f);

        /**
         * ----------------------------------------------------------------
         * Definition of a circle.
         * ----------------------------------------------------------------
         */
        Circle circle = Circle.newInstance("crop circle");
        circle.addStepfamily(phi_1);
        circle.addStepfamily(phi_2);
        circle.addStepfamily(phi_3);

        return circle;
    }

    public static Set<Constraint> geneticAlgorithmConstraints() {

        /**
         * ----------------------------------------------------------------
         * Definition of geneticAlgorithmConstraints.
         * ----------------------------------------------------------------
         */
        Set<Constraint> constraints = new HashSet<>();

        constraints.add(new AvailabilityConstraint());
        constraints.add(new FullNightMorningConstraint());
        constraints.add(new RatioConstraint());
        constraints.add(new MinTransitionConstraint());

        return constraints;
    }

    public static Set<org.jamesframework.core.problems.constraints.Constraint> variableNeighbourhoodSearchConstraints() {

        /**
         * ----------------------------------------------------------------
         * Definition of variableNeighbourhoodSearchConstraints.
         * ----------------------------------------------------------------
         */

        Set<org.jamesframework.core.problems.constraints.Constraint> constraints = new HashSet<>();

        constraints.add(new be.atemi.decision.parentime.james.constraint.hard.FullNightMorningConstraint());
        constraints.add(new be.atemi.decision.parentime.james.constraint.hard.AvailabilityConstraint());

        return constraints;

    }

    public static void main(String... args) {

        Circle efc = CircleFileReader.getInstance().read("efc.xml");

        //geneticAlgorithmCompute(efc);
        variableNeighbourhoodSearchCompute(efc);

    }

    private static void geneticAlgorithmCompute(Circle circle) {

        /**
         * ----------------------------------------------------------------
         * Computation of the circle planning (GA).
         * ----------------------------------------------------------------
         */
        BestCirclePlanningGenotype result = CirclePlanningGenotype.compute(circle, geneticAlgorithmConstraints());

        System.out.println("--------------------> AvailabilityConstraint     MAX : " + AvailabilityConstraint.MAX);
        System.out.println("--------------------> FullNightMorningConstraint MAX : " + FullNightMorningConstraint.MAX);
        System.out.println("--------------------> RatioConstraint            MAX : " + RatioConstraint.MAX);
        System.out.println("--------------------> MinTransitionConstraint    MAX : " + MinTransitionConstraint.MAX);

        System.out.println(result.getStatistics());

        PrettyPrinter.print(result.getGenotype(), circle.config().timeslots(), circle.config().days());

        PrettyPrinter.toLatexFigure(result.getGenotype(), circle.config().timeslots(), circle.config().days());
    }

    private static void variableNeighbourhoodSearchCompute(Circle circle) {

        /**
         * ----------------------------------------------------------------
         * Computation of the circle planning (VNS).
         * ----------------------------------------------------------------
         */
        BestCirclePlanningSolution result = CirclePlanningBestSolution.compute(circle, variableNeighbourhoodSearchConstraints(), 1, SearchAlgorithm.VARIABLE_NEIGHBOURHOOD_SEARCH, 10);

        PrettyPrinter.print(result, circle.config().timeslots(), circle.config().days());

        PrettyPrinter.toLatexFigure(result, circle.config().timeslots(), circle.config().days());
    }
}
