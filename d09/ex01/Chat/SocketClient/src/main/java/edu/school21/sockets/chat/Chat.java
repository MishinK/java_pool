package edu.school21.sockets.chat;

import java.io.*;
import java.net.Socket;

public class Chat {
    private final BufferedReader reader;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Socket clientSocket;

    public Chat(int port) throws IOException {
        clientSocket = new Socket("localhost", port);

        reader = new BufferedReader(new InputStreamReader(System.in));

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    public void start() {
        try {
            firstStage();

            getMessage();

            new ChatReader().start();

            new ChatWriter().start();

        } catch (IOException ex) {
            closeConnection();
        }
    }

    private void firstStage() throws IOException {
        getMessage();

        String command = reader.readLine();

        sendMessage(command);

        getMessage();

        String userName = reader.readLine();

        sendMessage(userName);

        getMessage();

        String password = reader.readLine();

        sendMessage(password);
    }

    private void getMessage() {
        try {
            String message = in.readLine();

            if (message.contains("Wrong") || message.contains("Error")) {
                closeConnection();
            }

            System.out.println(message);
        } catch (IOException e) {
            closeConnection();
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        if (!clientSocket.isClosed()) {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException ignored) {
            }
            System.exit(0);
        }
    }

    private class ChatReader extends Thread {
        @Override
        public void run() {
            String message;
            try {
                while (true) {
                        message = in.readLine();

                        if (message.equals("stop")) {
                            closeConnection();
                            break;
                        }

                        System.out.println(message);
                    }
            } catch (IOException e) {
                closeConnection();
            }
        }
    }

    private class ChatWriter extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String message = reader.readLine();

                    if (message.toLowerCase().equals("exit")) {
                        closeConnection();
                        break;
                    }

                    sendMessage(message);
                }
            } catch (IOException e) {
                closeConnection();
            }
        }
    }
}
