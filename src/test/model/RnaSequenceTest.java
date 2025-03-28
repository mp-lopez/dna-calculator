package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RnaSequenceTest {
    private RnaSequence emptyRnaSequence;
    private RnaSequence rnaSequenceCGUAAU;

    @BeforeEach
    void runBefore() {
        emptyRnaSequence = new RnaSequence();
        rnaSequenceCGUAAU = new RnaSequence();
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.C);
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.G);
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.U);
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.A);
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.A);
        rnaSequenceCGUAAU.addToSequence(RnaNucleotide.U);
    }

    @Test
    void testConstructor() {
        RnaSequence newRnaSequence = new RnaSequence();
        assertTrue(newRnaSequence.getSequence().isEmpty());
    }

    @Test
    void testAddAdenineToSequence() {
        emptyRnaSequence.addToSequence(RnaNucleotide.A);
        assertEquals(1, emptyRnaSequence.getSequence().size());
        assertEquals(RnaNucleotide.A, emptyRnaSequence.getSequence().get(0));
    }

    @Test
    void testAddCytosineToSequence() {
        emptyRnaSequence.addToSequence(RnaNucleotide.C);
        assertEquals(1, emptyRnaSequence.getSequence().size());
        assertEquals(RnaNucleotide.C, emptyRnaSequence.getSequence().get(0));
    }

    @Test
    void testAddGuanineToSequence() {
        emptyRnaSequence.addToSequence(RnaNucleotide.G);
        assertEquals(1, emptyRnaSequence.getSequence().size());
        assertEquals(RnaNucleotide.G, emptyRnaSequence.getSequence().get(0));
    }

    @Test
    void testAddThymineToSequence() {
        emptyRnaSequence.addToSequence(RnaNucleotide.U);
        assertEquals(1, emptyRnaSequence.getSequence().size());
        assertEquals(RnaNucleotide.U, emptyRnaSequence.getSequence().get(0));
    }

    @Test
    void testAddMultipleToSequence() {
        emptyRnaSequence.addToSequence(RnaNucleotide.C);
        emptyRnaSequence.addToSequence(RnaNucleotide.C);
        emptyRnaSequence.addToSequence(RnaNucleotide.A);
        emptyRnaSequence.addToSequence(RnaNucleotide.U);
        emptyRnaSequence.addToSequence(RnaNucleotide.G);
        assertEquals(5, emptyRnaSequence.getSequence().size());
        assertEquals(RnaNucleotide.C, emptyRnaSequence.getSequence().get(0));
        assertEquals(RnaNucleotide.C, emptyRnaSequence.getSequence().get(1));
        assertEquals(RnaNucleotide.A, emptyRnaSequence.getSequence().get(2));
        assertEquals(RnaNucleotide.U, emptyRnaSequence.getSequence().get(3));
        assertEquals(RnaNucleotide.G, emptyRnaSequence.getSequence().get(4));
    }

    @Test
    void testToString() {
        assertEquals("", emptyRnaSequence.toString());
        assertEquals("CGUAAU", rnaSequenceCGUAAU.toString());
    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyRnaSequence.isEmpty());
        assertFalse(rnaSequenceCGUAAU.isEmpty());
    }
}
