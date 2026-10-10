package com.minecraftlauncher;

import java.util.List;

public class MinecraftVersionManifest {
    public Latest latest;
    public List<Version> versions;

    public static class Latest {
        public String release;
        public String snapshot;
    }

    public static class Version {
        public String id;
        public String type;
        public String url;
        public String time;
        public String releaseTime;
        public String sha1;
        public int complianceLevel;

        public String getReleaseTime() {
            return releaseTime != null ? releaseTime : time;
        }

        public String getId() {
            return id;
        }
    }
}
