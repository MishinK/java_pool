public class Program {
	public static void main(String[] args) {
		User ivan1 = new User("Ivan", 100);
		User vova1 = new User("Vova", 200);
		User petr1 = new User("Petr", 200);
		User ivan2 = new User("Ivan", 100);
		User vova2 = new User("Vova", 200);
		User petr2 = new User("Petr", 200);
		User ivan3 = new User("Ivan", 100);
		User vova3 = new User("Vova", 200);
		User petr3 = new User("Petr", 200);
		User ivan4 = new User("Ivan", 100);
		User vova4 = new User("Vova", 200);
		User petr4 = new User("Petr", 200);
		UsersArrayList arr = new UsersArrayList();
		arr.addUser(ivan1);
		arr.addUser(vova1);
		arr.addUser(petr1);
		arr.addUser(ivan2);
		arr.addUser(vova2);
		arr.addUser(petr2);
		arr.addUser(ivan3);
		arr.addUser(vova3);
		arr.addUser(petr3);
		arr.addUser(ivan4);
		arr.addUser(vova4);
		arr.addUser(petr4);
		System.out.println(arr.number_users());
		System.out.println(arr.user_byId(1).getId());
		System.out.println(arr.user_byId(12).getId());
		System.out.println(arr.user_byIdx(0).getId());
		System.out.println(arr.user_byIdx(11).getId());
		System.out.println(arr.user_byId(13).getId());
		
	}
}
