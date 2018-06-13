package by.epam.trainingrpa;

import java.util.Date;
import java.util.Objects;

/**
 * This class describes a song
 * @version 1.0
 * @author Eugene
 * */
public class Song {
    /**
     * Name of the song
     * */
    final private String name;
    /**
     * Duration of the song
     * */
    final private Date duration;
    /**
     * Path to the song
     * */
    final private String path;

    /**
     * Constructor with parameters {@link Song#name}, {@link Song#duration} and {@link Song#path}
     * @param name Name of the song
     * @param duration Duration of the song
     * @param path Path to the song
     * */
    public Song(String name, Date duration, String path) {
        this.name = name;
        this.duration = duration;
        this.path = path;
    }

    /**
     * Getter that returns value of the field {@link Song#name}
     * @return name of the song
     * */
    public String getName() {
        return name;
    }

    /**
     * Getter that returns value of the field {@link Song#duration}
     * @return duration of the song
     * */
    public Date getDuration() {
        return duration;
    }

    /**
     * Getter that returns value of the field {@link Song#path}
     * @return path of the song
     * */
    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(name, song.name);
    }

    @Override
    public int hashCode() {
        //program will use only name for defining duplicates by artist, album and sons's name
        return Objects.hash(name);
    }
}
