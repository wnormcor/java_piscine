import java.util.concurrent.locks.ReentrantLock;

public class Program {

    public static void paramError() {
        System.err.println("use: java Program --count=[num_of_count]");
        System.exit(-1);
    }

    public static void main(String[] args) throws InterruptedException {
        if (1 != args.length) {
            paramError();
        }
        String[] params = args[0].split("=", 0);
        if (!params[0].equals("--count")) {
            paramError();
        }
        Integer count=0;
        try {
            count = Integer.valueOf(params[1]);
        } catch (Exception e) {
            paramError();
        }

        final Producer pc = new Producer();

        int finalCount = count;
        Thread threadEgg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.egg(finalCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadHen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.hen(finalCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadEgg.start();
        threadHen.start();

        threadEgg.join();
        threadHen.join();

    }
}
