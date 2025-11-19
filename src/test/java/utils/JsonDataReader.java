package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataReader {

    private static final JSONObject testData;

    static {
        try {
            // Get Base Dir
            String baseDir = System.getProperty("user.dir");

            /* Equivalent path: <project-root>/Test_Data/TestData.json */
            String path = Paths.get(baseDir, "Test_Data", "TestData.json").toString();

            var path1 = Paths.get(path);
            if (!Files.exists(path1)) {
                throw new RuntimeException("JSON data file not found at: " + path);
            }

            String content = new String(Files.readAllBytes(path1));
            testData = new JSONObject(content);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON data: " + e.getMessage(), e);
        }
    }

    public static String get(String key) {
        if (testData == null) {
            throw new IllegalStateException("JSON data not loaded.");
        }

        // Access first element in "data" array
        JSONArray dataArray = testData.optJSONArray("data");
        if (dataArray == null || dataArray.isEmpty()) {
            throw new IllegalStateException("No data entries found in JSON file.");
        }

        JSONObject firstObject = dataArray.optJSONObject(0);
        if (firstObject == null) {
            throw new IllegalStateException("Invalid JSON object structure.");
        }

        if (!firstObject.has(key)) {
            throw new RuntimeException("Key '" + key + "' not found in JSON.");
        }

        return firstObject.get(key).toString();
    }
}
