package edu.school21.sockets.app;

import edu.school21.sockets.server.*;
import com.beust.jcommander.*;
import java.io.IOException;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--port"})
    int port;

    public static void main(String[] args) throws IOException {
        Main program = new Main();

        JCommander.newBuilder().addObject(program).build().parse(args);
        program.run();
    }

    public void run() throws IOException  {
        Server server = new Server(port);
		System.out.printf("Server is running on port %d\n", port);
        server.startServer();
    }
}