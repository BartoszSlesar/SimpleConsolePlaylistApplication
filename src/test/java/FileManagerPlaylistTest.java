import playlist.Playlist;
import utils.FileManagerPlaylist;

public class FileManagerPlaylistTest {
    public static void main(String[] args) {
        FileManagerPlaylist fileManager = new FileManagerPlaylist();
        Playlist playlist = fileManager.getPlaylist("test");
        playlist.showAllSongsYouCanAddToPlayList();
    }
}
