package org.todeschini.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

public class MyServer {
    public static void main(String[] args) {
        System.out.println("Start server at " + LocalDateTime.now());

        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress("127.0.0.1", 5000));

            while (true) {
                System.out.println("Waiting a request " + LocalDateTime.now());
                SocketChannel socket = channel.accept();

                if (socket != null) {
                    String msg = "MSG recive at " + LocalDateTime.now();
                    ByteBuffer buffer = ByteBuffer.allocate(64);
                    buffer.put(msg.getBytes());
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        socket.write(buffer);
                    }
                    System.out.println("Sent mesg at " + LocalDateTime.now());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
