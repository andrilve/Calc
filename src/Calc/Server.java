package Calc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    //DataOutputStream out;

    public Server() throws IOException {
        System.out.println("server work");
        /*try(Socket socket = new Socket("127.0.0.1", 32123)){
            //    System.out.println("Connecting whit server " + socket.getInetAddress() + " port " + socket.getPort());
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(!socket.isOutputShutdown()){
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("I alive");
                out.writeInt(5);
                out.flush();
                break;
            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void out(String value) throws IOException {
        try(Socket socket = new Socket("127.0.0.1", 32123)){
            //    System.out.println("Connecting whit server " + socket.getInetAddress() + " port " + socket.getPort());
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(!socket.isOutputShutdown()){
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("I alive");
                out.writeUTF(value);
                out.flush();
                break;
            }
        }
        catch (UnknownHostException e) {
             e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
   }

}
