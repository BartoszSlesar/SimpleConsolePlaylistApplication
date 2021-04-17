package utils;


import playlist.Library;
import playlist.Playlist;

import java.util.List;

public interface LocalPersistentPlaylist {

     void saveData(Playlist playlist);


     List<Playlist> getPlaylists();


     Playlist getPlaylist(String name);


     void deletePlaylist(String name);
}
