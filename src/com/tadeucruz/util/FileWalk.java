package com.tadeucruz.util;

import com.tadeucruz.model.FileMusic;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by tadeucruz on 3/27/15.
 */
public class FileWalk extends SimpleFileVisitor<Path> {

    private ArrayList<FileMusic> listFileMusic = new ArrayList<FileMusic>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().toLowerCase().endsWith("mp3")) {
            try {
                FileMusic fm = new FileMusic(file.toString());
                listFileMusic.add(fm);
            } catch (TagException e) {
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();
            } catch (CannotReadException e) {
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();
            }
        }
        return super.visitFile(file, attrs);
    }

    public ArrayList<FileMusic> getListFileMusic() {
        return listFileMusic;
    }
}
