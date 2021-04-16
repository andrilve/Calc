package Calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("ui.fxml").openStream());

        Server server = new Server();
        ((Controller) fxmlLoader.getController()).setServer(server);
        Thread thread = new ThreadIn(server, (Controller) fxmlLoader.getController());
        thread.start();

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main( String[] args ) throws IOException {

        Lock lock = new ReentrantLock();
        Condition cv = lock.newCondition();
        StaticAccess.sCondition = cv;

        //Server server = new Server();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello frome thread");
            }
        }).start();

        launch(args);
    }


}
