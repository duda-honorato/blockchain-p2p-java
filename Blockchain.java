import java.util.*;

public class Blockchain {
    public List<Block> chain;
    public List<Transaction> pendingTransactions;
    public int difficulty = 4;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        this.chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, new ArrayList<>(), "0");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addTransaction(Transaction tx) {
        pendingTransactions.add(tx);
    }

    public void minePendingTransactions() {
        Block block = new Block(chain.size(), new ArrayList<>(pendingTransactions), getLatestBlock().hash);
        block.mineBlock(difficulty);
        chain.add(block);
        pendingTransactions.clear();
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block curr = chain.get(i);
            Block prev = chain.get(i - 1);

            if (!curr.hash.equals(curr.computeHash())) return false;
            if (!curr.previousHash.equals(prev.hash)) return false;
        }
        return true;
    }

    public void printChain() {
        for (Block b : chain) {
            System.out.println("Index: " + b.index + ", Hash: " + b.hash + ", Tx: " + b.transactions.size());
        }
    }
}
