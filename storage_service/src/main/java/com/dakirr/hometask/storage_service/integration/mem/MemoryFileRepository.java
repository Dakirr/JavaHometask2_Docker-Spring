package com.dakirr.hometask.storage_service.integration.mem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Repository;

import com.dakirr.hometask.storage_service.domain.repository.FileRepository;
import com.dakirr.hometask.storage_service.domain.value_object.File;

@Repository
public class MemoryFileRepository implements FileRepository {
    private final Path userSavesPath;

    public MemoryFileRepository() throws RuntimeException {
        String userHome = System.getProperty("user.home");
        this.userSavesPath = Paths.get(userHome, "saves");

        try {
            if (!Files.exists(this.userSavesPath)) {
                Files.createDirectories(this.userSavesPath);
            }
        } catch (IOException e) {
            System.err.println("Could not create directory at " + this.userSavesPath.toAbsolutePath() + ": " + e.getMessage());
            throw new RuntimeException("Failed to initialize save directory", e);
        }
    }

    public File get(String filename) throws RuntimeException {
        Path filePath = this.userSavesPath.resolve(filename);
        try {
            return new File(filename, Files.readAllBytes(filePath));
        } catch (IOException e) {
            System.err.println("Could not get file " + filename + ": " + e.getMessage());
            throw new RuntimeException("Failed to save file", e);
        }
        
    }

    public String write(File file) throws RuntimeException {
        Path filePath = this.userSavesPath.resolve(file.getName());
        try {
            Files.write(filePath, file.getContent());
            return filePath.toString();
        } catch (IOException e) {
            System.err.println("Could not save file " + file.getName() + ": " + e.getMessage());
            throw new RuntimeException("Failed to save file", e);
        }
    }

}
