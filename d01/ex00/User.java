
public class User {
	
	private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name,  Integer balance) {
        this.identifier = identifier;
		this.name = name;
		if (balance < 0)
			this.balance = 0;
        else
			this.balance = balance;
    }
    public int getIdentifier(){
        return (identifier);
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
