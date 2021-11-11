package ex03;
import java.util.Scanner;
public class Program {
	public static void print_answer(long answer, int idx)
	{
		if (answer == 0)
			return ;
		long n = answer % 10;
		print_answer(answer / 10, idx - 1);
		System.out.print("Week ");
		System.out.print(idx);
		System.out.print(" ");
		while(n > 0)
		{
			System.out.print("=");
			n--;
		}
		System.out.println(">");
	}
	public static void main(String []args) {
		Scanner scanner = new Scanner(System.in);
		long answer = 0;
		int n;
		int min;
		String str = scanner.next();
		if (str.equals("42"))
		{
			scanner.close();
			System.exit(1);
		}
		if (!str.equals("Week"))
		{
			System.err.println("Illegal Argument");
			scanner.close();
			System.exit(-1);
		}
		int week_idx = scanner.nextInt();
		int idx = 1;
		if (week_idx != idx)
		{
			System.err.println("Illegal Argument");
			scanner.close();
			System.exit(-1);
		}
		while (week_idx < 19)
		{
			min = 10;
			for(int i = 0; i < 5; i++)
			{
				n = scanner.nextInt();
				if (n < 1 || n > 9)
				{
					System.err.println("Illegal Argument");
					scanner.close();
					System.exit(-1);
				}
				if (n < min)
					min = n;
			}
			answer = answer * 10 + min;
			if (week_idx == 18)
				break ;
			str = scanner.next();
			if (str.equals("42"))
				break;
			if (!str.equals("Week"))
			{
				System.err.println("Illegal Argument");
				scanner.close();
				System.exit(-1);
			}
			week_idx = scanner.nextInt();
			idx++;
			if (week_idx != idx)
			{
				System.err.println("Illegal Argument");
				scanner.close();
				System.exit(-1);
			}
		}
		scanner.close();
		print_answer(answer, week_idx);
		System.exit(1);
	}
}
