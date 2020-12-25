package Calculations;

import javafx.scene.control.Label;
import java.math.BigInteger;


public class CalculatorView{

    private static Label screen;
    private static Label answer;
    Label operator;
    Label system;


    public CalculatorView(Label screen, Label answer, Label operator, Label system) {
        CalculatorView.screen = screen;
        CalculatorView.answer = answer;
        this.operator = operator;
        this.system = system;
        system.setText("10");
    }

    public static void outOfRangeInteger() {
        screen.setText("Hmm wait, this exponent is too big :'(");
        answer.setText("");
    }

    public void updateScreen(BigInteger number, int system){
        screen.setText(number.toString(system));
    }

    public void updateScreen() {
        screen.setText("");
    }

    public void updateAnswer(BigInteger number, int system){
        answer.setText(number.toString(system));
    }

    public void updateAnswer() {
        answer.setText("");
    }

    public void updateOperator(String op) {
        operator.setText(op);
    }

    public void updateOperator() {
        operator.setText("");
    }

    public void updateSystem(int system) {
        this.system.setText(Integer.toString(system));
    }

    public static void outOfRange(){
        screen.setText("Hmm wait, I cannot store such a huge number :'(");
        answer.setText("");
    }

    public static void illegalExpressionInfo(){
        screen.setText("Hmm wait, that's illegal!");
        answer.setText("");
    }

}
