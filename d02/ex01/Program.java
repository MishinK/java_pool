import java.io.*;
import java.util.*;

import static java.lang.Math.sqrt;

public class Program {
    public static ArrayList<String> getWords(String fileName) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        int i;
        String tmpLine = "";
        while((i = fileInputStream.read()) != -1){
            if (((char)i == ' ' || (char)i == '\n') && tmpLine.trim().length() > 0) {
                words.add(tmpLine.trim());
                tmpLine = "";
            } else 
                tmpLine += (char) i;
        }
        if (tmpLine.trim().length() > 0) {
            words.add(tmpLine.trim());
        }
        fileInputStream.close();
        return words;
    }

    public static ArrayList<Integer> getVector(TreeSet<String> dictionary, ArrayList<String> words) {
        ArrayList<Integer> v = new ArrayList<>();
        dictionary.forEach((word) -> {
            v.add((int)words.stream().filter(w -> w.equals(word)).count());
        });
        return (v);
    }

    public static double getNumerator(ArrayList<Integer> firstVector,ArrayList<Integer> secondVector) {
        double numerator = 0;
        for (int i = 0; i < firstVector.size(); i++)
            numerator += firstVector.get(i) * secondVector.get(i);
        return (numerator);
    }

    public static double getDenominator(ArrayList<Integer> firstVector,ArrayList<Integer> secondVector) {
        return (sqrt(getNumerator(firstVector, firstVector) * getNumerator(secondVector, secondVector)));
    }

    public static void saveDict(TreeSet<String> dictionary) throws IOException {
        File file = new File("dictionary.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
		dictionary.forEach((word) -> {
            try {
                fileOutputStream.write((word + "\n").getBytes());
            } catch (IOException e) {
                System.err.println("Error: write");
                System.exit(-1);
            }
        });
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("error: 2 arg expected");
            System.exit(-1);
        }
        ArrayList<String> firstListWords = getWords(args[0]);
        ArrayList<String> secondListWords = getWords(args[1]);
        TreeSet<String> dictionary = new TreeSet<>();
		dictionary.addAll(firstListWords);
        dictionary.addAll(secondListWords);
		saveDict(dictionary);
        if (firstListWords.size() == 0 || secondListWords.size() == 0) {
            System.out.printf("Similarity = %.2f\n", 0);
        }
        else {
			ArrayList<Integer> firstVector = getVector(dictionary, firstListWords);
        	ArrayList<Integer> secondVector = getVector(dictionary, secondListWords);
        	double numerator = getNumerator(firstVector, secondVector);
        	double denominator = getDenominator(firstVector, secondVector);
        	System.out.printf("Similarity = %.2f\n", numerator / denominator);
		}
    }
}