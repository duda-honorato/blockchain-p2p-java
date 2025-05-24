import java.util.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Uso: java Main <MINHA_PORTA> <DESTINOS> <MODO>");
            System.out.println("Ex: java Main 5000 10.147.18.12:5001,10.147.18.13:5002 miner");
            return;
        }

        int minhaPorta = Integer.parseInt(args[0]);
        String[] peers = args[1].split(",");
        String modo = args[2]; // "miner" ou "client"

        Blockchain blockchain = new Blockchain();
        Node node = new Node(blockchain, minhaPorta);
        for (String peer : peers) node.addPeer(peer);
        node.start();

        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        if (modo.equals("client")) {
            while (true) {
                System.out.print("Nova transação (formato: A,B,50): ");
                String[] partes = scanner.nextLine().split(",");
                Transaction tx = new Transaction(partes[0], partes[1], Integer.parseInt(partes[2]));
                blockchain.addTransaction(tx);
                node.broadcast("TX|" + gson.toJson(tx));
            }
        } else if (modo.equals("miner")) {
            while (true) {
                System.out.println("Minerando transações pendentes...");
                blockchain.minePendingTransactions();
                Block novo = blockchain.getLatestBlock();
                node.broadcast("BLOCK|" + gson.toJson(novo));
                blockchain.printChain();
            }
        }
    }
}
