import java.io.File;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        try {
            File file = new File("di.mp3");
            String trance = "http://pub6.di.fm/di_trance";
            String house = "http://pub4.di.fm/di_soulfulhouse";
            String psy = "http://pub6.di.fm/di_progressivepsy";
            Downloader downloader = new Downloader(trance, file);
            Mp3Player mp3Player = new Mp3Player(file);

            Radio radio = new Radio(downloader, mp3Player);

            radio.control();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
