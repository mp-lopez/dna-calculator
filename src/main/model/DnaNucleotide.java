package model;

// Note: code in toJson() adapted from JsonSerializationDemo
// Represents a DNA nucleotide: one of adenine (A), cytosine (C), guanine (G), or thymine (T)
public enum DnaNucleotide {
    A, C, G, T;

    // EFFECTS: returns complement of DNA nucleotide (A to T, C to G, G to C, T to A)
    public DnaNucleotide complement() {
        if (this == A) {
            return T;
        } else if (this == C) {
            return G;
        } else if (this == G) {
            return C;
        } else {
            return A;
        }
    }

    // EFFECTS: returns corresponding RNA nucleotide (A to A, C to C, G to G, T to U)
    public RnaNucleotide transcribe() {
        if (this == A) {
            return RnaNucleotide.A;
        } else if (this == C) {
            return RnaNucleotide.C;
        } else if (this == G) {
            return RnaNucleotide.G;
        } else {
            return RnaNucleotide.U;
        }
    }
}


