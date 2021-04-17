package utils;


import com.google.gson.Gson;
import playlist.Library;
import playlist.Playlist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagerPlaylist implements LocalPersistentPlaylist {

    @Override
    public void saveData(Playlist playlist) {
        Gson gson = new Gson();
        String json = gson.toJson(playlist);
        ReadWriteFiles.writeUsingFileWriter(json, playlist.getName(), "playlists");
    }


    @Override
    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        Gson gson = new Gson();
        for (File file : ReadWriteFiles.getAllFilesFromSource("playlists")) {
            if (file.isFile()) {
                String json = ReadWriteFiles.readUsingScanner(file);
                Playlist playlist = gson.fromJson(json, Playlist.class);
                playlists.add(playlist);
            }

        }
        return playlists;
    }


    @Override
    public Playlist getPlaylist(String name) {
        name = name.strip().replaceAll(" ", "_");
        String path = "src/main/resources/playlists/" + name + ".json";
        Gson gson = new Gson();
        File file = new File(path);
        Playlist playlist = null;
        if (file.isFile()) {
            String json = ReadWriteFiles.readUsingScanner(file);
            playlist = gson.fromJson(json, Playlist.class);
        } else {
            System.out.println("Playlist do not exist");
        }

        return playlist;
    }

    @Override
    public void deletePlaylist(String name) {
        String path = "src/data/playlists/" + name + ".json";
        File file = new File(path);
        if (file.isFile() && file.delete()) {
            System.out.println("Playlist was deleted");
        } else {
            System.out.println("Playlist do not exist");
        }
    }


}
