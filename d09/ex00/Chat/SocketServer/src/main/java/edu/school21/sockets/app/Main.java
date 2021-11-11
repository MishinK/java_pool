package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;
import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--port"})
    int port;

    public static void main(String[] args) {
        Main program = new Main();

        JCommander.newBuilder().addObject(program).build().parse(args);
        program.run();
    }

    public void run() {
        Server server = new Server(port);
        server.startServer();
    }
}