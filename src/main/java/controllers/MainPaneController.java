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
    private Label system;
    @FXML
    private Label operator;

    public void initialize(){

        view = new CalculatorView(screen, answer, operator, system);
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
            if (number != null)
                view.updateScreen(number, model.getSystem());
            else{
                model.reset();
                view.updateOperator();
            }
        }

    }

    public void calculate(){
        BigInteger result = model.calculate();

        if (result != null) {
            view.updateScreen(result, model.getSystem());
            view.updateAnswer(result, model.getSystem());
        }
        else{
            model.reset();
        }

        view.updateOperator();

    }

    public void operate(ActionEvent sign){
        Object source = sign.getSource();
        Button btn = (Button)source;
        String op = btn.getText();
        String butSrcTxt = op;
        if (butSrcTxt.equals("nCr"))
            butSrcTxt = "N";
        char symbol = butSrcTxt.charAt(0);

        if (model.operate(symbol))
            view.updateOperator(op);

        view.updateScreen();
    }

    public void calculateOneArgumentOperation(){

        BigInteger result = model.calculateOneArgumentOperation();
        if (result == null){
            model.reset();
            view.updateOperator();
        }
        else {
            view.updateScreen(result, model.getSystem());
            view.updateAnswer(result, model.getSystem());
        }
    }

    public void changeSystem(ActionEvent e){

        Object source = e.getSource();
        Button btn = (Button)source;
        String butSrcTxt = btn.getText();

        BigInteger result;
        BigInteger ans;

        String text = screen.getText();

        if (!model.isNumeric(text))
            result = BigInteger.ZERO;
        else
            result = new BigInteger(screen.getText(), model.getSystem());

        if (answer.getText().equals(""))
            ans = BigInteger.ZERO;
        else
            ans = new BigInteger(answer.getText(), model.getSystem());

        boolean outOfRange = false;

        if (butSrcTxt.equals("BINARY")) {
            if (model.setSystem(2))
                view.updateSystem(2);
            else
                outOfRange = true;

        }
        if (butSrcTxt.equals("DECIMAL")) {
            if (model.setSystem(10))
                view.updateSystem(10);
            else
                outOfRange = true;
        }
        if (butSrcTxt.equals("HEXADECIMAL")) {
            if (model.setSystem(16))
                view.updateSystem(16);
            else
                outOfRange = true;
        }
        if (!outOfRange) {
            view.updateScreen(result, model.getSystem());
            view.updateAnswer(ans, model.getSystem());
        }
        else {
            model.reset();
            view.updateOperator();
        }
    }

    public void delete(){
        BigInteger result = model.delete();
        view.updateScreen(result, model.getSystem());
    }

    public void reset(){

        model.reset();
        view.updateScreen();
        view.updateAnswer();
        view.updateSystem(10);
        view.updateOperator();
    }

    public void signChange(){
        BigInteger result = model.signChange();
        view.updateScreen(result, model.getSystem());
    }

}
