import com.google.gson.Gson;
import java.util.*;

public class Node extends Thread {
    private ServerSocket server;
    private Blockchain blockchain;
    private List<String> peers = new ArrayList<>();
    private Gson gson = new Gson();

    public Node(Blockchain blockchain, int port) throws IOException {
        this.server = new ServerSocket(port);
        this.blockchain = blockchain;
    }

    public void addPeer(String ipPort) {
        if (!peers.contains(ipPort)) {
            peers.add(ipPort);
        }
    }

    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    handleMessage(line);
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String ip, int port, String message) {
        try {
            Socket socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Envia mensagem para todos os peers
    public void broadcast(String message) {
        for (String peer : peers) {
            String[] parts = peer.split(":");
            sendMessage(parts[0], Integer.parseInt(parts[1]), message);
        }
    }

    private void handleMessage(String msg) {
        if (msg.startsWith("BLOCK|")) {
            Block received = gson.fromJson(msg.substring(6), Block.class);
            boolean added = blockchain.addBlock(received);
            System.out.println("Bloco recebido. Adicionado? " + added);
        } else if (msg.startsWith("TX|")) {
            Transaction tx = gson.fromJson(msg.substring(3), Transaction.class);
            blockchain.addTransaction(tx);
            System.out.println("Transação recebida: " + tx);
        } else {
            System.out.println("Mensagem desconhecida: " + msg);
        }
    }
}
