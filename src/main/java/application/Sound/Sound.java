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
    public static void stop(MusicType music) {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
        musicPlayer = createMediaPlayer(getSoundFileName(music));
        musicPlayer.stop();
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
        switch (soundEffect) {
            case FISTPUNCH:
                return "punch.mp3";
            case HADOUKEN:
                return "hadouken.mp3";
            case BLOCK:
                return "block.mp3";
            case FIGHTER_GETS_HIT:
                return "fighter_gets_hit.wav";
            case JUMP_LANDING:
                return "jump_landing.wav";
            case SCREAM:
                return "scream.wav";
            case PAIN:
                return "man-in-pain.wav";
            default:
                throw new RuntimeException("No Soundfilename set for this enum value:" + soundEffect);
        }
    }
    private static String getSoundFileName(MusicType music) {
        return switch (music) {
            case GAME_BACKGROUND -> null;
            case FIGHT -> "Fight.mp3";

        };
    }


}