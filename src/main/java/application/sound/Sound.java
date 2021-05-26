package application.sound;

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
        filePath = "/sound/" + filePath;

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
            case FIST_PUNCH -> "punch.mp3";
            case HADOKEN -> "hadouken.mp3";
            case BLOCK -> "block.mp3";
            case FIGHTER_GETS_HIT -> "fighter_gets_hit.wav";
            case JUMP_LANDING -> "jump_landing.wav";
            case SCREAM -> "scream.wav";
            case PAIN -> "man-in-pain.wav";
            case CHARGE_UP -> "chargeup.wav";
            case KO -> "KO.mp3";

        };
    }

    private static String getSoundFileName(MusicType music) {
        return switch (music) {
            case GAME_BACKGROUND -> null;
            case MENU -> "Menu.mp3";

            case FIGHT -> "Fight.mp3";

        };
    }


}