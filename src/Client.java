//package examples.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//example taken from: http://examples.javacodegeeks.com/core-java/nio/java-nio-socket-example/

public class Client {

    public void startClient() throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        try (SocketChannel client = SocketChannel.open(hostAddress)) {
            System.out.println("Client... started");
            String threadName = Thread.currentThread().getName();

            // Send messages to server
            String[] messages = new String[]{threadName + ": Hi guy", threadName + ": Hi tom", threadName + ": bye"};

            for (String message1 : messages) {
                byte[] message = message1.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(message);
                //sendBuff(client, buffer);
                client.write(buffer);
                Thread.sleep(10);
                //System.out.println(message1);
                buffer.clear();
            }
        }
    }
    private synchronized void sendBuff(SocketChannel client, ByteBuffer buffer) throws IOException {
        client.write(buffer);
    }
}