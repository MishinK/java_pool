package ex04;
import java.util.Scanner;

public class Program {
	public static void main(String []args) {
		Scanner scanner = new Scanner(System.in);
		char[] arr = scanner.nextLine().toCharArray();
		short[] c = new short[65535];
		for(int i = 0; i < arr.length; i++)
			c[arr[i] - 1]++;
		int[][] answer = new int[12][10];
		for (int k = 0; k < 10; k++)
		{
			short max = 0;
			int idx = -1;
			for(int i = 0; i < c.length; i++)
				if (max < c[i])
				{
					max = c[i];
					idx = i;
				}
			if (idx != -1)
			{
				c[idx] = 0;
				
				if (k == 0)
					answer[0][k] = max;
				answer[11][k] = (idx + 1);
				for (int j = 10; j > 0; j--)
				{
					if (max * 10 >= (11 - j) * answer[0][0])
						answer[j][k] = -1;
					else
					{
						answer[j][k] = max;
						break ;
					}
				}
				if (answer[1][k] == -1)
					answer[0][k] = max;
			}
			else 
				break ;
		}
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 10; ++j)
			{
				if (answer[i][j] == 0 )
					break ;
				if (i == 11)
				{
					System.out.print("   ");
					System.out.print((char)answer[i][j]);
				}
				else
				{
					if (answer[i][j] == -1)
						System.out.print("   #");
					else
					{
						if (answer[i][j] < 10)
							System.out.print("   ");
						else if (answer[i][j] < 100)
							System.out.print("  ");
						else 
						System.out.print(" ");
						System.out.print(answer[i][j]);
					}
				}
			}
			System.out.print("\n");
		}
		scanner.close();
		System.exit(1);
	}
}
