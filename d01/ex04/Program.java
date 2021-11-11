import java.util.UUID;


public class Program {
	public static void main(String[] args) {
		TransactionsService ts = new TransactionsService();
		System.out.println(ts.addUser("Jonh", 777));
		System.out.println(ts.addUser("Mike", 100));
		try{
			ts.transfer(1, 2, 100);
			System.out.println("The transfer is completed");
			ts.transfer(1, 2, 150);
			System.out.println("The transfer is completed");
			ts.transfer(1, 2, 50);
			System.out.println("The transfer is completed");
		}catch(Exception e){
			System.out.println("GG");
		}
		System.out.println(ts.balance(2));
		Transaction[] arr = ts.getTransfers(1);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		arr = ts.getTransfers(2);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		
		ts.rmTransfer(2, arr[2].getIdentifier());
		System.out.println("\n");
		arr = ts.getTransfers(2);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		System.out.println("\n");
		arr = ts.getTransfers(1);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		System.out.println("\n");
		arr = ts.getBad();
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		
		arr = ts.getTransfers(1);
		ts.rmTransfer(1, arr[2].getIdentifier());
		System.out.println("\n");
		arr = ts.getTransfers(2);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		System.out.println("\n");
		arr = ts.getTransfers(1);
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
		System.out.println("\n");
		arr = ts.getBad();
		for (int i = 0; i < arr.length; ++i)
			System.out.println(arr[i].getAmount() + " " + arr[i].getIdentifier());
	}
}
