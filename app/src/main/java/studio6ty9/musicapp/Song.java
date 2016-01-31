package studio6ty9.musicapp;

/**
 * Created by Michael on 29.01.2016.
 */
public class Song {
    private String Title;
    private String Path;

    public Song(String title, String path) {
        Title = title;
        Path = path;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
