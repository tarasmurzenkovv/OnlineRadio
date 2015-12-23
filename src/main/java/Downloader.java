import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Downloader implements Runnable {
    private static final int BUFFER_SIZE = 4096;
    private String resourceUri;
    private File file;

    public Downloader(String resourceUri, File file) {
        this.resourceUri = resourceUri;
        this.file = file;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public void continueDownloading() throws IOException {
        InputStream inputStream = new URL(resourceUri).openStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        byte[] buffer = new byte[BUFFER_SIZE*100];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
    }

    public boolean buffer() throws IOException {
        InputStream inputStream = new URL(resourceUri).openStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        for (int i = 0; i < BUFFER_SIZE*10; i += inputStream.read(buffer)) {
            fileOutputStream.write(buffer);
        }
        return true;
    }

    @Override
    public void run() {
        try {
            this.continueDownloading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
