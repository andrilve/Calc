package Calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.String;

public class Controller {
    @FXML
    private Text output;
    @FXML
    private Text output2;

    private long number1;
    private String operator = "";
    private boolean start = true;

    private Server server = null;

    public void setServer(Server server){
        this.server = server;
    }

    @FXML
    public void processNumpad(ActionEvent actionEvent ) {
        if (start) {
            output.setText("");
            output2.setText("");
            start = false;
        }
        String value = ((Button)actionEvent.getSource()).getText();
        output.setText(output.getText() + value);

        //Передаем значения на сервер
       outForServer(value);
    }

    @FXML
    public void processOperator(ActionEvent actionEvent ) {

        String value = ((Button)actionEvent.getSource()).getText();
         if(!"=".equals(value)){
            operator = value;
            outForServer(operator);
            output.setText("");
        }
        else {
            if (operator.isEmpty()) {
                return;
            }
            operator = "";
            start = true;
        }
    }

    private void outForServer(String textOut){
        try {
            server.out(textOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOutput2(String value){
        output2.setText(value);
    }
}
