package com.andrei.spring.mvc.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class StorageReader {
    public List<String> getStorageData(String filePath) {
        try {
            List<String> data = Files.readAllLines(Paths.get(filePath));
            System.out.println("storageReader filePath" + data);
            return data;
        } catch (IOException exception) {
            System.out.println(exception);
            return Collections.emptyList();
        }
    }
}
