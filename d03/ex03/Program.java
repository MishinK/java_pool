import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.channels.*;


public class Program {

	public static List<Package> readFile(String path) {
        List<Package> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                String[] split = br.readLine().trim().split(" ");
                if (split.length == 2)
					list.add(new Package(Integer.parseInt(split[0]), new URL(split[1])));
            }
        } catch (IOException e) {
            return (list);
		}
        return (list);
    }

	public static void download(List<Package> list, int size, int idx)
	{
		for (int i = 0; i < size; )
		{
			if (list.get(i).getStatus() == 0)
			{
				list.get(i).setStatus();
				System.out.printf("Thread-%d start download file number %d\n", idx, i + 1);
				URL url = list.get(i).getUrl();
				String[] split = url.toString().split("/");
				String name = split[split.length - 1];
				try (InputStream in = url.openStream(); FileOutputStream fos = new FileOutputStream(name))
				{
            		ReadableByteChannel rbc = Channels.newChannel(in);
            		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					System.out.printf("Thread-%d finish download file number %d\n", idx, i + 1);
        		} catch (IOException e) {
					e.printStackTrace();
        		}
			}
			i++;
		}
	}
	
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("error: 1 arg expected");
            System.exit(-1);
        }
        String[] argForParse = args[0].split("=");
        if (argForParse.length != 2 || !argForParse[0].equals("--threadsCount")) {
            System.err.println("expected: Program --threadsCount=<number>");
            System.exit(-1);
        }
        int count = Integer.parseInt(argForParse[1]);
		if (count < 1)
		{
			System.err.println("Bad value");
            System.exit(-1);
		}
		List<Package> list = readFile("files_urls.txt");
        if (list.size() < 1) {
            System.err.println("no files_urls.txt");
            System.exit(-1);
        }
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i  < count; i++) {
			int idx = i + 1;
            threads.add(new Thread(() -> download(list, list.size(), idx)));
        }
		for (Thread th : threads)
			th.start();
		for (Thread th : threads)
			th.join();
    }
}