import java.util.UUID;


public class Program {
	public static void main(String[] args) {
		User ivan1 = new User("Ivan", 100);
		User vova1 = new User("Vova", 200);
		User petr1 = new User("Petr", 200);
		Transaction t1 = new Transaction(UUID.randomUUID(), ivan1, vova1, "debits", 50);
		Transaction t2 = new Transaction(UUID.randomUUID(), ivan1, petr1, "credits", -50);
		Transaction t3 = new Transaction(UUID.randomUUID(), vova1, petr1, "debits", 50);
		Transaction[] tarr = ivan1.transactions.toArray();
		System.out.println(tarr[0].getIdentifier());
		System.out.println(tarr[1].getIdentifier());
		tarr = vova1.transactions.toArray();
		System.out.println(tarr[0].getIdentifier());
		System.out.println(tarr[1].getIdentifier());
		tarr = petr1.transactions.toArray();
		System.out.println(tarr[0].getIdentifier());
		System.out.println(tarr[1].getIdentifier());
		petr1.transactions.rmTrans(t2.getIdentifier());
		tarr = petr1.transactions.toArray();
		System.out.println(tarr[0].getIdentifier());
		petr1.transactions.rmTrans(t2.getIdentifier());
	}
}
