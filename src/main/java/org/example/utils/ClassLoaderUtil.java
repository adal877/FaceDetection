package org.example.utils;

import java.io.File;
import java.net.URISyntaxException;

public class ClassLoaderUtil {
    public static String getResource(String resourceName) throws URISyntaxException {
        File f = new File(resourceName);
        return f.getAbsolutePath() + "/";
    }
}