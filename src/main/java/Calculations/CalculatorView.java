package Calculations;

import javafx.scene.control.Label;
import java.math.BigInteger;


public class CalculatorView{

    Label screen;
    Label answer;


    public CalculatorView(Label screen, Label answer) {
        this.screen = screen;
        this.answer = answer;
    }

    public void updateScreen(BigInteger number, int system){
        screen.setText(number.toString(system));
    }

    public void updateScreen() {
        screen.setText("");
    }
    public void updateScreen(String value) {
        screen.setText(value);
    }
    public void updateAnswer(String value) {
        screen.setText(value);
    }

    public void updateAnswer(BigInteger number, int system){
        answer.setText(number.toString(system));
    }
}
