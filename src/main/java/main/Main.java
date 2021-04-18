package main;

import jdk.swing.interop.SwingInterOpUtils;
import playlist.Library;
import playlist.Playlist;
import utils.FileManagerLibrary;
import utils.FileManagerPlaylist;
import utils.ReadWriteFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private static FileManagerLibrary fileManagerLibrary = new FileManagerLibrary();
    private static FileManagerPlaylist fileManagerPlaylist = new FileManagerPlaylist();

    public static void main(String[] args) {

        mainOption();


    }

    public static void mainOption() {
        displayOptionMainMenu();
        boolean run = true;
        while (run) {
            System.out.println("Please choose which option you want to select: (1: display options)");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    displayOptionMainMenu();
                    break;
                case 2:
                    Playlist playlist = selectPlaylist();
                    playMusic(playlist);
                    break;
                case 3:
                    CreatePlaylist();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Turning of:");
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }


    private static void displayOptionMainMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("1: DisplayOptions\n");
        builder.append("2: Select Playlist\n");
        builder.append("3: Create New Playlist\n");
        builder.append("4: Delete Playlist\n");
        builder.append("5: End Program\n");
        System.out.println(builder.toString());
    }

    private static void displayOptionPlaylistMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("1: DisplayOptions\n");
        builder.append("2: Add Song to Playlist\n");
        builder.append("3: Remove Song From Playlist\n");
        builder.append("4: Delete Song\n");
        builder.append("5: Next Song\n");
        builder.append("6: Previous Song\n");
        builder.append("7: Delete Current Song\n");
        builder.append("8: Select different playlist\n");
        System.out.println(builder.toString());
    }

    public static void playMusic(Playlist playlist) {
        displayOptionPlaylistMenu();
        boolean run = true;
        while (run) {
            System.out.println("Playlist: " + playlist.getName() + ".\nSelect option: (1: display available options)");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Quiting current playlist:");
                    run = false;
                    break;
                default:
                    break;
            }


        }


    }

    public static void CreatePlaylist() {
        String[] n = ReadWriteFiles.getAllFileNames("libraries");
        List<String> names = Arrays.stream(n).map(e -> e.replace(".json", "")).collect(Collectors.toList());
        Library library = selectLibrary();
        scan.nextLine();
        System.out.println("Please enter playlist Name");
        String playListName = "";
        boolean contains = false;
        do {
            playListName = scan.nextLine();
            contains = names.contains(playListName);
            if (contains) {
                System.out.println("Playlist with this name already exist");
            }
        } while (contains);

        Playlist playlist = new Playlist(playListName, library);
        fileManagerPlaylist.saveData(playlist);

    }


    private static String selectObjectName(String parentFolder) {
        System.out.println("Please select library using numbers");
        String[] names = ReadWriteFiles.getAllFileNames(parentFolder);
        int index = 1;
        for (String name : names) {
            System.out.println(index + ": " + name);
        }
        int select = -1;
        do {
            if (scan.hasNextInt()) {
                select = scan.nextInt();
                if (select < 1 || select >= names.length + 1) {
                    System.out.println("Wrong Number");
                }
            } else {
                System.out.println("Given value is not a number");
                scan.next();
            }

        } while (select < 1 || select >= names.length + 1);

        return names[index - 1].replace(".json", "");
    }

    private static Library selectLibrary() {
        String name = selectObjectName("libraries");
        return fileManagerLibrary.getLibrary(name);
    }

    private static Playlist selectPlaylist() {
        String name = selectObjectName("playlists");
        return fileManagerPlaylist.getPlaylist(name);
    }


}
