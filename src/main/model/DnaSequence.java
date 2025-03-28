package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Note: code in toJson() and sequenceToJson() adapted from JsonSerializationDemo
// Represents a DNA sequence as a list of DnaNucleotide
public class DnaSequence implements Writable {
    private List<DnaNucleotide> sequence;

    // EFFECTS: constructs an empty DNA sequence
    public DnaSequence() {
        sequence = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given DNA nucleotide to DNA sequence
    public void addNucleotideToSequence(DnaNucleotide dnaNucleotide) {
        sequence.add(dnaNucleotide);
    }

    // MODIFIES: this
    // EFFECTS: appends given DNA sequence to end of DNA sequence
    public void addSequenceToSequence(DnaSequence dnaSequence) {
        for (DnaNucleotide dnaNucleotide : dnaSequence.getSequence()) {
            this.addNucleotideToSequence(dnaNucleotide);
        }
        EventLog.getInstance().logEvent(new Event("Added " + dnaSequence + " to sequence"));
    }

    // EFFECTS: returns complementary DNA sequence
    public DnaSequence complement() {
        DnaSequence complementarySequence = new DnaSequence();
        for (DnaNucleotide dnaNucleotide : sequence) {
            complementarySequence.addNucleotideToSequence(dnaNucleotide.complement());
        }
        EventLog.getInstance().logEvent(new Event("Complemented sequence"));
        return complementarySequence;
    }

    // EFFECTS: returns corresponding RNA sequence
    public RnaSequence transcribe() {
        RnaSequence transcribedSequence = new RnaSequence();
        for (DnaNucleotide dnaNucleotide : sequence) {
            transcribedSequence.addToSequence(dnaNucleotide.transcribe());
        }
        EventLog.getInstance().logEvent(new Event("Transcribed sequence"));
        return transcribedSequence;
    }

    // EFFECTS: returns string corresponding to DNA sequence
    @Override
    public String toString() {
        String stringSequence = new String();
        for (DnaNucleotide dnaNucleotide : sequence) {
            stringSequence = stringSequence.concat(dnaNucleotide.toString());
        }
        return stringSequence;
    }

    // EFFECTS: returns true if DNA sequence is empty, false otherwise
    public boolean isEmpty() {
        return sequence.isEmpty();
    }

    public List<DnaNucleotide> getSequence() {
        return sequence;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sequence", sequence);
        return json;
    }
}
