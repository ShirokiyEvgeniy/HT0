package by.epam.trainingrpa;

import java.util.Objects;

/**
 * This class is used for logging songs with similar artist, album and song name
 * @version 1.0
 * @author Eugene
 * */
public class SongToLog {
    /**
     * Artist of the song
     * */
    private Artist artist;
    /**
     * Album of the song
     * */
    private Album album;
    /**
     * Song that is similar
     * */
    private Song song;

    /**
     * Constructor with parameters
     * @param artist Artist
     * @param album Album
     * @param song Song
     * */
    public SongToLog(Artist artist, Album album, Song song) {
        this.artist = artist;
        this.album = album;
        this.song = song;
    }

    /**
     * Getter that returns value of the field {@link SongToLog#artist}
     * @return Artist
     * */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Getter that returns value of the field {@link SongToLog#album}
     * @return Album
     * */
    public Album getAlbum() {
        return album;
    }

    /**
     * Getter that returns value of the field {@link SongToLog#song}
     * @return Song
     * */
    public Song getSong() {
        return song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongToLog songToLog = (SongToLog) o;
        return Objects.equals(artist, songToLog.artist) &&
                Objects.equals(album, songToLog.album) &&
                Objects.equals(song, songToLog.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, album, song);
    }
}
