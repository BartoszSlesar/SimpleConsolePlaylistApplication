package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ReadWriteFiles {

    public static void writeUsingFileWriter(String data, String fileName, String parentFolder) {
        fileName = fileName.strip().replaceAll(" ", "_");
        File directory = new File("src/main/resources/" + parentFolder);
        directory.mkdirs();
        FileWriter fr = null;
        try {
            fr = new FileWriter(directory.getPath() + "/" + fileName+".json");
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readUsingScanner(File file) {
        Scanner scan = null;
        StringBuilder builder = new StringBuilder();
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                builder.append(scan.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                scan.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    public static File[] getAllFilesFromSource(String parentFolder) {
        File folder = new File("src/main/resources/" + parentFolder);
        return folder.listFiles();
    }

    public static String[] getAllFileNames(String parentFolder) {
        File folder = new File("src/main/resources/" + parentFolder);
        return folder.list();

    }
}
