package be.atemi.decision.parentime.jenetics;

import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.util.ISeq;
import io.jenetics.util.IntRange;

public class FamilyChromosome extends IntegerChromosome {


    protected FamilyChromosome(ISeq<IntegerGene> genes, IntRange lengthRange) {
        super(genes, lengthRange);
    }
}
