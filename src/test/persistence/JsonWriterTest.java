package persistence;

import model.DnaNucleotide;
import model.DnaSequence;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Note: code adapted from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptySequence() {
        try {
            DnaSequence dnaSequence = new DnaSequence();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySequence.json");
            writer.open();
            writer.write(dnaSequence);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySequence.json");
            dnaSequence = reader.read();
            assertTrue(dnaSequence.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTestSequence() {
        try {
            DnaSequence writtenDnaSequence = new DnaSequence();
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.T);
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.G);
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.C);
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.A);
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.A);
            writtenDnaSequence.addNucleotideToSequence(DnaNucleotide.C);

            JsonWriter writer = new JsonWriter("./data/testWriterTestSequence.json");
            writer.open();
            writer.write(writtenDnaSequence);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTestSequence.json");
            DnaSequence readDnaSequence = reader.read();

            assertEquals(6, readDnaSequence.getSequence().size());
            assertEquals(DnaNucleotide.T, readDnaSequence.getSequence().get(0));
            assertEquals(DnaNucleotide.G, readDnaSequence.getSequence().get(1));
            assertEquals(DnaNucleotide.C, readDnaSequence.getSequence().get(2));
            assertEquals(DnaNucleotide.A, readDnaSequence.getSequence().get(3));
            assertEquals(DnaNucleotide.A, readDnaSequence.getSequence().get(4));
            assertEquals(DnaNucleotide.C, readDnaSequence.getSequence().get(5));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
