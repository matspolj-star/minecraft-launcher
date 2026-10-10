package com.minecraftlauncher;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LauncherConfig {
    private final Path minecraftDirectory;
    private final Path javaHome;
    private final int defaultMemoryMb;

    public LauncherConfig() {
        this.minecraftDirectory = Paths.get(System.getProperty("user.home"), ".minecraft");
        this.javaHome = Paths.get(System.getProperty("java.home"));
        this.defaultMemoryMb = 6144;
    }

    public Path getMinecraftDirectory() {
        return minecraftDirectory;
    }

    public Path getJavaHome() {
        return javaHome;
    }

    public int getDefaultMemoryMb() {
        return defaultMemoryMb;
    }
}
