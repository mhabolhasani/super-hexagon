package Menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;
    protected static boolean isRunning = false;

    protected static void playAudio(String filePath){
        try {
            if(!isRunning) {
                File audioFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                if (!isPlaying()) {
                    clip.start();
                    MusicPlayer.isRunning = true;
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void MuteSong(){
        if(isPlaying()) {
            clip.stop();
        }
    }

    public static void continuePlaying(){
        if(!isPlaying()) {
            clip.start();
        }
    }

    public static boolean isPlaying() {
        return MusicPlayer.clip != null && MusicPlayer.clip.isRunning();
    }

}