public class SomeThread extends Thread {

    int count = 0;
    String message;

    public SomeThread(int count, String message) {
        this.count = count;
        this.message = message;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
            try {
                Thread.sleep(100, 0);
            } catch (Exception e) {
                System.err.println("Error in sleep");
                System.exit(-1);
            }
        }
    }
}
