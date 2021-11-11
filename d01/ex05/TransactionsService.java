import java.util.UUID;

public class TransactionsService {
	public TransactionsList trlist;
	public UsersList userlst;

	public TransactionsService()
	{
		this.trlist = new TransactionsLinkedList();
		this.userlst = new UsersArrayList();
	}
	public int addUser(String name,  Integer balance)
	{
		User u = new User(name, balance);
		this.userlst.addUser(u);
		return(u.getId());
	}
	public int balance(int id) 
	{
		try{
			return (this.userlst.user_byId(id).getBalance());
		}catch(Exception e){
			return (0);
		}
	}
	public void transfer(int id_recipient, int id_sender, int amount) throws IllegalTransactionException
	{
		try{
			User recipient = this.userlst.user_byId(id_recipient);
			User sender = this.userlst.user_byId(id_sender);
			UUID id_tr = UUID.randomUUID();
			Transaction tr_d = new Transaction(id_tr, recipient, sender, -amount);
			Transaction tr_c = new Transaction(id_tr, recipient, sender, amount);
			recipient.setBalance(recipient.getBalance()-amount);
			recipient.transactions.addTrans(tr_d);
			sender.setBalance(sender.getBalance()+amount);
			sender.transactions.addTrans(tr_c);
			if (amount < 0 || sender.getBalance() < 0 || recipient.getBalance() < 0)
				throw new IllegalTransactionException();
		}
			catch(Exception e){
				throw new IllegalTransactionException();
		}
	}
	public Transaction[] getTransfers(int id)
	{	try{
			return(this.userlst.user_byId(id).transactions.toArray());
		}
		catch(Exception e){
		return (null);
	}
	}
	public void rmTransfer(int id_user, String id_tr){
		try{
		User u = this.userlst.user_byId(id_user);
		Transaction tr = u.transactions.findTrans(id_tr);
		if (tr != null)
		{
			if (this.trlist.findTrans(tr.getIdentifier()) != null)
				this.trlist.rmTrans(id_tr);
			else
				this.trlist.addTrans(new Transaction(tr));
			u.transactions.rmTrans(id_tr);
		}
		}
		catch(Exception e){
			return;
		}
	}
	public Transaction[] getBad(){
		return(this.trlist.toArray());
	}
}
