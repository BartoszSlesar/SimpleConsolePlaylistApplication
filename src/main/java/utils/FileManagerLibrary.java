package utils;

import com.google.gson.Gson;
import playlist.Library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManagerLibrary implements LocalPersistentLibrary {
    @Override
    public void saveData(Library library) {
        Gson gson = new Gson();
        String json = gson.toJson(library);
        ReadWriteFiles.writeUsingFileWriter(json, library.getLibraryName(),"libraries");
    }

    @Override
    public List<Library> getLibraries() {
        List<Library> libraryList = new ArrayList<>();
        Gson gson = new Gson();
        for (File file : ReadWriteFiles.getAllFilesFromSource("libraries")) {
            if (file.isFile()) {
                String json = ReadWriteFiles.readUsingScanner(file);
                Library bank = gson.fromJson(json, Library.class);
                libraryList.add(bank);
            }

        }
        return libraryList;
    }

    @Override
    public Library getLibrary(String name) {
        name = name.strip().replaceAll(" ", "_");
        String path = "src/main/resources/libraries/" + name + ".json";
        Gson gson = new Gson();
        File file = new File(path);
        Library library = null;
        if (file.isFile()) {
            String json = ReadWriteFiles.readUsingScanner(file);
            library = gson.fromJson(json, Library.class);
        } else {
            System.out.println("Library do not exist");
        }

        return library;

    }

    @Override
    public void deleteLibrary(String name) {
        name = name.strip().replaceAll(" ", "_");
        String path = "src/main/resources/libraries/" + name + ".json";
        File file = new File(path);
        if (file.isFile() && file.delete()) {
            System.out.println("Library was deleted");
        } else {
            System.out.println("Library do not exist");
        }
    }



}
