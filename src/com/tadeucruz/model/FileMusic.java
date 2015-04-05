package com.tadeucruz.model;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by tadeucruz on 3/27/15.
 */
public class FileMusic {

    private String path;
    private File music;
    private Tag musicTag;

    public FileMusic(String path) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        this.path = path;
        music = new File(this.path);
        AudioFile f = AudioFileIO.read(music);
        musicTag = f.getTag();
    }

    public String getArtist() {
        String volta = null;
        try {
            volta = musicTag.getFirst(FieldKey.ARTIST);
        } catch (NullPointerException ex) {
            System.out.println("O arquivo não apresenta um Artista para fazer a organização: " + path);
            volta = null;
        }

        return volta;
    }

    public String getAlbum() {
        return musicTag.getFirst(FieldKey.ALBUM);
    }

    public String getTitle() {
        return musicTag.getFirst(FieldKey.TITLE);
    }

    public String getTrack() {
        return musicTag.getFirst(FieldKey.TRACK);
    }

    public File getMusic() {
        return music;
    }
}
