public class Program {

    public static void paramError() {
        System.err.println("use: java Program --count=[num_of_count]");
        System.exit(-1);
    }

    public static void main(String[] args) {
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
        SomeThread Egg = new SomeThread(count, "Egg");
        SomeThread Hen = new SomeThread(count, "Hen");
        Egg.start();
        Hen.start();
        try {
            Egg.join();
            Hen.join();
        } catch (Exception e) {
            System.err.println("Error in join");
            System.exit(-1);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
