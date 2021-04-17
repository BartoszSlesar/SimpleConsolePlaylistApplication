package playlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

public class Playlist {
    private String name;
    private Library library;
    private LinkedList<Song> playListSongs;

    public Playlist(String name, Library library) {
        this.name = name;
        this.library = library;
        playListSongs = new LinkedList<Song>();
    }

    public String getName() {
        return name;
    }

    public String getLibraryName() {
        return library.getLibraryName();
    }

    public void addSongToPlayList(String albumName, String bandName, String songTitle) {
        Album album = new Album(albumName, bandName);
        addSongToPlayList(album, songTitle);
    }

    public void addSongToPlayList(Album album, String songTitle) {
        Song song = library.findSong(album, songTitle);
        if (song == null) {
            System.out.println("Song " + songTitle + " can't be added");
        } else {
            this.playListSongs.addFirst(song);
        }
    }

    public int addSongToPlayList(String songTitle) {
        ArrayList<Song> songs = library.findSong(songTitle);
        int returnValue = -1;
        if (songs.isEmpty()) {
            System.out.println("Song with this name do not exist");
        } else if (songs.size() == 1) {
            this.playListSongs.addFirst(songs.get(0));
            returnValue = 0;
        } else {
            System.out.println("Multiple songs was found, please chose which one you want to add");
            returnValue = 1;
        }

        return returnValue;
    }


    public boolean removeSongFromPlayList(int index) {
        this.playListSongs.remove(index);
        return true;
    }

    public boolean removeSongFromPlayList(String title) {
        ListIterator<Song> iterator = getSongIterator();
        boolean result = false;
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getTitle().equals(title)) {
                iterator.remove();
                result = true;
                break;
            }
        }
        return result;
    }

    public ListIterator<Song> getSongIterator() {
        return this.playListSongs.listIterator();
    }

    public void showAllSongsYouCanAddToPlayList() {
        library.showAlbums(true);
    }

    public void ListAllSongsInPlaylist() {
        int index = 1;
        for (Song song : this.playListSongs) {
            System.out.println(index + ": " + song.getTitle() + " album: " + song.getAlbumName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return name.equals(playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
