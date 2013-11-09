package MKind;

import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Omnicis
 */
public class Sound {

    String pathPrefix = "MKind/audio/";
    String fileName;
    int lastPlay;
    AudioStream as;

    Sound(String fName) {
        lastPlay = 0;
        fileName = fName;
        try {
            as = new AudioStream(ClassLoader.getSystemClassLoader().getResourceAsStream(pathPrefix + fName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void play() {
        AudioPlayer.player.start(as);
    }
}
