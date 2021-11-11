
public class User {
	
	private Integer id = UserIdsGenerator.getInstance().generateId();
    private String name;
    private Integer balance;

    public User(String name,  Integer balance) {
		this.name = name;
		if (balance < 0)
			this.balance = 0;
        else
			this.balance = balance;
    }
    public int getId(){
        return (id);
    }
	public String getName(){
        return (name);
    }
    public int getBalance(){
        return (balance);
    }
	public void setBalance(Integer balance){
		this.balance = balance;
    }
}
