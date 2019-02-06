package com.jjhome.doclet.antlr4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UTFileUtils {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private UTFileUtils() {
    }

    public static String slurp(String classpathFileName) throws IOException {
        String absPath = UTFileUtils.class.getResource(classpathFileName).getPath();
        if (isWindows()) {
            // remove leading slash on Windows
            absPath = absPath.substring(1);
        }
        return new String(Files.readAllBytes(Paths.get(absPath)));
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }
}
