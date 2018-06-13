package by.epam.trainingrpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class describes an album
 * @version 1.0
 * @author Eugene
 * */
public class Album {
    /**
     * Name of the album
     * */
    final private String name;
    /**
     * List of the songs of this album
     * */
    private List<Song> songs;

    /**
     * Constructor with parameter {@link Album#name}
     * @param name Name of the album
     * */
    public Album(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    /**
     * Constructor with parameters {@link Album#name} and {@link Album#songs}
     * @param name Name of the album
     * @param songs List of the songs
     * */
    public Album(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    /**
     * Getter that returns value of the field {@link Album#name}
     * @return name of the album
     * */
    public String getName() {
        return name;
    }

    /**
     * Getter that returns value of the field {@link Album#songs}
     * @return list of the songs
     * */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * This method is used for adding a new song to the list
     * @param song a new song
     * */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * This method is used for removing a song from the list
     * @param song a song to remove
     * */
    public void removeSong(Song song) {
        songs.remove(song);
    }

    /**
     * Getter that returns the song by its name
     * @param string a name of the song
     * @return the song
     * */
    public Song getSong(String string) {
        for (Song s : songs) {
            if (s.getName().equals(string)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) &&
                Objects.equals(songs, album.songs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, songs);
    }
}
