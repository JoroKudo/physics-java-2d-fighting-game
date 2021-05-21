package application.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound {
    private final static Map<String, Media> cache = new HashMap<>();
    private static MediaPlayer musicPlayer;

    public static void play(MusicType music) {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
        musicPlayer = createMediaPlayer(getSoundFileName(music));
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.play();
    }

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
        return switch (soundEffect) {
            case FISTPUNCH -> "punch.mp3";
            case HADOUKEN -> "hadouken.mp3";
            case BLOCK -> "block.mp3";
            case FIGHTER_GETS_HIT -> "fighter_gets_hit.wav";
            case JUMP_LANDING -> "jump_landing.wav";
            case SCREAM -> "scream.wav";
            case PAIN -> "man-in-pain.wav";
            case CHARGEUP -> "chargeup.wav";
        };
    }

    private static String getSoundFileName(MusicType music) {
        return switch (music) {
            case GAME_BACKGROUND -> null;
            case FIGHT -> "Fight.mp3";

        };
    }


}