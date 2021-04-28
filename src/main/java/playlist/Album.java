package playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
    private String title;
    private String band;
    private List<Song> songs;
    private String releaseYear;

    public Album(String title, String band) {
        this(title, band, "");
    }

    public Album(String title, String band, String releaseYear) {
        this.title = title;
        this.band = band;
        this.releaseYear = releaseYear;
        songs = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getBand() {
        return band;
    }

    public List<Song> getAllSongs() {
        return songs;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public boolean addMultipleSongs(ArrayList<Song> songs) {
        boolean result = false;
        boolean added = false;
        for (Song s : songs) {
            added = addSong(s);
            if (added && !result) {
                result = true;
            }

        }
        return result;
    }

    public Song getSong(String title) {
        int index = findSongIndex(title);
        Song song = null;
        if (index >= 0) {
            song = this.songs.get(index);
        }
        return song;
    }

    public boolean addSong(Song song) {
        song.setAlbumName(this.getTitle());
        boolean result = false;
        if (!checkIfExist(song)) {
            this.songs.add(song);
            result = true;
        }
        return result;
    }

    public boolean addSong(String title, double duration) {
        return addSong(Song.createSong(title, duration));
    }

    public boolean removeSong(String title) {
        Song song = Song.createSong(title);
        int index = findSongIndex(song);
        boolean result = false;
        if (index >= 0) {
            this.songs.remove(index);
            result = true;
        }
        return result;
    }

    public boolean updateSong(Song oldSong, Song newSong) {

        boolean result = false;
        if (!checkIfExist(newSong)) {
            int index = findSongIndex(oldSong);
            if (index >= 0) {
                this.songs.set(index, newSong);
                result = true;
            } else {
                System.out.println("The " + oldSong.getTitle() + " do not exist in this album");
            }

        }
        return result;
    }

    private boolean checkIfExist(Song song) {
        boolean doExist = false;
        for (Song s : this.songs) {
            if (s.getTitle().equals(song.getTitle())) {
                doExist = true;
                break;
            }
        }
        return doExist;
    }

    private int findSongIndex(String title) {
        return findSongIndex(new Song(title));
    }

    private int findSongIndex(Song song) {
        return this.songs.indexOf(song);
    }

    private int findSongIndexLoop(Song song) {
        int index = -1;
        for (Song s : this.songs) {
            index++;
            if (s.getTitle().equals(song.getTitle())) {
                break;
            }
        }

        return index;
    }

    @Override
    public String toString() {
        return "Album name: " + this.title + " band name: " + this.band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return title.equals(album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
