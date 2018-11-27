package pl.mgluchowski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputData {

    public static void calculate() throws IOException {

        List<String> coorProjStringList = readFile(Settings.getProjectFilePath());
        List<Coordinate> coorProjList = stringsToCoords(coorProjStringList);

        List<String> coorMeasStringList = readFile(Settings.getMeasureFilePath());
        List<Coordinate> coorMeasList = stringsToCoords(coorMeasStringList);

        Calculate.calculate(coorProjList,coorMeasList);
        
//        coorProjList.forEach(point -> System.out.println(point));
//        coorMeasList.forEach(point -> System.out.println(point));
    }

    private static List<String> readFile(File path) throws FileNotFoundException, IOException {
        List<String> coordinates = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(" ") || line.contains("\t")) {
                coordinates.add(cleanText(line));
            }
        }
        return coordinates;
    }

    private static String cleanText(String text) {
        text = text.replaceAll("\t", " ");
        while (text.contains("  ")) {
            text = text.replaceAll("  ", " ");
        }
        if (text.substring(0, 1).contains(" ")) {
            text = text.substring(1);
        }
        int lenght = text.length();
        if (text.substring(lenght - 1).contains(" ")) {
            text = text.substring(0, lenght - 1);
        }
        return text;
    }

    private static String replaceComma(String text) {
        return text.replace(",", ".");
    }

    private static List<Coordinate> stringsToCoords(List<String> coordsStrings) {
        List<Coordinate> resultList = new ArrayList<>();
        for (int i = 0; i < coordsStrings.size(); i++) {
            resultList.add(stringToCoord(coordsStrings.get(i)));
        }
        return resultList;
    }

    private static Coordinate stringToCoord(String coordString) {
        String[] split = cleanText(coordString).split(" ");

        String n = split[0];
        String x = replaceComma(split[1]);
        String y = replaceComma(split[2]);
        String h = replaceComma(split[3]);
        return new Coordinate(n, x, y, h);

    }

}
