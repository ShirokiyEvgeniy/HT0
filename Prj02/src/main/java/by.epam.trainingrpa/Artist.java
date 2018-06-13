package by.epam.trainingrpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class describes an artist
 * @version 1.0
 * @author Eugene
 * */
public class Artist {
    /**
     * Name of the artist
     * */
    final private String name;
    /**
     * List with his/her albums
     * */
    private List<Album> albums;

    /**
     * Constructor with parameter {@link Artist#name}
     * @param name Name of the artist
     * */
    public Artist(String name) {
        this.name = name;
        albums = new ArrayList<>();
    }

    /**
     * Constructor with parameters {@link Artist#name} and {@link Artist#albums}
     * @param name Name of the artist
     * @param albums List of the albums
     * */
    public Artist(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    /**
     * Getter that returns value of the field {@link Artist#name}
     * @return name of the artist
     * */
    public String getName() {
        return name;
    }

    /**
     * Getter that returns value of the field {@link Artist#albums}
     * @return list of the albums
     * */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * This method is used for adding a new album to the list
     * @param album a new album
     * */
    public void addAlbum(Album album) {
        albums.add(album);
    }

    /**
     * This method is used for removing an album from the list
     * @param album an album to remove
     * */
    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    /**
     * Getter that returns the album by its name
     * @param string a name of the album
     * @return the album
     * */
    public Album getAlbum(String string) {
        for (Album a : albums) {
            if (a.getName().equals(string)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) &&
                Objects.equals(albums, artist.albums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, albums);
    }
}
