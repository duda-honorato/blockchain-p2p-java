public class Transaction {
    public String sender;
    public String receiver;
    public int amount;

    public Transaction(String sender, String receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String toString() {
        return sender + " -> " + receiver + " (" + amount + ")";
    }
}
