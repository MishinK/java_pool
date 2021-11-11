package ex01;
import java.util.Scanner;
public class Program {
	public static void main(String []args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		scanner.close();
		int i = 2;
		if (number < 2)
		{
			System.err.println("Illegal Argument");
			System.exit(-1);
		}
		else
		{
			for(; i * i <= number && number % i != 0; ++i){}
			System.out.println((boolean)(i * i > number) + " " + (i - 1));
		}
		System.exit(1);
	}
}
