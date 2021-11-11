package ex02;
import java.util.Scanner;
public class Program {
	public static int summ(int n){
		int sum = 0;
		while (n > 0)
		{
			sum = sum + n % 10;
			n /= 10;
		}
		return (sum);
	}
	public static boolean prim(int n){
		int i = 2;
		for(; i * i <= n && n % i != 0; ++i){}
			return ((boolean)(i * i > n));
	}
	public static void main(String []args) {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		int number = scanner.nextInt();
		while (number != 42)
		{
			if (prim(summ(number)))
				count++;
			number = scanner.nextInt();
		}
		scanner.close();
		System.out.println("Count of coffee-request - " + count);
		System.exit(1);
	}
}
