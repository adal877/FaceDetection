package org.example.utils;

import java.io.File;

public class ResourceUtil {
    // Get the absolut path of the resource by name
    public static String getResourcePath(String resourceName) {
        File f = new File(resourceName);
        return f.getAbsolutePath() + "/";
    }
}