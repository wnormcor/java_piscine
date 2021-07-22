import java.util.Random;
import java.util.Vector;

public class Program {

    private static int sumOfThreads;

    static class GenArrayForThreading {
        int sumOfIntInArray = 0;
        int[] genArray;

        public void genArray(int arrSize) {
            Random rd = new Random();
            genArray = new int[arrSize];
            for (int i = 0; i < genArray.length; i++) {
                genArray[i] = rd.nextInt(1000);
                sumOfIntInArray += genArray[i];
            }
        }

        public int getSumOfIntInArray() {
            return sumOfIntInArray;
        }

        public int[] getGenArray() {
            return genArray;
        }
    }

    static class progThread extends Thread {

        int id;
        int first;
        int last;
        int[] arr;

        public progThread(int id, int first, int last, int[] arr) {
            this.id = id;
            this.first = first;
            this.last = last;
            this.arr = arr;
        }

        public void run() {
            int i = first;
            int j = last;
            int sum = 0;
            for (; i < j; ++i) {
                sum += arr[i];
            }
            sumOfThreads += sum;
            System.out.println("Thread " + ++id + ": from " + first + " to " + (last - 1) + " sum is " + sum);
        }
    }

    public static void paramError() {
        System.err.println("use: java Program --arraySize=[size_of_array > 0] --threadsCount=[count_of_threads > 0 < size_of_array]");
        System.exit(-1);
    }

    public static void main(String[] args) throws InterruptedException {
        if (2 != args.length) {
            paramError();
        }
        String[] paramArraySize = args[0].split("=", 0);
        if (!paramArraySize[0].equals("--arraySize")) {
            paramError();
        }
        Integer arraySize=0;
        try {
            arraySize = Integer.valueOf(paramArraySize[1]);
        } catch (Exception e) {
            paramError();
        }
        String[] paramThreadsCount = args[1].split("=", 0);
        if (!paramThreadsCount[0].equals("--threadsCount")) {
            paramError();
        }
        Integer threadsCount=0;
        try {
            threadsCount = Integer.valueOf(paramThreadsCount[1]);
        } catch (Exception e) {
            paramError();
        }
        if (arraySize < 1 || threadsCount < 1 || threadsCount > arraySize) {
            paramError();
        }

        GenArrayForThreading object = new GenArrayForThreading();
        object.genArray(arraySize);
        System.out.println("Sum: " + object.getSumOfIntInArray());

        int arr[] = object.getGenArray();

        int module = arraySize % threadsCount;
        int border = arraySize / threadsCount;

        Vector<progThread> arrayOfThreads = new Vector<progThread>();

        if (module != 0) {
            int i = 0, j = 0;
            for (; j < threadsCount - 1; i += border, ++j) {
                arrayOfThreads.add(new progThread(j, i, i + border, arr));
            }
            arrayOfThreads.add(new progThread(j, i, arraySize, arr));
        } else {
            for (int i = 0, j = 0; j < threadsCount; i += border, ++j) {
                arrayOfThreads.add(new progThread(j, i, i + border, arr));
            }
        }
        for (progThread curThread : arrayOfThreads) {
            curThread.start();
        }
        for (progThread curThread : arrayOfThreads) {
            curThread.join();
        }

        System.out.println("Sum by threads: " + sumOfThreads);
    }
}
