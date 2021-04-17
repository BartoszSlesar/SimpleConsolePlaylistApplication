package utils;


import playlist.Library;
import playlist.Playlist;

import java.util.List;

public interface LocalPersistentLibrary {


    void saveData(Library library);

    List<Library> getLibraries();

    Library getLibrary(String name);

    void deleteLibrary(String name);
}
