package by.epam.trainingrpa;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

public class Main {
    /**
     * Is used for finding all subdirectories and putting them into Set
     * @param dir Start for finding
     * @param set Set for putting new subdirectories
     * */
    private static void recursiveFinder(File dir, HashSet<File> set) { //this method finds all subdirectories
        set.add(dir);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory() && file.canRead()) {
                set.add(file);
                recursiveFinder(file, set);
            }
        }
    }

    /**
     * Is used for getting extension of the file
     * @param mystr Path to the file
     * @return extension
     * */
    private static String getFileExtension(String mystr) { // returns file extension
        int index = mystr.lastIndexOf('.');
        return index == -1 ? null : mystr.substring(index + 1);
    }

    /**
     * Checks the correctness of the file or directory
     * @param string Path for checking
     * @return true if path is correct
     * */
    private static boolean isCorrectFile(String string) { // define correctness of the file
        return string.matches("([A-Z|a-z]:[/][^*|\"<>?\\n]*)|([/].*?[/].*)");
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Usage: Enter paths to directories in which you want to find\n" +
                                   "\t   song and make a catalog with them.\n" +
                                   "\t   Example: \"C:/\" \"D:/Music\".\n" +
                                   "\t   Please, use regular slashes, not back slashes.");
                return;
            }
            HashSet<File> allDirs = new HashSet<>(); // set with all directories and subdirectories
            ArrayList<Artist> artists = new ArrayList<>(); // list with all artists
            HashSet<File> mp3Files = new HashSet<>(); // list with all checked mp3-files
            HashMap<HashCode, ArrayList<File>> equalsCheckSum = new HashMap<>(); // mp3-files with similar checksum
            HashMap<SongToLog, ArrayList<File>> notEqualsCheckSum = new HashMap<>(); // mp3-files with similar artists, albums and song's names

            for (String arg : args) { //loop for finding all subdirectories and directories
                if (isCorrectFile(arg)) {
                    File temp = new File(arg);
                    if (temp.isDirectory() && temp.exists() && temp.canRead()) {
                        recursiveFinder(temp, allDirs);
                    }
                } else {
                    System.out.println(arg + " is incorrect");
                }
            }
            if (allDirs.isEmpty()) {
                System.out.println("All directories were incorrect");
                return;
            }
            for (File file : allDirs) { // loop for making our lists
                File[] files = file.listFiles();
                if (files != null) {
                    Hash: for (File temp : files) {
                        if (temp.isFile() && "mp3".equals(getFileExtension(temp.toPath().toString()))) {
                            for (File mp3 : mp3Files) {
                                HashCode hashCode1 = Files.asByteSource(mp3).hash(Hashing.md5());
                                HashCode hashCode2 = Files.asByteSource(temp).hash(Hashing.md5());
                                // if hash codes are similar, program adds this song to the list
                                // and starts checking other files
                                if (hashCode1.equals(hashCode2)) {
                                    ArrayList<File> list = equalsCheckSum.get(hashCode1);
                                    if (list == null) {
                                        list = new ArrayList<>();
                                    }
                                    list.add(temp);
                                    equalsCheckSum.put(hashCode1, list);
                                    continue Hash;
                                }
                            }
                            // getting information about mp3-file
                            InputStream input = new FileInputStream(temp);
                            ContentHandler handler = new DefaultHandler();
                            Metadata metadata = new Metadata();
                            Parser parser = new Mp3Parser();
                            ParseContext parseCtx = new ParseContext();
                            parser.parse(input, handler, metadata, parseCtx);
                            input.close();

                            String artist = metadata.get("xmpDM:artist");
                            if (artist == null) {
                                artist = "NO_NAME";
                            }
                            String album = metadata.get("xmpDM:album");
                            if (album == null) {
                                album = "NO_ALBUM";
                            }
                            String song = metadata.get("title");
                            if (song == null) {
                                song = "NO_SONG";
                            }
                            String duration = metadata.get("xmpDM:duration");
                            if (duration == null) {
                                duration = "0";
                            }
                            int pos = duration.lastIndexOf('.');
                            if (pos != -1) {
                                duration = duration.substring(0, pos);
                            }

                            Date date = new Date(Long.parseLong(duration));


                            boolean flag = true;
                            //loop for adding new songs
                            //if song is a duplicate, it will be putted in the list
                            //and program will start checking next mp3-file
                            for (Artist a : artists) {
                                if (a.getName().equals(artist)) {
                                    flag = false;
                                    if (a.getAlbum(album) != null) {
                                        if (a.getAlbum(album).getSong(song) != null) {
                                            ArrayList<File> list = notEqualsCheckSum.get(new SongToLog(a, a.getAlbum(album), new Song(song, date, temp.getPath())));
                                            if (list == null) {
                                                list = new ArrayList<>();
                                            }
                                            File tmp = new File(temp.getPath());
                                            list.add(tmp);
                                            notEqualsCheckSum.put(new SongToLog(a, a.getAlbum(album), new Song(song, date, temp.getPath())), list);
                                            continue Hash;
                                        } else {
                                            a.getAlbum(album).addSong(new Song(song, date, temp.getPath()));
                                        }
                                    } else {
                                        Album al = new Album(album);
                                        al.addSong(new Song(song, date, temp.getPath()));
                                        a.addAlbum(al);
                                    }
                                }
                            }
                            if (flag) {
                                Artist a = new Artist(artist);
                                Album al = new Album(album);
                                al.addSong(new Song(song, date, temp.getPath()));
                                a.addAlbum(al);
                                artists.add(a);
                            }
                            mp3Files.add(temp);
                        }
                    }
                }
            }
            int count = 1;
            System.setProperty("log4j.configurationFile", "src/main/resources/configuration/log4j2.xml");
            Logger logger = LogManager.getLogger("logger1");
            logger.info("////////Logging at " + (new Date()) + "////////");
            for (Map.Entry<HashCode, ArrayList<File>> entry : equalsCheckSum.entrySet()) {
                ArrayList<File> value = entry.getValue();
                logger.info("Duplicates " + count + ":");
                for (File f : value) {
                    logger.info("\t" + f.getAbsolutePath());
                }
                count++;
            }
            logger = LogManager.getLogger("logger2");
            logger.debug("////////Logging at " + (new Date()) + "////////");
            for (Map.Entry<SongToLog, ArrayList<File>> entry : notEqualsCheckSum.entrySet()) {
                SongToLog songToLog = entry.getKey();
                ArrayList<File> value = entry.getValue();
                logger.debug("Artist: " +songToLog.getArtist().getName() + ", Album: " + songToLog.getAlbum().getName() + ", Song: " + songToLog.getSong().getName());
                for (File f : value) {
                    logger.debug("\t" + f.getAbsolutePath());
                }
            }
            File result = new File("result.html");
            Writer writer = new PrintWriter(new BufferedWriter(new FileWriter(result)));
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            TimeZone tz = TimeZone.getTimeZone("GMT");
            dateFormat.setTimeZone(tz);
            writer.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    " <head>\n" +
                    "  <meta charset=\"utf-8\">\n" +
                    "  <title>Catalog mp3</title>\n" +
                    " </head>\n" +
                    " <body> \n");
            writer.append("<table cellspacing=\"0\" border=\"1\">\n");
            writer.append("<tr><th>Artist</th><th>Album</th><th>Song</th><th>Duration</th></tr>");
            for (Artist a : artists) {
                writer.append("<tr>").append("<td>").append(a.getName()).append("</td>");
                writer.append("<td></td><td></td><td></td></tr>");
                for (Album al : a.getAlbums()) {
                    writer.append("<tr><td></td><td>").append(al.getName()).append("</td>");
                    writer.append("<td></td><td></td></tr>");

                    for (Song s : al.getSongs()) {
                        writer.append("<tr><td></td><td></td><td>");
                        writer.append(s.getName()).append("</td>");
                        writer.append("<td>").append(dateFormat.format(s.getDuration())).append("</td></tr>");
                    }
                }
            }
            writer.append("</table>\n");
            writer.append( " </body>\n" + "</html>");
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}