package com.kosi.util;

public class FilesUtil {

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static String getPathByOS(String path) {
        return isWindows() ? "c:" + path : path;
    }
}
