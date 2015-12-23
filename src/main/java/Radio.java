import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Radio {
    private Downloader downloader;
    private Mp3Player mp3Player;
    private ExecutorService pool;
    private int currentUriIndex;
    private final String[] uris = new String[]
            {"http://pub6.di.fm/di_trance"
                    , "http://pub4.di.fm/di_soulfulhouse",
                    "http://pub6.di.fm/di_progressivepsy"};

    public Radio(Downloader downloader, Mp3Player mp3Player) {
        this.downloader = downloader;
        this.mp3Player = mp3Player;
    }

    private void stopPlaying() {
        pool.shutdown();
        mp3Player.stopPlaying();
    }

    private void play() {
        this.pool = Executors.newFixedThreadPool(2);
        boolean hasFinished = false;
        try {
            hasFinished = downloader.buffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hasFinished) {
            pool.submit(mp3Player);
            pool.submit(downloader);
        }
    }

    private void switchChannel(String newChannelUri) {
        downloader.setResourceUri(newChannelUri);
        this.stopPlaying();
        this.play();
    }

    public void control() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "stop":
                    this.stopPlaying();
                    break;
                case "play":
                    this.play();
                    break;
                case "switch":
                    System.out.println("Enter uri to play");
                    String channel = scanner.nextLine();
                    this.switchChannel(channel);
                    break;
                case "next":
                    String switchUri = this.uris[currentUriIndex % uris.length];
                    this.switchChannel(switchUri);
                    currentUriIndex++;
                case "quit":
                    System.out.println("Buy!");
                    System.exit(0);
            }
        }
    }
}
