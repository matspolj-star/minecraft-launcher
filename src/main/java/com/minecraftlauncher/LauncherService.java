package com.minecraftlauncher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LauncherService {
    private final LauncherConfig config = new LauncherConfig();
    private final JavaDetector javaDetector = new JavaDetector();

    public List<String> getAvailableVersions() {
        List<String> versions = new ArrayList<>();
        Path versionsDir = config.getMinecraftDirectory().resolve("versions");

        if (Files.exists(versionsDir)) {
            try {
                Files.list(versionsDir)
                    .filter(Files::isDirectory)
                    .map(path -> path.getFileName().toString())
                    .sorted(Comparator.reverseOrder())
                    .forEach(versions::add);
            } catch (IOException ignored) {
            }
        }

        if (versions.isEmpty()) {
            versions.add("1.20.4");
            versions.add("1.20.1");
            versions.add("1.19.2");
            versions.add("1.18.2");
            versions.add("1.16.5");
        }

        return versions;
    }

    public String detectJavaPath() {
        return javaDetector.detectJavaExecutable();
    }

    public String detectJavaVersion() {
        List<String> lines = javaDetector.detectJavaVersion();
        return lines.isEmpty() ? "Java not found" : lines.get(0);
    }

    public Process launchMinecraft(String version, int memoryMb, String username) throws IOException {
        Path minecraftDir = config.getMinecraftDirectory();
        Path versionDir = minecraftDir.resolve("versions").resolve(version);
        Path jarFile = versionDir.resolve(version + ".jar");

        if (!Files.exists(jarFile)) {
            throw new IOException("Minecraft version '" + version + "' is not installed. Add the official .jar under ~/.minecraft/versions/" + version + "/" + version + ".jar");
        }

        String javaPath = detectJavaPath();
        List<String> command = new ArrayList<>();
        command.add(javaPath);
        command.add("-Xmx" + memoryMb + "m");
        command.add("-Xms512m");
        command.add("-cp");
        command.add(jarFile.toString());
        command.add("net.minecraft.client.main.Main");
        command.add("--version");
        command.add(version);
        command.add("--gameDir");
        command.add(minecraftDir.toString());
        command.add("--assetsDir");
        command.add(minecraftDir.resolve("assets").toString());
        command.add("--username");
        command.add(username);

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(minecraftDir.toFile());
        builder.inheritIO();
        return builder.start();
    }
}
