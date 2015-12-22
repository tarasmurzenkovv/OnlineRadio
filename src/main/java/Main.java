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
            Player player = new Player(file);

            Radio radio = new Radio(downloader,player);

            radio.control();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
