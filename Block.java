//Representa um bloco da blockchain
import java.util.*;

public class Block {
    public int index; //posicao
    public long timestamp; //Qd criado
    public List<Transaction> transactions; //Transacoes dentro do bloco
    public String previousHash; //hash - bloco anterior
    public String hash; //hash - atual
    public int nonce; //valor - mineracao

    public Block(int index, List<Transaction> transactions, String previousHash) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = computeHash();
    }

    public String computeHash() {
        //SHA-256 HashCriptografado
        return Utils.applySha256(index + Long.toString(timestamp) + transactions.toString() + previousHash + nonce);
        //Ex sem SHA-256 Sem SHA-256: {1}{1716318612345}{[Alice -> Bob (10)]}{00000abc123}{3215}

    }

    //Realiza a mineração tentando encontrar um hash que comece com difficulty zeros 
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = computeHash();
        }
        System.out.println("Block mined: " + hash);
    }
}
