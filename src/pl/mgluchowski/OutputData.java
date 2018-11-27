package pl.mgluchowski;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputData {

    public static void exportTxt(List<Result> resultList) throws FileNotFoundException, IOException {

        String newFile = initOutpuFile();

        writeData(resultList, newFile);

        System.out.println("Utworzono plik txt: " + newFile);
    }

    private static String initOutpuFile() {
        String file = Settings.getMeasureFilePath().toString();
        StringBuilder newFile = new StringBuilder();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        fileArray[lengthArr - 1] = "new" + fileArray[lengthArr - 1];
        for (int i = 0; i < lengthArr; i++) {
            newFile = newFile.append(fileArray[i]);
            if (i != (lengthArr - 1)) {
                newFile = newFile.append('\\');
            }
        }
        return newFile.toString();
    }

    private static void writeData(List<Result> what, String where) throws IOException {
        int lenghtCL = what.size();
        try (
                FileWriter fw = new FileWriter(where);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < lenghtCL; i++) {
                pw.println(what.get(i).toString());
            }
        }
    }

}
