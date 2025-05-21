//implementa um no da rede blockchain, capaz de relacionar as conexoes e msg a outros nos
import java.net.*;
import java.io.*;

public class Node extends Thread {
    private ServerSocket server;
    private Blockchain blockchain; // ref - blockchain

    public Node(Blockchain blockchain, int port) throws IOException {
        this.server = new ServerSocket(port);
        this.blockchain = blockchain;
    }

    //fica aceitando as conexoes e lendo msg
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Mensagem recebida: " + line);
                    // lógica de sincronização futura
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //encia msg para outros nos
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
}
