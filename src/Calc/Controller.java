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

    private boolean start = false;

    private String squareSign = "^2";
    private String reset = "Reset";
    private String equal = "=";

    private String varNumb = "";
    private String varSign = "";

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
            outForServer(reset);
        }
        String value = ((Button)actionEvent.getSource()).getText();
        if (!varSign.equals("")){
            outForServer(varSign);
            varSign = "";
        }
        varNumb = varNumb + value;
        output.setText(output.getText() + value);

        //Передаем значения на сервер
       //outForServer(value);
    }

    @FXML
    public void processOperator(ActionEvent actionEvent ) {
        if (start) {
            output.setText("");
            output2.setText("");
            start = false;
            outForServer(reset);
        }
        String value = ((Button)actionEvent.getSource()).getText();
        if (!varNumb.equals("")){
            outForServer(varNumb);
            varNumb = "";
        }
        varSign = value;
        if (value.equals(equal)){
            start = true;
            outForServer(value);
            varNumb = "";
            varSign = "";
        }

        output.setText(output.getText() + value);
        //outForServer(value);
    }

    @FXML
    public void processSquare(ActionEvent actionEvent ) {
        output.setText(output.getText() + squareSign);
        outForServer(squareSign);
    }

    @FXML
    public void processReset(ActionEvent actionEvent ) {
        output.setText("");
        output2.setText("");
        outForServer(reset);
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
