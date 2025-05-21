//Representa uma transação entre dois participantes
public class Transaction {
    public String sender;
    public String receiver;
    public int amount;

    public Transaction(String sender, String receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    //representação da transação, usada para gerar o hash do bloco
    public String toString() {
        return sender + " -> " + receiver + " (" + amount + ")";
    }
}
