package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DnaNucleotideTest {
    private DnaNucleotide a = DnaNucleotide.A;
    private DnaNucleotide c = DnaNucleotide.C;
    private DnaNucleotide g = DnaNucleotide.G;
    private DnaNucleotide t = DnaNucleotide.T;

    @Test
    void testComplement() {
        assertEquals(DnaNucleotide.T, a.complement());
        assertEquals(DnaNucleotide.G, c.complement());
        assertEquals(DnaNucleotide.C, g.complement());
        assertEquals(DnaNucleotide.A, t.complement());
    }

    @Test
    void testTranscribe() {
        assertEquals(RnaNucleotide.A, a.transcribe());
        assertEquals(RnaNucleotide.C, c.transcribe());
        assertEquals(RnaNucleotide.G, g.transcribe());
        assertEquals(RnaNucleotide.U, t.transcribe());
    }
}