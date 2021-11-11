public class Program {
	public static void print(String name, int count, Say say)
	{
		for (int i = 0; i < count; i++) {
			try{
				if (name.equals("Egg"))
					say.getEgg();
				else
					say.getHen();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
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
        Say say = new Say();
		Thread egg = new Thread(() -> print("Egg", count, say));
		Thread hen = new Thread(() -> print("Hen", count, say));
		egg.start();
        hen.start();
    }
}