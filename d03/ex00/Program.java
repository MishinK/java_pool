public class Program {
	public static void print(String name, int count)
	{
		for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
	}
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("error: 1 arg expected");
            System.exit(-1);
        }
        String[] argForParse = args[0].split("=");
        if (argForParse.length != 2 || !argForParse[0].equals("--count")) {
            System.err.println("expected: Program --count=<number>");
            System.exit(-1);
        }
        int count = Integer.parseInt(argForParse[1]);
        Thread egg = new Thread(() -> print("Egg", count));
		Thread hen = new Thread(() -> print("Hen", count));
		egg.start();
        hen.start();
        egg.join();
        hen.join();
        print("Human", count);
    }
}