
public interface TransactionsList {
    public void addTrans(Transaction trans);
    public void rmTrans(String id) throws TransactionNotFoundException;
    public Transaction[] toArray();
}
