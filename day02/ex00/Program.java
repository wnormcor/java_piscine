import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Program {
    static String buf;
    static HashMap<String, String> signatures = new HashMap<>();

    private static final String litHexCodes = "0123456789ABCDEF";

    public static boolean getUserSelectedFile() {
        Scanner input = new Scanner(System.in);
        String requestFile = input.nextLine();
        if (requestFile.equals("42")) {
            return false;
        }
        try {
            FileInputStream sigfis = new FileInputStream(requestFile);
            byte[] significationInFile = new byte[7];
            sigfis.read(significationInFile);
            sigfis.close();
            buf = getHex(significationInFile);
            return true;
        } catch (Exception e) {
            System.err.println("Can't read file " + requestFile);
        }
        return false;
    }

    public static void loadSignaturesTxt(FileInputStream inputFile) {
        try {
            Scanner input = new Scanner(inputFile);
            String lineOfSignature;
            String extension;
            String signatureOfExtension;

            while (input.hasNextLine()) {
                lineOfSignature = input.nextLine();
                extension = lineOfSignature.substring(0, lineOfSignature.indexOf(','));
                signatureOfExtension = lineOfSignature.substring(lineOfSignature.indexOf(',') + 1);
                signatureOfExtension = signatureOfExtension.replaceAll("\\s", "");
                signatures.put(extension, signatureOfExtension);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean findSignature() {
        for (Map.Entry<String, String> pair : signatures.entrySet()) {
            if (buf.lastIndexOf(pair.getValue()) != -1) {
                buf = pair.getKey();
                return true;
            }
        }
        return false;
    }

    public static String getHex(byte[] inputBytes) {
        final StringBuilder hex = new StringBuilder(2 * inputBytes.length);
        for (final byte b : inputBytes) {
            hex.append(litHexCodes.charAt((b & 0xF0) >> 4)).append(litHexCodes.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static void writeResult(OutputStream outputFile) {
        if (findSignature()) {
            try {
                outputFile.write(buf.getBytes());
                outputFile.write('\n');
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        try {
            FileInputStream inputFile = new FileInputStream("signatures.txt");
            OutputStream outputFile = new FileOutputStream("result.txt");
            while (getUserSelectedFile()) {
                System.out.println("PROCESSED");
                loadSignaturesTxt(inputFile);
                writeResult(outputFile);
            }
        } catch (Exception e) {
            System.err.println("Can't open result.txt/signatures.txt files");
            System.exit(-1);
        }

    }
}
