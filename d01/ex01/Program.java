import java.util.UUID;


public class Program {
	public static void main(String[] args) {
		User ivan = new User("Ivan", 100);
		User vova = new User("Vova", 200);
		User petr = new User("Petr", 200);

		System.out.println(ivan.getName() + " " + ivan.getId());
		System.out.println(vova.getName() + " " + vova.getId());
		System.out.println(petr.getName() + " " + petr.getId());
	}
}
