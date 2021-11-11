import java.util.Scanner;

public class Menu {
	private TransactionsService ts = new TransactionsService();
	public go(int dev)
	{
		while(true)
		{
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		if (dev == 1)
		{
			System.out.println("5. DEV - remove a transfer by ID");
			System.out.println("6. DEV - check transfer validity");
		}
		System.out.println("7. Finish execution");
		String cmd = scanner.next();
		
		if (cmd.equals("1"))
		{
			System.out.println("Enter a user name and a balance");
			String name = scanner.next();
			int b = scanner.nextInt();
			System.out.println("User with id = " + ts.addUser(name, b) + " is added");
		}
		else if (cmd.equals("2"))
		{
			System.out.println("Enter a user ID");
			int b = scanner.nextInt();
			System.out.println(ts.userlst.getId(b).getName() + " - " + ts.balance(b));

		}else if (cmd.equals("3"))
		{
			System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			inc c = scanner.nextInt();
			try{
				ts.transfer(a, b, c);
				System.out.println("The transfer is completed");
			}catch(Exception e){
				System.out.println("Error");
			}

		}else if (cmd.equals("4"))
		{
			System.out.println("Enter a user ID");
			int a = scanner.nextInt();
			Transaction[] arr = ts.getTransfers(a);
			for (int i = 0; i < arr.length; ++i)
				System.out.println("To " + arr[i].getRecipient().getName() + " " + 
				arr[i].getRecipient().getId() + " " + arr[i].getAmount() + " " + arr[i].getIdentifier());

		}
		else if (cmd.equals("7"))
		{
			break ;
		}
		}
	}
}
