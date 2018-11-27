package pl.mgluchowski;

import java.io.File;

public class Settings {
    private static File projectFilePath;
    private static File measureFilePath;

    public static File getProjectFilePath() {
        return projectFilePath;
    }

    public static void setProjectFilePath(File projectFilePath) {
        Settings.projectFilePath = projectFilePath;
    }

    public static File getMeasureFilePath() {
        return measureFilePath;
    }

    public static void setMeasureFilePath(File measureFilePath) {
        Settings.measureFilePath = measureFilePath;
    }
    
}