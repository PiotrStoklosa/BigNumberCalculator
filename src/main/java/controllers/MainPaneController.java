package controllers;

import Calculations.CalculatorModel;
import Calculations.CalculatorView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigInteger;

public class MainPaneController {

    private CalculatorView view;
    private CalculatorModel model;

    @FXML
    private Label screen;
    @FXML
    private Label answer;

    @FXML
    private Button[] digits;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button e;
    @FXML
    private Button f;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button zero;

    public void initialize(){

        view = new CalculatorView(screen, answer);
        model = new CalculatorModel();

    }

    public void insertNumber(ActionEvent digit){

        Object source = digit.getSource();
        Button btn = (Button)source;
        String butSrcTxt = btn.getText();
        int figure = Integer.parseInt(butSrcTxt);
        BigInteger number = model.insertNumber(figure);
        view.updateScreen(number);

    }

    public void calculate(){
        BigInteger result = model.calculate();
        view.updateScreen(result);
        view.updateAnswer(result);

    }

    public void operate(ActionEvent sign){
        Object source = sign.getSource();
        Button btn = (Button)source;
        String butSrcTxt = btn.getText();
        if (butSrcTxt.equals("nCr"))
            butSrcTxt = "N";
        char symbol = butSrcTxt.charAt(0);

        model.operate(symbol);

        view.updateScreen();
    }

    public void calculateOneArgumentOperation(){

        BigInteger result = model.calculateOneArgumentOperation();
        view.updateScreen(result);
        view.updateAnswer(result);
    }
    public CalculatorView getView() {
        return view;
    }

    public CalculatorModel getModel() {
        return model;
    }
}
