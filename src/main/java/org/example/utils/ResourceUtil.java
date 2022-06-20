package org.example.utils;

import java.io.File;

public class ResourceUtil {
    public static String getResourcePath(String resourceName) {
        File f = new File(resourceName);
        return f.getAbsolutePath() + "/";
    }
}