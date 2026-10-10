package com.minecraftlauncher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class AuthService {
    private String signedInUsername = "Guest";

    public boolean login(String username, String password) {
        if (username == null || username.isBlank()) {
            return false;
        }

        signedInUsername = username.trim();
        return true;
    }

    public String getSignedInUsername() {
        return signedInUsername;
    }

    public boolean openMicrosoftLogin() {
        try {
            String url = "https://login.live.com/oauth20_authorize.srf?client_id=00000000402B7A0B&response_type=code&scope=openid%20offline_access%20XboxLive.signin%20XboxLive.offline_access&redirect_uri=https://login.live.com/oauth20_desktop.srf&state=launcher";
            Desktop.getDesktop().browse(new URI(url));
            return true;
        } catch (IOException | URISyntaxException e) {
            return false;
        }
    }

    public String getMicrosoftLoginUrl() {
        return "https://login.live.com/oauth20_authorize.srf?client_id=00000000402B7A0B&response_type=code&scope=openid%20offline_access%20XboxLive.signin%20XboxLive.offline_access&redirect_uri=https://login.live.com/oauth20_desktop.srf&state=launcher";
    }
}
