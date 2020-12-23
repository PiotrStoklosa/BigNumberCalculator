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

        int figure;
        
        if (model.isNumeric(butSrcTxt))
            figure = Integer.parseInt(butSrcTxt);
        
        else{
            figure = switch (butSrcTxt){
                case "A" -> 10;
                case "B" -> 11;
                case "C" -> 12;
                case "D" -> 13;
                case "E" -> 14;
                case "F" -> 15;
                default -> throw new IllegalStateException("Unexpected value: " + butSrcTxt);
            };
        }
        
        if (figure < model.getSystem()) {
            BigInteger number = model.insertNumber(figure);
            view.updateScreen(number, model.getSystem());
        }

    }

    public void calculate(){
        BigInteger result = model.calculate();
        view.updateScreen(result, model.getSystem());
        view.updateAnswer(result, model.getSystem());

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
        view.updateScreen(result, model.getSystem());
        view.updateAnswer(result, model.getSystem());
    }

    public void changeSystem(ActionEvent e){

        Object source = e.getSource();
        Button btn = (Button)source;
        String butSrcTxt = btn.getText();

        BigInteger result;
        BigInteger ans;

        if (screen.getText().equals(""))
            result = BigInteger.ZERO;
        else
            result = new BigInteger(screen.getText(), model.getSystem());

        if (answer.getText().equals(""))
            ans = BigInteger.ZERO;
        else
            ans = new BigInteger(answer.getText(), model.getSystem());


        if (butSrcTxt.equals("BINARY"))
            model.setSystem(2);
        if (butSrcTxt.equals("DECIMAL"))
            model.setSystem(10);
        if (butSrcTxt.equals("HEXADECIMAL"))
            model.setSystem(16);

        view.updateScreen(result, model.getSystem());
        view.updateAnswer(ans, model.getSystem());
    }


    public CalculatorView getView() {
        return view;
    }

    public CalculatorModel getModel() {
        return model;
    }
}
