package playlist;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String libraryName;
    private List<Album> albums;


    public Library(String libraryName) {
        this(libraryName, new ArrayList<Album>());
    }

    public Library(String libraryName, List<Album> albums) {
        this.libraryName = libraryName;
        this.albums = albums;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public boolean addAlbum(Album... album) {
        boolean result = false;
        for (Album a : album) {
            boolean check = checkIfExist(a);
            if (!check) {
                this.albums.add(a);
                result = true;
            }
        }
        return result;
    }

    public boolean removeAlbum(Album album) {
        int index = findAlbumIndex(album);
        boolean result = false;
        if (index >= 0) {
            this.albums.remove(index);
            result = true;
        }
        return result;
    }

    public boolean updateAlbum(String oldAlbumName, String band, Album newAlbum) {
        Album oldAlbum = getAlbum(oldAlbumName, band);
        if (oldAlbum == null) {
            System.out.println(oldAlbumName + " do not exist");
            return false;
        }
        return updateAlbum(oldAlbum, newAlbum);
    }


    public boolean updateAlbum(Album oldAlbum, Album newAlbum) {
        int index = findAlbumIndex(oldAlbum);
        boolean result = false;
        if (!checkIfExist(newAlbum)) {
            this.albums.set(index, newAlbum);
            result = true;
        }
        return result;
    }


    public boolean addSongToAlbum(String band, String albumName, String songTitle, double duration) {
        Album album = new Album(albumName, band);
        return addSongToAlbum(album, songTitle, duration);

    }

    public boolean addSongToAlbum(Album album, String songTitle, double duration) {
        Album al = getAlbum(album);
        if (al == null) {
            System.out.println(album.getTitle() + " do not exist");
            return false;
        }
        al.addSong(songTitle, duration);
        return true;
    }

    private int findAlbumIndex(String name, String band) {
        return findAlbumIndex(name, band);
    }

    private int findAlbumIndex(Album album) {
        return this.albums.indexOf(album);
    }


    public ArrayList<Song> findSong(String title) {
        ArrayList<Song> foundSongs = new ArrayList<>();
        for (Album album : this.albums) {
            Song song = findSong(album, title);
            if (song != null) {
                foundSongs.add(song);
            }
        }
        return foundSongs;
    }


    public Song findSong(String albumName, String band, String songTitle) {
        return findSong(new Album(albumName, band), songTitle);
    }


    public Song findSong(Album album, String songTitle) {
        Song song = null;
        Album al = getAlbum(album);
        if (al != null) {
            for (Song s : al.getAllSongs()) {
                if (s.getTitle().equals(songTitle)) {
                    song = s;
                    break;
                }
            }
        }
        return song;
    }

    public ArrayList<Album> findAlbumForBand(String band) {
        ArrayList<Album> foundAlbums = new ArrayList<>();
        for (Album album : this.albums) {
            if (album.getBand().equals(band)) {
                foundAlbums.add(album);
            }
        }
        return foundAlbums;
    }

    public Album getAlbum(String name) {
        return getAlbum(name, "");
    }

    public Album getAlbum(String name, String band) {
        return getAlbum(new Album(name, band));
    }

    public Album getAlbum(Album album) {
        int index = findAlbumIndex(album);
        Album al = null;
        if (index >= 0) {
            al = this.albums.get(index);
        }
        return al;
    }

    public boolean checkIfExist(Album album) {
        return this.albums.contains(album);
    }

    public int showAlbums(boolean showAllSongs) {
        int index = 1;
        for (Album album : this.albums) {
            System.out.println(index + ". " + album.toString());
            index++;
            if (showAllSongs) {
                System.out.println("Songs:");
                listAllSongs(album);
            }
            System.out.println("================================================");
        }
        return index;
    }

    public void listAllSongs(Album album) {
        int index = 1;
        for (Song song : album.getAllSongs()) {
            System.out.println(index + ". " + song.toString());
            index++;
        }
    }


}
