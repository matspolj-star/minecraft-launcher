package com.minecraftlauncher;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MainController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label accountNameLabel;
    @FXML private Label loginStatusLabel;
    @FXML private Button loginButton;
    @FXML private ComboBox<String> versionComboBox;
    @FXML private Slider memorySlider;
    @FXML private Label memoryLabel;
    @FXML private Label javaLabel;
    @FXML private Label statusLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Button playButton;

    private final AuthService authService = new AuthService();
    private final LauncherService launcherService = new LauncherService();

    @FXML
    private void initialize() {
        versionComboBox.getItems().setAll(launcherService.getAvailableVersions());
        versionComboBox.setValue(versionComboBox.getItems().get(0));

        memorySlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            int gb = newValue.intValue();
            memoryLabel.setText(gb + " GB");
        });

        javaLabel.setText(launcherService.detectJavaVersion());
        memoryLabel.setText((int) memorySlider.getValue() + " GB");
        accountNameLabel.setText("Guest");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        new Thread(() -> {
            boolean success = authService.login(username, password);
            Platform.runLater(() -> {
                if (success) {
                    accountNameLabel.setText(authService.getSignedInUsername());
                    loginStatusLabel.setText("Signed in successfully");
                    statusLabel.setText("Account ready");
                } else {
                    loginStatusLabel.setText("Username is required");
                    statusLabel.setText("Login failed");
                }
            });
        }).start();
    }

    @FXML
    private void handlePlay() {
        String version = versionComboBox.getValue();
        int memory = (int) memorySlider.getValue();
        String username = usernameField.getText() == null || usernameField.getText().isBlank()
                ? "Player"
                : usernameField.getText();

        playButton.setDisable(true);
        progressBar.setProgress(0.15);
        statusLabel.setText("Preparing Minecraft launch...");

        new Thread(() -> {
            try {
                Thread.sleep(600);
                Process process = launcherService.launchMinecraft(version, memory * 1024, username);
                Platform.runLater(() -> {
                    statusLabel.setText("Minecraft launched successfully");
                    progressBar.setProgress(1.0);
                    playButton.setDisable(false);
                });

                process.onExit().thenRun(() -> {
                    Platform.runLater(() -> {
                        statusLabel.setText("Minecraft exited");
                        playButton.setDisable(false);
                    });
                });
            } catch (Exception ex) {
                Platform.runLater(() -> {
                    statusLabel.setText(ex.getMessage());
                    progressBar.setProgress(0.0);
                    playButton.setDisable(false);
                });
            }
        }).start();
    }

    @FXML
    private void handleSettings() {
        statusLabel.setText("Settings panel is not implemented yet");
        progressBar.setProgress(0.5);
    }
}
