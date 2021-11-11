
import java.util.UUID;


public class Transaction {
	private String identifier;
    private User recipient;
    private User sender;
    private String category;
    private Integer amount;

    public Transaction(UUID id, User recipient, User sender, Integer amount) {
        this.identifier = id.toString();
		this.recipient = recipient;
		this.sender = sender;
		if (amount > 0)
			this.category = "debits";
		else
			this.category = "credits";
		this.amount = amount;
    }
	public Transaction(Transaction other)
	{
		this.identifier = other.getIdentifier();
		this.recipient = other.getRecipient();
		this.sender = other.getSender();
		this.category = other.getCategory();
		this.amount = other.getAmount();
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

