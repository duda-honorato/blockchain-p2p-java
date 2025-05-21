public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain bc = new Blockchain();

        Node node = new Node(bc, 5000); // Porta do servidor
        node.start();

        // Exemplo de transação e mineração
        bc.addTransaction(new Transaction("Alice", "Bob", 10));
        bc.minePendingTransactions();
        bc.printChain();

        // Exemplo de envio de mensagem
        // node.sendMessage("192.168.x.x", 5000, "Hello, I'm alive");
    }
}
