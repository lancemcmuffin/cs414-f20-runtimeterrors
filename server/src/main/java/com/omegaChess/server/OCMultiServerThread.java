package com.omegaChess.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OCMultiServerThread extends Thread {

    private Socket socket = null;

    public OCMultiServerThread(Socket socket) {
        super("SquareMultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;

            OCProtocol p = new OCProtocol();

            while ((inputLine = in.readLine()) != null) {

                outputLine = p.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye")) {
                    break;
                }

            }

            socket.close();

        } catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }

}
