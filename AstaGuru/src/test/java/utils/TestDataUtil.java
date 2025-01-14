/*
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDataUtil {
    private static Map<String, Object> testData;

    static {
        // Load test data once during the class initialization
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testData = objectMapper.readValue(new File("src/test/resources/TestData/testData1.json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static Map<String, Object> getTestData(String key) {
        return (Map<String, Object>) testData.get(key);
    }
    public static List<String> getLotNames() {
        return (List<String>) ((Map<String, Object>) testData.get("lots")).get("lotNames");
    }
}
*/

package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestDataUtil {
    private static Map<String, Object> testData;

    // In-memory data store for runtime data (StepDataStore functionality)
    private static final ConcurrentHashMap<String, String> stepDataStore = new ConcurrentHashMap<>();

    static {
        // Load test data once during the class initialization
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            testData = objectMapper.readValue(new File("src/test/resources/TestData/testData1.json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    // Method to retrieve test data by key
    public static Map<String, Object> getTestData(String key) {
        return (Map<String, Object>) testData.get(key);
    }

    // Method to retrieve lot names
    public static List<String> getLotNames() {
        return (List<String>) ((Map<String, Object>) testData.get("lots")).get("lotNames");
    }

    // StepDataStore methods
    public static void put(String key, String value) {
        stepDataStore.put(key, value);
    }

    public static String get(String key) {
        return stepDataStore.get(key);
    }

    public static void clear() {
        stepDataStore.clear();
    }
}