package com.tadeucruz.control;

import com.tadeucruz.model.FileMusic;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by tadeucruz on 3/27/15.
 */
public class MainControler {

    private boolean isMusic(File f) {
        boolean back = false;

        if (f.toString().toLowerCase().endsWith("mp3")) {
            back = true;
        }

        return back;
    }

    public void organizeMusic(String pathOri, String pathDest) {

        Collection<File> files = FileUtils.listFiles(new File(pathOri), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        for (File f : files) {
            if (isMusic(f)) {
                try {
                    FileMusic fm = new FileMusic(f.toString());

                    String distPath = pathDest + "/" + fm.getArtist() + "/" + fm.getAlbum();
                    String newMusic = distPath + "/" + fm.getTrack() + " - " + fm.getTitle() + "." + FilenameUtils.getExtension(f.toString());
                    mkdir(distPath);
                    copy(f.toString(), newMusic);

                } catch (TagException e) {
                    e.printStackTrace();
                } catch (ReadOnlyFileException e) {
                    e.printStackTrace();
                } catch (CannotReadException e) {
                    e.printStackTrace();
                } catch (InvalidAudioFrameException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void mkdir(String dir) throws IOException {
        dir = FilenameUtils.normalize(dir);
        FileUtils.forceMkdir(new File(dir));
    }

    private void copy(String src, String dist) throws IOException {
        FileUtils.copyFile(new File(src), new File(dist));
    }

}
