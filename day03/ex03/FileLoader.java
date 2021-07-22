import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileLoader extends Thread{
    private volatile boolean isFree = true;

    private volatile String pathToLoad;

    private int id;

    private int urlId;

    public FileLoader(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            if (!isFree){
                try {
                    System.out.println("Thread-" + id + " start download file number " + urlId);
                    URL url = new URL(pathToLoad);

                    BufferedInputStream reader = new BufferedInputStream((url.openStream()));

                    String[] splitStr = pathToLoad.split("/");

                    if (splitStr.length > 0){
                        FileOutputStream fileOutputStream = new FileOutputStream(splitStr[splitStr.length-1]);


                        byte[] buffer  = new byte[1024];

                        int read;

                        while ((read = reader.read(buffer, 0, 1024)) != -1){
                            fileOutputStream.write(buffer, 0, read);
                        }

                        fileOutputStream.close();
                        System.out.println("Thread " + id + " end download file number " + urlId);
                        setFree(true);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Thread " + id + " failed to load file number " + urlId);
                    setFree(true);
                }
            }
        }
    }

    public synchronized void setNewTask(String pathToLoad, int urlId) {
        this.pathToLoad = pathToLoad;
        this.urlId = urlId;
        isFree = false;
    }

    public synchronized void setFree(boolean free) {
        isFree = free;
    }

    public synchronized boolean isFree() {
        return isFree;
    }
}
