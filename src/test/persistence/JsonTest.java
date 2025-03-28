package persistence;

import model.DnaNucleotide;
import model.DnaSequence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Note: code adapted from JsonSerializationDemo
public class JsonTest {
    protected void checkSequence(List<DnaNucleotide> sequence, DnaSequence dnaSequence) {
        assertEquals(sequence, dnaSequence.getSequence());
    }
}
