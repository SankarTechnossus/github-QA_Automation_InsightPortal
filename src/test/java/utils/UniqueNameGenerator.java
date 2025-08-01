package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UniqueNameGenerator {

    private static final String BASE_NAME = "TestSan";
    private static final int PAD_LENGTH = 4;
    private static final String FILE_PATH = "Test_Data/uniqueNameIndex.txt";

    public static String generateNextName() {
        int currentNumber = readCurrentNumber();
        int nextNumber = currentNumber + 1;
        saveCurrentNumber(nextNumber);
        return BASE_NAME + String.format("%0" + PAD_LENGTH + "d", nextNumber);
    }

    private static int readCurrentNumber() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                Files.writeString(file.toPath(), "18");
                return 18;
            }
            return Integer.parseInt(Files.readString(file.toPath()).trim());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void saveCurrentNumber(int number) {
        try {
            Files.writeString(Paths.get(FILE_PATH), String.valueOf(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
