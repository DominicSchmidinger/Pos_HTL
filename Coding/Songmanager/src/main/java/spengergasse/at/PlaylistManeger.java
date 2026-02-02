package spengergasse.at;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaylistManeger {
    private List<Song> playlist;


    public PlaylistManeger() {
        this.playlist = new ArrayList<>();
    }

    public void addSongs(Song song) {
        //if (playlist.contains(song)) {
        //throw new IllegalArgumentException("mag ich nicht");
        //}
        playlist.add(song);
    }

    public ArrayList<Song> filterByGernre(String genre) {
        ArrayList<Song> gemeinsamegenre = new ArrayList<>();

        if (genre == null) throw new IllegalArgumentException("Genre kann nicht null sein");
        for (Song s : playlist) {
            if (s.getGenre().equals(genre)) {
                gemeinsamegenre.add(s);
            }
        }
        return gemeinsamegenre;
    }

    public void shufflePlaylist() {
        Random rnd = new Random();
    }

    public void removeDuplicate(Song song) {
        while (playlist.contains(song)) {
            playlist.remove(song);
        }
        playlist.add(song);
    }

    @Override
    public String toString() {
        return "PlaylistManeger{" +
                "playlist=" + playlist +
                '}';
    }
}
