//Mantém toda a cadeia de blocos e gerencia as transações
import java.util.*;

public class Blockchain {
    public List<Block> chain; //lista - blocos
    public List<Transaction> pendingTransactions; //transacoes q n foram mineradas
    public int difficulty = 4; //dificuldade - mineracao

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        this.chain.add(createGenesisBlock());
    }
    //cria o primeiro
    private Block createGenesisBlock() {
        return new Block(0, new ArrayList<>(), "0");
    }
    //ultimo bloco
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }
    //adiciona transacoes pendnetes
    public void addTransaction(Transaction tx) {
        pendingTransactions.add(tx);
    }
    //cria um bloco com as transações pendentes, minera e adiciona a cadeia
    public void minePendingTransactions() {
        Block block = new Block(chain.size(), new ArrayList<>(pendingTransactions), getLatestBlock().hash);
        block.mineBlock(difficulty);
        chain.add(block);
        pendingTransactions.clear();
    }
    //valida se ta integra
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block curr = chain.get(i);
            Block prev = chain.get(i - 1);

            if (!curr.hash.equals(curr.computeHash())) return false;
            if (!curr.previousHash.equals(prev.hash)) return false;
        }
        return true;
    }
    //printa o resumo da blockchain
    public void printChain() {
        for (Block b : chain) {
            System.out.println("Index: " + b.index + ", Hash: " + b.hash + ", Tx: " + b.transactions.size());
        }
    }
}
