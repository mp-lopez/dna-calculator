package model;

import java.util.ArrayList;
import java.util.List;

// Represents an RNA sequence as a list of RnaNucleotide
public class RnaSequence {
    private List<RnaNucleotide> sequence;

    public RnaSequence() {
        sequence = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given RNA nucleotide to RNA sequence
    public void addToSequence(RnaNucleotide rnaNucleotide) {
        sequence.add(rnaNucleotide);
    }

    // EFFECTS: returns string corresponding to RNA sequence
    @Override
    public String toString() {
        String stringSequence = new String();
        for (RnaNucleotide rnaNucleotide : sequence) {
            stringSequence = stringSequence.concat(rnaNucleotide.toString());
        }
        return stringSequence;
    }

    // EFFECTS: returns true of RNA sequence is empty, false otherwise
    public boolean isEmpty() {
        return sequence.isEmpty();
    }

    public List<RnaNucleotide> getSequence() {
        return sequence;
    }
}
