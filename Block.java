import java.util.*;

public class Block {
    public int index;
    public long timestamp;
    public List<Transaction> transactions;
    public String previousHash;
    public String hash;
    public int nonce;

    public Block(int index, List<Transaction> transactions, String previousHash) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = computeHash();
    }

    public String computeHash() {
        return Utils.applySha256(index + Long.toString(timestamp) + transactions.toString() + previousHash + nonce);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = computeHash();
        }
        System.out.println("Block mined: " + hash);
    }
}
