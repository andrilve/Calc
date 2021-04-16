package Calc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    static Socket socket;

    static {
        try {
            socket = new Socket("127.0.0.1", 32123);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void out(String value) throws IOException {
        try {
            while(!socket.isOutputShutdown()){
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(value);
                out.flush();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return socket;
    }
}
