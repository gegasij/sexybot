package com.converter.text.service;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    public static void writeToFile(String path, String data) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new java.io.FileWriter(path, true));
            writer.append(data);
            writer.append('\n');
            writer.close();
        } catch (Exception e) {
            System.exit(100);
        }
    }
}
