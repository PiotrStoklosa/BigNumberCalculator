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

    public void updateScreen(BigInteger number){
        screen.setText(number.toString());
    }

    public void updateScreen() {
        screen.setText("");
    }

    public void updateAnswer(BigInteger number){
        answer.setText(number.toString());
    }
}
