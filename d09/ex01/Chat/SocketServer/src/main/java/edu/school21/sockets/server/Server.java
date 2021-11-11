package edu.school21.sockets.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Connection> connections;
    private final Integer port;

    public Server(int port) {
        this.port = port;
        connections = new ArrayList<>();
    }

    public void startServer() throws IOException {
        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket socket = server.accept();

            try {
                connections.add(new Connection(socket));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}