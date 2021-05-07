import java.io.IOException;
import java.util.Vector;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Vector<Thread> threads = new Vector<Thread>();
        Runnable server = () -> {
            try {
                new Server("localhost", 8090).startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Runnable client = () -> {
            try {
                new Client().startClient();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread serverThread = new Thread(server);
        serverThread.setDaemon(true);
        serverThread.start();

        for(int i = 0; i < 10; i++) {
            threads.add(new Thread(client, "client-" + String.valueOf(i+1)));
            threads.get(i).start();
        }

        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
    }
}
