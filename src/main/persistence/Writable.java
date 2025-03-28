package persistence;

import org.json.JSONObject;

// Note: code adapted from JsonSerializationDemo
// Represents something that can be converted to a JSON object
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
