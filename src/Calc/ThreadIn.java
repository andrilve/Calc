package Calc;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ThreadIn extends Thread {

    private final Socket socket;
    private final Controller controller;

    public ThreadIn(Server server, Controller controller){
        this.socket = server.getSocket();
        this.controller = controller;
    }

    public void run(){
        while (!socket.isOutputShutdown()) {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                assert in != null;
                String value = in.readUTF();
                controller.setOutput2(value);
            } catch (EOFException e) {
                System.out.println("Socket is connect? = " + socket.isConnected());
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
