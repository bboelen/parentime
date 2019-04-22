package be.atemi.decision.parentime.helper;

import be.atemi.decision.parentime.james.BestCirclePlanningSolution;
import be.atemi.decision.parentime.james.CirclePlanningSolution;
import be.atemi.decision.parentime.jenetics.StepfamilyChromosome;
import be.atemi.decision.parentime.jenetics.StepfamilyGene;
import be.atemi.decision.parentime.model.Person;
import be.atemi.decision.parentime.model.Stepfamily;
import io.jenetics.Genotype;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class PrettyPrinter {

    private static Map<Integer, String> stepfamilyColors = new HashMap<>();

    static {
        stepfamilyColors.put(0, "");
        stepfamilyColors.put(1, "|[draw,fill=gray!30]|");
        stepfamilyColors.put(2, "|[draw,fill=gray!60]|");
        stepfamilyColors.put(3, "|[draw,fill=gray!70]|");
        stepfamilyColors.put(4, "|[draw,fill=gray!80]|");
        stepfamilyColors.put(5, "|[draw,fill=gray!90]|");
    }

    public static void print(Genotype<StepfamilyGene> genotype, int timeslots, int days) {
        genotype.toSeq().stream().sorted(Comparator.comparingLong(c -> c.as(StepfamilyChromosome.class).child().getId())).forEach(chromosome -> {
            StepfamilyGene[] sequence = new StepfamilyGene[timeslots * days];
            chromosome.toSeq().toArray(sequence);
            cr();
            Person child = chromosome.as(StepfamilyChromosome.class).child();
            System.out.println(String.format(" * custody schedule for %s %s", child.getFirstName(), child.getLastName()));
            cr();
            print("");
            for (int i = 0; i < days; i++) {
                print(String.format("[    day %s    ]", i + 1));
            }
            cr();
            cr();
            for (int i = 0; i < timeslots; i++) {
                print(String.format("timeslot %s    |", String.format("%02d", i)));
                for (int j = 0; j < days; j++) {
                    print(sequence[i + j * timeslots].getAllele().getName());
                }
                cr();
            }
        });
    }

    public static void print(BestCirclePlanningSolution solution, int timeslots, int days) {
        for (Person child : solution.getBestSolution().getDeltaStructure().keySet()) {
            cr();
            System.out.println(String.format(" * custody schedule for %s %s", child.getFirstName(), child.getLastName()));
            cr();
            print("");
            for (int i = 0; i < days; i++) {
                print(String.format("[    day %s    ]", i + 1));
            }
            cr();
            cr();
            for (int i = 0; i < timeslots; i++) {
                print(String.format("timeslot %s    |", String.format("%02d", i)));
                for (int j = 0; j < days; j++) {
                    print(solution.getBestSolution().getDeltaStructure().get(child).get(i + j * timeslots).getName());
                }
                cr();
            }
        }
    }

    public static void toLatexFigure(Genotype<StepfamilyGene> genotype, int timeslots, int days) {

        outln("\\begin{figure}[ht!]");
        outln("    \\centering");

        genotype.toSeq().stream().sorted(Comparator.comparingLong(c -> c.as(StepfamilyChromosome.class).child().getId())).forEach(chromosome -> {
            StepfamilyGene[] sequence = new StepfamilyGene[timeslots * days];
            chromosome.toSeq().toArray(sequence);

            Person child = chromosome.as(StepfamilyChromosome.class).child();

            outln("    \\begin{subfigure}[b]{0.4\\textwidth}");
            outln("         \\begin{tikzpicture} [nodes in empty cells, ");
            outln("           nodes={minimum width=0.5cm, minimum height=0.5cm},");
            outln("           row sep=-\\pgflinewidth, column sep=-\\pgflinewidth]");
            outln("           border/.style={draw}");
            outln("           \\matrix(vector)[matrix of nodes,");
            outln("                row 1/.style={nodes={draw=none, minimum width=0.3cm}},");
            outln("                nodes={draw}]");
            outln("                {");


            out("                   ");
            for (int i = 0; i < days; i++) {
                out("& ");
            }

            outln("\\\\");

            for (int i = 0; i < timeslots; i++) {
                out("                   ");
                for (int j = 0; j < days; j++) {
                    Stepfamily sf = sequence[i + j * timeslots].getAllele();
                    out(stepfamilyColors.get(sf.getId()));
                    out("$\\phi_" + (sf.getId() + 1) + "$");
                    if (j < days - 1) out(" & ");
                }
                outln("\\\\");
            }

            outln("                };");
            outln("         \\end{tikzpicture}");
            outln("         \\caption{$\\chi_" + (child.getId() + 1) + " (" + child.getFirstName().substring(0, 1) + ")$}");
            outln("         \\label{fig:cfe_ga_solution_" + child.getFirstName().substring(0, 1).toLowerCase() + "}");
            outln("    \\end{subfigure}");


            if (child.getId() < 3) {
                outln("         ~");
            }
        });

        outln("    \\caption{Solution possible du CFE}\\label{fig:cfe_ga_solution}");
        outln("\\end{figure}");
    }

    private static void out(Object o) {
        System.out.print(o);
    }

    private static void outln(Object o) {
        System.out.println(o);
    }

    private static void print(Object o) {
        System.out.print(String.format("%20s", o));
    }

    private static void cr() {
        System.out.println();
    }
}
