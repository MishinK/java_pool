package edu.school21.sockets.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	static Socket client;
	static DataInputStream dis;
	static DataOutputStream dos;
	static Scanner scanner;

	public static void main(String[] args) throws IOException {
		try {
			client = new Socket("localhost", 8080);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			scanner = new Scanner(System.in);

			String serverMes;
			String clientMes;
			while (true) {
				serverMes = dis.readLine();
				System.out.println(serverMes);
				if (serverMes == null || serverMes.equals("Successful!") || serverMes.equals("user already exists"))
					break ;
				System.out.print("> ");
				clientMes = scanner.nextLine();
				dos.write(clientMes.concat("\n").getBytes());
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			client.close();
			dis.close();
			dos.close();
			scanner.close();
		}
	}
}