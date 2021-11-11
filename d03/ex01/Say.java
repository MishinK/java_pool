import java.awt.List;

public class Say<String> {
	private static int i = 0;
	
    public synchronized void getEgg() throws InterruptedException{

        while (i % 2 == 1)
			wait();
		i++;
		System.out.println("Egg");
        notify();
    }

    public synchronized void getHen()  throws InterruptedException {

		while (i % 2 == 0)
			wait();
		i--;
		System.out.println("Hen");
        notify();
    }
}