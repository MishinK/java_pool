package edu.school21.sockets.app;

import java.io.*;
import com.beust.jcommander.*;
import edu.school21.sockets.chat.Chat;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--port"})
    int port;

    public static void main(String[] args) {
        Main program = new Main();

        JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);

        program.run();
    }

    public void run() {
        try {
            Chat chat = new Chat(port);

            chat.start();
        } catch (IOException ignored) {
        }
    }

}