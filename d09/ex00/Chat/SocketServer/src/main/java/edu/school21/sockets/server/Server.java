package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private Socket clientSocket = null;
    private ServerSocket server = null;
   
    private BufferedReader in = null;
    private BufferedWriter out = null;

    private Integer port;

    public Server(int port) {
        this.port = port;
    }

	public void endServer()
	{
		try {
			in.close();
			clientSocket.close();
			server.close();
			out.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

    public void startServer() {
        try {
            server = new ServerSocket(port);

            clientSocket = server.accept();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            sendMessage("Hello from Server! Type 'signUp' command to register");

            String command = in.readLine();

            if (!command.equals("signUp")) {
                sendMessage("Error command");
                endServer();
                return;
            }

            sendMessage("Enter username:");
			String userName = in.readLine();
			sendMessage("Enter password:");
			String password = in.readLine();

            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);

            if (usersService.signUp(userName, password)) {
                sendMessage("Successful!");
            } else {
                sendMessage("user already exists");
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        } finally {
			endServer();
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
}