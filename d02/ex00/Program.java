import java.io.*;
import java.util.*;

public class Program {
    public static String intToHex(int n) {
        String digits = "0123456789ABCDEF";
        String hex = "";
        if (n < 1)
            return ("0");
        while (n > 0) {
            int digit = n % 16;
            n = n / 16;
			hex = digits.charAt(digit) + hex;
        }
        return (hex);
    }
    public static Map<String, String> getSignature() throws IOException {
        Map<String, String> signature = new LinkedHashMap<>();
        File file = new File("signatures.txt");
        try {
			FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
			String line = "";
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				line += (char) i;
				if ((char) i == '\n') {
					String[] tmp = line.split(",");
					signature.put(tmp[1].trim().substring(0, 11), tmp[0]);
					line = "";
				}
			}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return (signature);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> signature = getSignature();
		try {
			File file = new File("result.txt");
			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        	for (String fileName = scanner.nextLine(); !fileName.equals("42"); fileName = scanner.nextLine()) {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                String fileType = intToHex(fileInputStream.read()) + " " + intToHex(fileInputStream.read()) + " " + intToHex(fileInputStream.read()) + " " + intToHex(fileInputStream.read());
                fileInputStream.close();
                String resultType = signature.get(fileType);
                if (resultType != null){
                    fileOutputStream.write((resultType + "\n").getBytes());
					System.out.println("PROCESSED");
                } else
                    System.out.println("UNDEFINED");
        	}
			fileOutputStream.close();
		}catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
		scanner.close();
    }
}