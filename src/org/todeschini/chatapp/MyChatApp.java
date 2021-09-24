package org.todeschini.chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Vector;

import static java.lang.System.out;

public class MyChatApp {

    public static void main(String[] args) {
        MyChatApp app = new MyChatApp();
        try {
            app.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Vector<String> users = new Vector<>();
    Vector<HandleClient> clients = new Vector<HandleClient>();

    public void process() throws Exception {
        ServerSocket server = new ServerSocket( 9999, 10);
        out.println(LocalDateTime.now());
        out.println("Server started");

        while (true) {
            Socket client = server.accept();
            HandleClient handle = new HandleClient(client);
            clients.add(handle);
        }
    }

    public void breadcast(String user, String msg) {
        for (HandleClient c : clients) {
            if (!c.getUsername().equals(user)) {
                c.sendMessage(user, msg);
            }
        }
    }

    class HandleClient extends Thread {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket client) {

            try {

                this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                this.output = new PrintWriter(client.getOutputStream(), true);
                this.name = input.readLine();

                users.add(name);
                start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String uname,String msg) {
            output.println(uname.concat( " ").concat(msg));
        }

        public String getUsername() {
            return name;
        }

        public void run() {
            String line;
            try {
                while (true) {
                    line = input.readLine();

                    if (line.equals("end")) {
                        clients.remove(this);
                        users.remove(name);
                        break;
                    }
                    breadcast(name, line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}