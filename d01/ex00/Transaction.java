
import java.util.UUID;


public class Transaction {
	private String identifier;
    private User recipient;
    private User sender;
    private String category;
    private Integer amount;

    public Transaction(UUID id, User recipient, User sender, String category, Integer amount) {
        this.identifier = id.toString();
		this.recipient = recipient;
		this.sender = sender;
		this.category = category;
		if ((category.equals("debits") && amount > 0) || (category.equals("credits") && amount < 0))
		{
			this.amount = amount;
			recipient.setBalance(recipient.getBalance()+amount);
			sender.setBalance(sender.getBalance()-amount);	
		}
		else
			this.amount = 0;
    }
	public String getIdentifier(){
		return (this.identifier);
	}
	public User getRecipient(){
		return (this.recipient);
	}
	public User getSender(){
		return (this.sender);
	}
	public String getCategory(){
		return (this.category);
	}
	public Integer getAmount(){
		return (this.amount);
	}
}

