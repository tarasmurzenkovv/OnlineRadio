import java.io.File;
import java.lang.Exception;import java.lang.String;import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        try {
            File file = new File("di.mp3");
            String trance = "http://pub6.di.fm/di_trance";
            String house = "http://pub4.di.fm/di_soulfulhouse";
            Downloader downloader = new Downloader(house, file);
            Player player = new Player(file);
            ExecutorService pool = Executors.newFixedThreadPool(2);

            boolean hasFinished = downloader.buffer();
            
            if (hasFinished) {
                pool.submit(player);
                pool.submit(downloader);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
