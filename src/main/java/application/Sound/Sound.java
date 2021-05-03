package application.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound {
    private final static Map<String, Media> cache = new HashMap<>();
    private static MediaPlayer musicPlayer;


    public static void play(SoundEffectType soundEffect) {
        MediaPlayer player = createMediaPlayer(getSoundFileName(soundEffect));
        player.play();
    }

    private static MediaPlayer createMediaPlayer(String filePath) {
        filePath = "/Sound/" + filePath;

        if (!cache.containsKey(filePath)) {
            URL url = Sound.class.getResource(filePath);
            if (url == null) {
                throw new RuntimeException("Could not load file: " + filePath);
            }

            cache.put(filePath, new Media(url.toString()));
        }

        return new MediaPlayer(cache.get(filePath));
    }

    private static String getSoundFileName(SoundEffectType soundEffect) {
        switch (soundEffect) {
            case FISTPUNCH:
                return "punch.mp3";
            case HADOUKEN:
                return "hadouken.mp3";
            case BLOCK:
                return "block.mp3";
            default:
                throw new RuntimeException("No Soundfilename set for this enum value:" + soundEffect);
        }
    }


}