package utils;

import playlist.Album;
import playlist.Library;
import playlist.Song;
import utils.FileManagerLibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SongUploader {
    public static void main(String[] args) {
        ArrayList<Song> ram = getSongs("src/main/resources/album.txt");
        ArrayList<Song> meteora = getSongs("src/main/resources/meteora.txt");
        ArrayList<Song> american = getSongs("src/main/resources/american.txt");

        Album daftPunk = new Album("Random Access Memories", "Daft Punk", "2013");
        daftPunk.addMultipleSongs(ram);

        Album linkinPark = new Album("Meteora", "Linkin Park", "2003");
        linkinPark.addMultipleSongs(meteora);

        Album greenDay = new Album("American Idiot", "Green Day", "2004");
        greenDay.addMultipleSongs(american);


        Library library = new Library("Chill Out");
        library.addAlbum(daftPunk, linkinPark, greenDay);


        FileManagerLibrary libraryManager = new FileManagerLibrary();

        libraryManager.saveData(library);


//        readValues(ram);
//        System.out.println("==========================================");
//        readValues(meteora);
//        System.out.println("==========================================");
//        readValues(american);

    }

    private static void readValues(ArrayList<Song> list) {
        for (Song a : list) {
            System.out.println(a.toString());
        }

    }

    private static ArrayList<Song> getSongs(String filePath) {
        File file = new File(filePath);
        ArrayList<String> list = readUsingScanner(file);
        ArrayList<Song> songs = new ArrayList();
        for (String val : list) {
            String[] aSong = val.split("\\|");
            Song song = Song.createSong(aSong[0], Double.valueOf(aSong[1]));
            songs.add(song);
        }
        return songs;
    }

    private static ArrayList<String> readUsingScanner(File file) {
        Scanner scan = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
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

        return list;
    }
}
