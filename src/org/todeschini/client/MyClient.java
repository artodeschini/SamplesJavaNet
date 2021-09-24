package org.todeschini.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient {
    public static void main(String[] args) {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
        try (SocketChannel channel = SocketChannel.open(address)) {
            ByteBuffer buffer = ByteBuffer.allocate(64);
            int read = channel.read(buffer);

            while (read > 0 ) {
                buffer.flip();
                while ( buffer.hasRemaining()) {
                    System.out.println( (char) buffer.get() + "\n");
                }
                read = channel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
