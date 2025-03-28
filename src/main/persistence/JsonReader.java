package persistence;

import model.DnaNucleotide;
import model.DnaSequence;
import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.DnaCalculator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Note: code adapted from JsonSerializationDemo
// Represents a reader that reads the DNA sequence from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads DNA sequence from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DnaSequence read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded sequence from " + DnaCalculator.JSON_STORE));
        return parseDnaSequence(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses sequence from JSON object and returns it
    private DnaSequence parseDnaSequence(JSONObject jsonObject) {
        DnaSequence dnaSequence = new DnaSequence();
        addSequence(dnaSequence, jsonObject);
        return dnaSequence;
    }

    // MODIFIES: dnaSequence
    // EFFECTS: parses sequence from JSON object and adds it to dnaSequence
    private void addSequence(DnaSequence dnaSequence, JSONObject jsonObject) {
        Object json = jsonObject.get("sequence");
        JSONArray jsonArraySequence = (JSONArray) json;
        for (Object object : jsonArraySequence) {
            String stringNucleotide = (String) object;
            dnaSequence.addNucleotideToSequence(DnaNucleotide.valueOf(stringNucleotide));
        }
    }
}

