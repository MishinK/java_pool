import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
	
    public static void sum_by_thread(int[] numbers, int begin, int end, int size, int count, int idx, long[] arr)
	{
		int sum = 0;
		if (idx == count)
			end = size;
		for (int i = begin; i < end; i++)
			sum += numbers[i];
		arr[idx-1] = sum;
		System.out.printf("Thread %d: from %d to %d sum is %d\n", idx, begin, end-1, sum);
	}

    public static void main(String[] args) throws InterruptedException {
		if (args.length != 2) {
            System.err.println("error: 2 arg expected");
            System.exit(-1);
        }
        String[] argForParse1 = args[0].split("=");
		String[] argForParse2 = args[1].split("=");
        if (argForParse1.length != 2 || argForParse2.length != 2 || !argForParse1[0].equals("--arraySize") || !argForParse2[0].equals("--threadsCount")) {
            System.err.println("expected: Program --arraySize=<number> --threadsCount=<number>");
            System.exit(-1);
        }
        int ArraySize = Integer.parseInt(argForParse1[1]);
        int ThreadsCount = Integer.parseInt(argForParse2[1]);
        if (ArraySize < 1 || ThreadsCount < 1 || ArraySize < ThreadsCount) {
            System.err.println("Bad value");
            System.exit(-1);
        }
        int[] numbers = new int[ArraySize];
		long sum = 0;
        for (int i = 0; i < ArraySize; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);
        ArrayList<Thread> threads = new ArrayList<>();
		long[] arr = new long[ThreadsCount];
        for (int i = 0; i  < ThreadsCount; i++) {
            int begin = i * (ArraySize / ThreadsCount);
            int end = begin + ArraySize / ThreadsCount;
			int idx = i + 1;
            threads.add(new Thread(() -> sum_by_thread(numbers, begin, end, ArraySize, ThreadsCount, idx, arr)));
        }
        for (Thread th : threads)
            th.start();
		for (Thread th : threads)
            th.join();
		sum = 0;
		for (int i = 0; i < ThreadsCount; ++i)
			sum += arr[i];
		System.out.printf("Sum by threads: %d\n", sum);
    }
}