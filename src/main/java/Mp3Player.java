import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Mp3Player implements Runnable {
    private File file;
    private Player player;

    public Mp3Player(File file) {
        this.file = file;
    }

    public void play() {
        try {
            FileInputStream fis = new FileInputStream("di.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
            player.play();
        } catch (Exception e) {
            System.out.println("Problem playing file " + file.getName());
        }
    }

    public void stopPlaying() {
        this.player.close();
        this.file.delete();
    }

    @Override
    public void run() {
        this.play();
    }
}
