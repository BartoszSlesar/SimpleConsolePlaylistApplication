package main;

import playlist.Library;
import playlist.Playlist;
import playlist.Song;
import utils.FileManagerLibrary;
import utils.FileManagerPlaylist;
import utils.ReadWriteFiles;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private static FileManagerLibrary fileManagerLibrary = new FileManagerLibrary();
    private static FileManagerPlaylist fileManagerPlaylist = new FileManagerPlaylist();

    public static void main(String[] args) {

        runPlayListApplication();


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

    public static void runPlayListApplication() {
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
                    if (playlist != null) {
                        playMusic(playlist);
                    }
                    break;
                case 3:
                    createPlaylist();
                    break;
                case 4:
                    Playlist toBeDeleted = selectPlaylist();
                    if (toBeDeleted != null) {
                        fileManagerPlaylist.deletePlaylist(toBeDeleted.getName());
                        System.out.println("Play list has been deleted");
                    }
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


    private static void displayOptionPlaylistMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("1: DisplayOptions\n");
        builder.append("2: Add Song to Playlist\n");
        builder.append("3: Remove Song From Playlist\n");
        builder.append("4: Show all Song in playlist\n");
        builder.append("5: Next Song\n");
        builder.append("6: Previous Song\n");
        builder.append("7: Delete Current Song\n");
        builder.append("8: Save playlist\n");
        builder.append("9: Select different playlist (end)\n");
        System.out.println(builder.toString());
    }

    public static void playMusic(Playlist playlist) {
        displayOptionPlaylistMenu();
        ListIterator<Song> playMusic = playlist.getSongIterator();
        boolean goingForwoard = true;
        Song currentSong = null;
        if (playMusic.hasNext()) {
            currentSong = playMusic.next();
        }
        boolean run = true;
        while (run) {
            System.out.println("Playlist: " + playlist.getName() + ".\nSelect option: (1: display available options)\n");
            if (currentSong != null) {
                System.out.println("Currently playing: " + currentSong.toString());
            }
            int option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    displayOptionPlaylistMenu();
                    break;
                case 2:
                    addSongToPlaylist(playlist);
                    break;
                case 3:
                    removeSongFromPlaylist(playlist);
                    break;
                case 4:
                    playlist.ListAllSongsInPlaylist();
                    break;
                case 5:
                    if (!goingForwoard) {
                        playMusic.next();
                        goingForwoard = true;
                    }
                    if (playMusic.hasNext()) {
                        currentSong = playMusic.next();
                    } else {
                        System.out.println("You are currently in the end of play list");
                    }
                    break;
                case 6:
                    if (goingForwoard) {
                        playMusic.previous();
                        goingForwoard = false;
                    }
                    if (playMusic.hasPrevious()) {
                        currentSong = playMusic.previous();

                    } else {
                        System.out.println("You are currently in the first song");
                    }
                    break;
                case 7:

                    if (goingForwoard) {
                        playMusic.remove();
                        System.out.println(currentSong.getTitle() + " was removed from playlist");
                        if (playMusic.hasNext()) {
                            currentSong = playMusic.next();
                        } else if (playMusic.hasPrevious()) {
                            currentSong = playMusic.previous();
                            goingForwoard = false;
                        }
                    } else {
                        playMusic.remove();
                        System.out.println(currentSong.getTitle() + " was removed from playlist");
                        if (playMusic.hasPrevious()) {
                            currentSong = playMusic.previous();
                        } else if (playMusic.hasNext()) {
                            currentSong = playMusic.previous();
                            goingForwoard = true;
                        }
                    }


                    break;
                case 8:
                    fileManagerPlaylist.saveData(playlist);
                    break;
                case 9:
                    System.out.println("Quiting current playlist:");
                    run = false;
                    break;
                default:
                    break;
            }


        }


    }

    private static void removeSongFromPlaylist(Playlist playlist) {
        playlist.ListAllSongsInPlaylist();
        System.out.println("Please select number");
        int size = playlist.numberOfSongs();
        if (size > 0) {
            int selected = scan.nextInt();
            if (selected < 1 || selected > size) {
                System.out.println("Wrong number was selected");
            } else {
                playlist.removeSongFromPlayList(selected - 1);
            }
        }
    }

    private static void displayAddSongOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("0: to display options\n");
        sb.append("1: if you want to display all Avalible albums\n");
        sb.append("2: if you want to display all songs from album\n");
        sb.append("3: Add Song to playlist\n");
        sb.append("4: Add all Song from Selected Album\n");
        sb.append("5: exit");
        System.out.println(sb.toString());
    }

    private static void addSongToPlaylist(Playlist playlist) {
        displayAddSongOptions();
        boolean notSelected = true;
        String select = "";

        do {
            System.out.println("Please select option: ");
            select = scan.nextLine();
            switch (select) {
                case "0":
                    displayAddSongOptions();
                    break;
                case "1":
                    playlist.showAvailableAlbums();
                    break;
                case "2":
                    System.out.println("Please type album");
                    String album = scan.nextLine();
                    playlist.showAllSongsYouCanAddToPlayList(album);
                    break;
                case "3":
                    System.out.println("Please type song title");
                    String songTitle = scan.nextLine();
                    System.out.println("Please type album name");
                    String albumName = scan.nextLine();
                    notSelected = !playlist.addSongToPlayList(albumName, songTitle);
                    if (!notSelected) {
                        select = "Song was added correctly";
                    }
                    break;
                case "4":
                    playlist.showAvailableAlbums();
                    System.out.println("Please type album name");
                    String name = scan.nextLine();
                    notSelected = !playlist.addAllSongsFromAlbum(name);
                    if (!notSelected) {
                        select = "All songs was added correctly";
                    }
                    break;
                case "5":
                    select = "exit";
                    notSelected = false;
                    break;
                default:
                    break;
            }

        } while (notSelected);
        System.out.println(select);
    }

    public static Playlist createPlaylist() {
        String[] p = ReadWriteFiles.getAllFileNames("playlists");
        List<String> names = Arrays.stream(p).map(e -> e.replace(".json", "")).collect(Collectors.toList());
        Library library = selectLibrary();
        if (library == null) {
            System.out.println("You do not have any libraries");
            return null;
        }
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
        return playlist;
    }


    private static String selectObjectName(String parentFolder) {
        System.out.println("Please select library using numbers");
        String[] names = ReadWriteFiles.getAllFileNames(parentFolder);
        if (names.length <= 0) {
            return null;
        }
        int index = 1;
        for (String name : names) {
            System.out.println(index + ": " + name);
            index++;
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

        return names[select - 1].replace(".json", "");
    }

    private static Library selectLibrary() {
        String name = selectObjectName("libraries");
        if (name == null) {
            System.out.println("You dnot have any libraries");
            return null;
        }
        return fileManagerLibrary.getLibrary(name);
    }

    private static Playlist selectPlaylist() {
        String name = selectObjectName("playlists");
        if (name == null) {
            System.out.println("You do not have any playlists yet. do you want to Create one ? Yes/No");
            String choice = "";
            do {
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    return createPlaylist();
                } else if (choice.equalsIgnoreCase("no")) {
                    return null;
                } else {
                    System.out.println("Please type Yes/No");
                    choice = "run";
                }
            } while (choice.equals("run"));
        }
        return fileManagerPlaylist.getPlaylist(name);
    }


}
