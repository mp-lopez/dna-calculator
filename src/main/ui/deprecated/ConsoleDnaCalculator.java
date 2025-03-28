package ui.deprecated;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Console-based DNA calculator
// Note: code adapted from AccountNotRobust & JsonSerializationDemo
public class ConsoleDnaCalculator {
    private static final String JSON_STORE = "./data/sequence.json";
    private Scanner input;
    private DnaSequence sequence;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the DNA calculator
    public ConsoleDnaCalculator() {
        input = new Scanner(System.in);
        sequence = new DnaSequence();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDnaCalculator();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDnaCalculator() {
        displayInitialMenu();
        initializeSequence();

        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
//                for (Event event : EventLog.getInstance()) {
//                    System.out.println(event.toString());
//                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays initial menu of options to user (prompting to enter new DNA sequence or load from file)
    private void displayInitialMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\te -> enter new DNA sequence");
        System.out.println("\tl -> load DNA sequence from file");
    }

    // MODIFIES: this
    // EFFECTS: sets sequence to entered sequence or loads sequence from file
    private void initializeSequence() {
        String command = input.next();
        if (command.equals("e")) {
            setNewSequence();
        } else if (command.equals("l")) {
            loadSequence();
        } else {
            System.out.println("ERROR: selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets sequence to entered sequence; ignores invalid characters
    //                                             (characters other than A/a, C/c, G/g, or T/t)
    private void setNewSequence() {
        System.out.println("Enter DNA sequence: ");
        sequence = toDnaSequence(input.next());
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view sequence");
        System.out.println("\ta -> add to sequence");
        System.out.println("\tc -> complement");
        System.out.println("\tt -> transcribe");
        System.out.println("\ts -> save sequence to file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            viewSequence();
        } else if (command.equals("a")) {
            addToSequence();
        } else if (command.equals("c")) {
            doComplement();
        } else if (command.equals("t")) {
            doTranscribe();
        } else if (command.equals("s")) {
            saveSequence();
        } else {
            System.out.println("ERROR: selection not valid");
        }
    }

    // EFFECTS: prints current sequence
    private void viewSequence() {
        System.out.println("Current sequence: " + sequence.toString());
    }

    // MODIFIES: this
    // EFFECTS: adds inputted sequence to end of current sequence; ignores invalid characters
    //                                                             (characters other than A/a, C/c, G/g, or T/t)
    private void addToSequence() {
        System.out.println("Enter DNA sequence to add: ");
        String inputtedSequence = input.next();
        DnaSequence sequenceToAdd = toDnaSequence(inputtedSequence);
        sequence.addSequenceToSequence(sequenceToAdd);

        System.out.println("Updated DNA sequence: " + sequence.toString());
    }

    // EFFECTS: prints complementary DNA sequence
    private void doComplement() {
        DnaSequence complementarySequence = sequence.complement();
        System.out.println("Complementary sequence: " + complementarySequence.toString());
    }

    // EFFECTS: prints corresponding RNA sequence
    private void doTranscribe() {
        RnaSequence transcribedSequence = sequence.transcribe();
        System.out.println("Transcribed sequence: " + transcribedSequence.toString());
    }

    // EFFECTS: saves current DNA sequence to file
    private void saveSequence() {
        try {
            jsonWriter.open();
            jsonWriter.write(sequence);
            jsonWriter.close();
            System.out.println("Saved sequence to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads DNA sequence from file
    private void loadSequence() {
        try {
            sequence = jsonReader.read();
            System.out.println("Loaded sequence from " + JSON_STORE + ": " + sequence.toString());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns a DNA sequence corresponding to inputted string; ignores invalid characters
    //          (characters other than A/a, C/c, G/g, or T/t)
    private static DnaSequence toDnaSequence(String stringSequence) {
        stringSequence = stringSequence.toUpperCase();
        char[] charArraySequence = stringSequence.toCharArray();
        DnaSequence dnaSequence = new DnaSequence();
        for (Character character : charArraySequence) {
            if (character.equals('A')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.A);
            }
            if (character.equals('C')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.C);
            }
            if (character.equals('G')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.G);
            }
            if (character.equals('T')) {
                dnaSequence.addNucleotideToSequence(DnaNucleotide.T);
            }
        }

        return dnaSequence;
    }
}
