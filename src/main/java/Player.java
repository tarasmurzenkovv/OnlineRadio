import jaco.mp3.player.MP3Player;

import java.io.File;import java.lang.Override;import java.lang.Runnable;import java.lang.String;

public class Player implements Runnable {
    private File file;
    private MP3Player mp3Player;

    public Player(File file) {
        this.file = file;
        this.mp3Player = new MP3Player(this.file);
    }

    public void play(){
        this.mp3Player.play();
    }

    public void stopPlaying(){
        this.mp3Player.stop();

    }

    public void pausePlaying(){
        this.mp3Player.stop();
    }

    public void resumePlaying(){

    }

    public void switchChannel(String channelUri){

    }

    @Override
    public void run() {
        this.play();
    }
}
