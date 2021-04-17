package playlist;

import java.util.Objects;

public class Song {
    private String title;
    private double duration;
    private String albumName;


    public Song(String title) {
        this(title, 0.0, "");
    }


    public Song(String title, double duration) {
        this(title, duration, "");
    }

    public Song(String title, double duration, String albumName) {
        this.title = title;
        this.duration = duration;
        this.albumName = albumName;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public static Song createSong(String title) {
        return new Song(title);
    }

    public static Song createSong(String title, double duration) {
        return new Song(title, duration);
    }

    public static Song createSong(String title, double duration, String albumName) {
        return new Song(title, duration, albumName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return title.equals(song.title) && Objects.equals(albumName, song.albumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, albumName);
    }

    @Override
    public String toString() {
        return "Title: " + this.title + ", Duration: " + duration + " min";
    }
}
