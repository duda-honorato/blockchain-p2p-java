import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java Main <IP_DESTINO> <PORTA>");
            return;
        }

        String destinoIP = args[0];
        int porta = Integer.parseInt(args[1]);
        int minhaPorta = 5000; // Porta local para escutar

        Blockchain blockchain = new Blockchain();
        Node node = new Node(blockchain, minhaPorta);
        node.start(); // Inicia o servidor que escuta

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite mensagens para enviar para o outro nó:");

        while (true) {
            String msg = scanner.nextLine();
            node.sendMessage(destinoIP, porta, msg);
        }
    }
}
