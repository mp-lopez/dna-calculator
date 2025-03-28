package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DnaSequenceTest {
    private DnaSequence emptyDnaSequence;
    private DnaSequence dnaSequenceACGTTC;
    private DnaSequence dnaSequenceA;
    private DnaSequence dnaSequenceC;
    private DnaSequence dnaSequenceG;
    private DnaSequence dnaSequenceT;


    @BeforeEach
    void runBefore() {
        emptyDnaSequence = new DnaSequence();

        dnaSequenceA = new DnaSequence();
        dnaSequenceA.addNucleotideToSequence(DnaNucleotide.A);

        dnaSequenceC = new DnaSequence();
        dnaSequenceC.addNucleotideToSequence(DnaNucleotide.C);

        dnaSequenceG = new DnaSequence();
        dnaSequenceG.addNucleotideToSequence(DnaNucleotide.G);

        dnaSequenceT = new DnaSequence();
        dnaSequenceT.addNucleotideToSequence(DnaNucleotide.T);

        dnaSequenceACGTTC = new DnaSequence();
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.A);
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.C);
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.G);
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.T);
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.T);
        dnaSequenceACGTTC.addNucleotideToSequence(DnaNucleotide.C);
    }

    @Test
    void testConstructor() {
        DnaSequence newDnaSequence = new DnaSequence();
        assertTrue(newDnaSequence.getSequence().isEmpty());
    }

    @Test
    void testAddAdenineToSequence() {
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.A);
        assertEquals(1, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.A, emptyDnaSequence.getSequence().get(0));
    }

    @Test
    void testAddCytosineToSequence() {
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.C);
        assertEquals(1, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.C, emptyDnaSequence.getSequence().get(0));
    }

    @Test
    void testAddGuanineToSequence() {
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.G);
        assertEquals(1, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.G, emptyDnaSequence.getSequence().get(0));
    }

    @Test
    void testAddThymineToSequence() {
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.T);
        assertEquals(1, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.T, emptyDnaSequence.getSequence().get(0));
    }

    @Test
    void testAddMultipleNucleotidesToSequence() {
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.C);
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.C);
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.A);
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.T);
        emptyDnaSequence.addNucleotideToSequence(DnaNucleotide.G);
        assertEquals(5, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.C, emptyDnaSequence.getSequence().get(0));
        assertEquals(DnaNucleotide.C, emptyDnaSequence.getSequence().get(1));
        assertEquals(DnaNucleotide.A, emptyDnaSequence.getSequence().get(2));
        assertEquals(DnaNucleotide.T, emptyDnaSequence.getSequence().get(3));
        assertEquals(DnaNucleotide.G, emptyDnaSequence.getSequence().get(4));
    }

    @Test
    void testAddOneNucleotideSequenceToSequence() {
        emptyDnaSequence.addSequenceToSequence(dnaSequenceA);
        assertEquals(1, emptyDnaSequence.getSequence().size());
        assertEquals(DnaNucleotide.A, emptyDnaSequence.getSequence().get(0));
    }

    @Test
    void testAddSequenceToSequence() {
        DnaSequence dnaSequenceGTCA = new DnaSequence();
        dnaSequenceGTCA.addNucleotideToSequence(DnaNucleotide.G);
        dnaSequenceGTCA.addNucleotideToSequence(DnaNucleotide.T);
        dnaSequenceGTCA.addNucleotideToSequence(DnaNucleotide.C);
        dnaSequenceGTCA.addNucleotideToSequence(DnaNucleotide.A);

        dnaSequenceACGTTC.addSequenceToSequence(dnaSequenceGTCA);

        assertEquals(10, dnaSequenceACGTTC.getSequence().size());
        assertEquals(DnaNucleotide.A, dnaSequenceACGTTC.getSequence().get(0));
        assertEquals(DnaNucleotide.C, dnaSequenceACGTTC.getSequence().get(1));
        assertEquals(DnaNucleotide.G, dnaSequenceACGTTC.getSequence().get(2));
        assertEquals(DnaNucleotide.T, dnaSequenceACGTTC.getSequence().get(3));
        assertEquals(DnaNucleotide.T, dnaSequenceACGTTC.getSequence().get(4));
        assertEquals(DnaNucleotide.C, dnaSequenceACGTTC.getSequence().get(5));
        assertEquals(DnaNucleotide.G, dnaSequenceACGTTC.getSequence().get(6));
        assertEquals(DnaNucleotide.T, dnaSequenceACGTTC.getSequence().get(7));
        assertEquals(DnaNucleotide.C, dnaSequenceACGTTC.getSequence().get(8));
        assertEquals(DnaNucleotide.A, dnaSequenceACGTTC.getSequence().get(9));

    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyDnaSequence.isEmpty());
        assertFalse(dnaSequenceACGTTC.isEmpty());
    }

    @Test
    void testComplement() {
        assertEquals(6, dnaSequenceACGTTC.complement().getSequence().size());
        assertEquals(DnaNucleotide.T, dnaSequenceACGTTC.complement().getSequence().get(0));
        assertEquals(DnaNucleotide.G, dnaSequenceACGTTC.complement().getSequence().get(1));
        assertEquals(DnaNucleotide.C, dnaSequenceACGTTC.complement().getSequence().get(2));
        assertEquals(DnaNucleotide.A, dnaSequenceACGTTC.complement().getSequence().get(3));
        assertEquals(DnaNucleotide.A, dnaSequenceACGTTC.complement().getSequence().get(4));
        assertEquals(DnaNucleotide.G, dnaSequenceACGTTC.complement().getSequence().get(5));
    }

    @Test
    void testTranscribe() {
        assertEquals(6, dnaSequenceACGTTC.transcribe().getSequence().size());
        assertEquals(RnaNucleotide.A, dnaSequenceACGTTC.transcribe().getSequence().get(0));
        assertEquals(RnaNucleotide.C, dnaSequenceACGTTC.transcribe().getSequence().get(1));
        assertEquals(RnaNucleotide.G, dnaSequenceACGTTC.transcribe().getSequence().get(2));
        assertEquals(RnaNucleotide.U, dnaSequenceACGTTC.transcribe().getSequence().get(3));
        assertEquals(RnaNucleotide.U, dnaSequenceACGTTC.transcribe().getSequence().get(4));
        assertEquals(RnaNucleotide.C, dnaSequenceACGTTC.transcribe().getSequence().get(5));
    }

    @Test
    void testToString() {
        assertEquals("", emptyDnaSequence.toString());
        assertEquals("ACGTTC", dnaSequenceACGTTC.toString());
    }
}
