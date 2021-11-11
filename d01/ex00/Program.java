import java.util.UUID;


public class Program {
	public static void main(String[] args) {
		User ivan = new User(1, "Ivan", 100);
		User vova = new User(2, "Vova", 200);

		System.out.println(ivan.getName() + " " + ivan.getBalance());
		System.out.println(vova.getName() + " " + vova.getBalance());

		Transaction t1 = new Transaction(UUID.randomUUID(), ivan, vova, "debits", 50);

		System.out.println(ivan.getName() + " " + ivan.getBalance());
		System.out.println(vova.getName() + " " + vova.getBalance());

		Transaction t2 = new Transaction(UUID.randomUUID(), ivan, vova, "credits", -100);

		System.out.println(ivan.getName() + " " + ivan.getBalance());
		System.out.println(vova.getName() + " " + vova.getBalance());
	}
}
