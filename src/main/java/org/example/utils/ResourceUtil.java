package org.example.utils;

import java.io.File;

public class ResourceUtil {
    // Get the absolute path of the resource by name
    public static String getResourcePath(String resourceName) {
        File f = new File(resourceName);
        System.out.println(f.getAbsolutePath());
        return f.getAbsolutePath() + "/";
    }
}