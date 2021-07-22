public class Config {

    private int threadCount = -1;

    public boolean initialize(String[] args) {

        if (args.length != 1) {
            System.err.println("Argument needed. java Program --threadsCount=2");
            return false;
        }

        String[] parse = args[0].split("=");
        if (!parse[0].equals("--threadsCount")) {
            System.err.println("Argument needed. java Program --threadsCount=2");
            return false;
        }

        try {
            int value = Integer.parseInt(parse[1]);
            if (value < 1){
                System.err.println("Invalid input value");
                return false;
            } else {
                threadCount = value;
            }
        } catch (Exception e) {
            System.err.println("Invalid input value");
            return false;
        }

        return true;
    }

    public int getThreadCount() {
        return threadCount;
    }
}
