package com.converter.text.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    public List<String> getFolderFilePaths(String folderPaths) {
        return Arrays.stream(Objects.requireNonNull(Paths.get(folderPaths).toFile().listFiles()))
                .map(File::getAbsolutePath)
                .toList();
    }

    public void moveFile(String from, String to) throws IOException {
        Path pathFrom = Paths.get(from);
        Path pathTo = Paths.get(to.concat("\\").concat(pathFrom.toFile().getName()));
        Files.move(pathFrom, pathTo, StandardCopyOption.REPLACE_EXISTING);
    }
}
