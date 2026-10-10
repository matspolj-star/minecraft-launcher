package com.minecraftlauncher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JavaDetector {
    public String detectJavaExecutable() {
        String javaHome = System.getProperty("java.home");
        if (javaHome != null && !javaHome.isBlank()) {
            Path javaBin = Path.of(javaHome, "bin", "java");
            if (Files.exists(javaBin)) {
                return javaBin.toString();
            }
        }

        try {
            Process process = new ProcessBuilder("which", "java").start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String path = reader.readLine();
                if (path != null && !path.isBlank()) {
                    return path;
                }
            }
        } catch (Exception ignored) {
        }

        return "java";
    }

    public List<String> detectJavaVersion() {
        List<String> output = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(detectJavaExecutable(), "-version").start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }
        } catch (Exception ignored) {
            output.add("Java not found");
        }
        return output;
    }
}
