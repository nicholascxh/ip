package dasani.util.storage;

import dasani.exception.DasaniException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manages storage locations and file setup for Dasani.
 */
public class StorageManager {
    private static final String STORAGE_FILE_NAME = "tasks.txt";
    private final String filePath;

    /**
     * Constructs a StorageManager instance and ensures the storage file exists.
     */
    public StorageManager() {
        Path jarDir = getJarDirectory();
        if (jarDir == null) {
            throw new RuntimeException("[Dasani]: Could not determine JAR directory.");
        }
        this.filePath = jarDir.resolve(STORAGE_FILE_NAME).toString();
        ensureFileExists();
    }

    /**
     * Gets the directory where the JAR file is located.
     *
     * @return Path representing the JAR's directory.
     */
    private Path getJarDirectory() {
        try {
            return Paths.get(new File(StorageManager.class.getProtectionDomain().getCodeSource()
                    .getLocation().toURI()).getParent());
        } catch (URISyntaxException e) {
            System.err.println("[Dasani]: Error retrieving JAR directory.");
            return null;
        }
    }

    /**
     * Ensures the storage file exists.
     */
    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Files.createFile(file.toPath());
                System.out.println("[Dasani]: Created new storage file at " + filePath);
            }
        } catch (IOException e) {
            System.err.println("[Dasani]: Unable to create storage file: " + filePath);
        }
    }

    /**
     * Gets the storage file path.
     *
     * @return The file path.
     */
    public String getFilePath() {
        return filePath;
    }
}
