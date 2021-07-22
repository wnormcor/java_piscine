import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Config config = new Config();
        int urlId = 1;
        List<FileLoader> threads = new ArrayList<>();

        if (config.initialize(args)) {
            LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

            try {
                map = getUrlsFromFile();
            } catch (IOException e) {
                System.out.println("IOException. Check file with url");
            }

            while (map.containsValue(false)) {
                for (Map.Entry<String, Boolean> pair : map.entrySet()) {
                    boolean isFind = false;
                    if (!pair.getValue()) {
                        for (int i = 0; i < threads.size(); i++) {
                            if (threads.get(i).isFree()) {
                                threads.get(i).setNewTask(pair.getKey(), urlId++);
                                pair.setValue(true);
                                isFind = true;
                                break;
                            }
                        }
                        if (!isFind && threads.size() < config.getThreadCount()) {
                            FileLoader fileLoader = new FileLoader(threads.size() + 1);
                            threads.add(fileLoader);
                            fileLoader.setNewTask(pair.getKey(), urlId++);
                            fileLoader.start();
                            pair.setValue(true);

                        }
                    }
                }
            }
        }
    }

    private static LinkedHashMap<String, Boolean> getUrlsFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("urls_for_load.txt"));

        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();

        while (reader.ready()){
            String line = reader.readLine();
            String[] parse = line.split(" ");
            if (parse.length > 1) {
                map.put(parse[1], false);
            }
        }
        reader.close();

        return map;
    }

}
