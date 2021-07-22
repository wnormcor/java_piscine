import java.io.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.util.Scanner;
import java.util.Arrays;

public class Program {
    static Vector<String> dictionary = new Vector<String>();
    private static Integer vecTextOne[];
    private static Integer vecTextTwo[];

    public static void addWordInDict(FileInputStream inputFile) {
        Scanner input = new Scanner(inputFile);
        String readLine;
        while (input.hasNextLine()) {
            readLine = input.nextLine();
            readLine = readLine.replaceAll("\\p{Punct}", "");
            readLine = readLine.toLowerCase();
            String[] wordInReadLine = readLine.split("\\s+", 0);
            for (String word : wordInReadLine) {
                if (!dictionary.contains(word) && word != "") {
                    dictionary.add(word);
                }
            }
        }
    }

    public static void fillTextVector(FileInputStream inputFile, Integer [] vecTest) {
        Scanner input = new Scanner(inputFile);
        String readLine;
        while (input.hasNextLine()) {
            readLine = input.nextLine();
            readLine = readLine.replaceAll("\\p{Punct}", "");
            readLine = readLine.toLowerCase();
            String[] wordInReadLine = readLine.split("\\s+", 0);
            for (String word : wordInReadLine) {
                Integer index = dictionary.indexOf(word);
                vecTest[index] += 1;
            }
        }
    }

    public static void main(String[] args) {
        if (2 != args.length) {
            System.err.println("use: Program fileA.txt fileB.txt");
            System.exit(-1);
        }
        for (int i = 0; i < 2; i++) {
            File file = new File(args[i]);
            if (!file.exists()) {
                System.err.println("Could not open file '" + args[i] + "'. Exit");
                System.exit(-1);
            }
        }
        try {
            FileInputStream fileA = new FileInputStream(args[0]);
            FileInputStream fileB = new FileInputStream(args[1]);
            OutputStream fileDict = new FileOutputStream("dictionary.txt");
            addWordInDict(fileA);
            addWordInDict(fileB);
            fileA.close();
            fileB.close();
            fileA = new FileInputStream(args[0]);
            fileB = new FileInputStream(args[1]);
            Integer lenOfDict = dictionary.size();
            vecTextOne = new Integer[lenOfDict];
            Arrays.fill(vecTextOne, 0);
            fillTextVector(fileA, vecTextOne);
            vecTextTwo = new Integer[lenOfDict];
            Arrays.fill(vecTextTwo, 0);
            fillTextVector(fileB, vecTextTwo);
            Integer Numerator = 0;
            Integer DenominatorA = 0;
            Integer DenominatorB = 0;
            for (int i = 0; i < lenOfDict; i++) {
                Numerator += vecTextOne[i] * vecTextTwo[i];
                DenominatorA += vecTextOne[i] * vecTextOne[i];
                DenominatorB += vecTextTwo[i] * vecTextTwo[i];
            }
            Double similarity = Numerator / (Math.sqrt(DenominatorA) * Math.sqrt(DenominatorB));
            DecimalFormat f = new DecimalFormat("##.00");
            System.out.println("Similarity = " + Math.floor(similarity * 100) / 100.0);
            for (String word : dictionary) {
                fileDict.write(word.getBytes());
                fileDict.write('\n');
            }
        } catch (Exception e) {
            System.err.println("Could write dict 'dictionary.txt' Exit");
            System.exit(-1);
        }
    }
}
