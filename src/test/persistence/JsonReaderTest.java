package persistence;

import model.DnaNucleotide;
import model.DnaSequence;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Note: code adapted from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DnaSequence dnaSequence = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySequence() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySequence.json");
        try {
            DnaSequence dnaSequence = reader.read();
            assertTrue(dnaSequence.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTestSequence() {
        JsonReader reader = new JsonReader("./data/testReaderTestSequence.json");
        try {
            DnaSequence dnaSequence = reader.read();
            assertEquals(6, dnaSequence.getSequence().size());
            assertEquals(DnaNucleotide.A, dnaSequence.getSequence().get(0));
            assertEquals(DnaNucleotide.C, dnaSequence.getSequence().get(1));
            assertEquals(DnaNucleotide.G, dnaSequence.getSequence().get(2));
            assertEquals(DnaNucleotide.T, dnaSequence.getSequence().get(3));
            assertEquals(DnaNucleotide.T, dnaSequence.getSequence().get(4));
            assertEquals(DnaNucleotide.C, dnaSequence.getSequence().get(5));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
