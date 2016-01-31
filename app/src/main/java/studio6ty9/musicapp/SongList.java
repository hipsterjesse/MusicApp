package studio6ty9.musicapp;

import android.os.Environment;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 30.01.2016.
 */
public class SongList {
    private static List<Song> songs = new ArrayList<Song>();
    private  File[] musicFiles;

    public SongList() {
        musicFiles = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).listFiles();
        for (int i= 0; i <musicFiles.length; i++){
            songs.add(new Song(musicFiles[i].getName(), musicFiles[i].toString()));
        }
    }

    public static List<Song> getSongs() {
        return songs;
    }
}
