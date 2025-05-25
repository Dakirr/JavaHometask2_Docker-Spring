package com.dakirr.hometask.analisys_service.integration.mem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Repository;

import com.dakirr.hometask.analisys_service.domain.repository.FileRepository;
import com.dakirr.hometask.analisys_service.domain.value_object.TextFile;

@Repository
public class MemoryFileRepository implements FileRepository {
    private final Path userSavesPath;

    public MemoryFileRepository() throws RuntimeException {
        String userHome = System.getProperty("user.home");
        this.userSavesPath = Paths.get(userHome, "analisys");

        try {
            if (!Files.exists(this.userSavesPath)) {
                Files.createDirectories(this.userSavesPath);
            }
        } catch (IOException e) {
            System.err.println("Could not create directory at " + this.userSavesPath.toAbsolutePath() + ": " + e.getMessage());
            throw new RuntimeException("Failed to initialize save directory", e);
        }
    }

    public TextFile get(String filename) throws RuntimeException {
        Path filePath = this.userSavesPath.resolve(filename);
        try {
            return new TextFile(filename, Files.readAllBytes(filePath));
        } catch (IOException e) {
            System.err.println("Could not get file " + filename + ": " + e.getMessage());
            throw new RuntimeException("Failed to save file", e);
        }
        
    }

    public String write(TextFile file) throws RuntimeException {
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
