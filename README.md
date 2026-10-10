# Minecraft Launcher (JavaFX Starter)

This project is a clean JavaFX starter for a Minecraft launcher built with modern UI, smooth animations, and the right architecture for official Minecraft launching.

What is included:
- JavaFX launcher UI
- dark premium Minecraft-inspired theme
- animated cards and loading indicators
- account/login area
- config support
- launcher service for official Minecraft version launch flow
- Java detection and launcher setup structure
- Microsoft OAuth placeholder architecture for future login integration

Important:
This project is a legal and correct launcher foundation. It is designed to launch the official Minecraft client using valid local installation metadata and proper Java execution, not a cracked client.

Project structure:
- `src/main/java/com/minecraftlauncher` – Java classes
- `src/main/resources/app.fxml` – JavaFX layout
- `src/main/resources/styles.css` – theme and animations

Run the app:

```bash
mvn clean javafx:run
```

Requirements:
- Java 17+
- Maven

If you want to run actual Minecraft, make sure your `.minecraft` folder exists and contains a version directory such as:

```text
~/.minecraft/versions/1.20.4/1.20.4.jar
```

You can also update `LauncherService.java` to support:
- Microsoft OAuth login
- version manifest parsing
- Forge / Fabric profiles
- modpack installation
- download manager

This repository is intentionally structured as a reliable starter project that can evolve into a production launcher.
