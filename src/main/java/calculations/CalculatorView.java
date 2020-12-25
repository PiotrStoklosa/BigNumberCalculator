package calculations;

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
        system.setText("DECIMAL");

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

        if (system == 2)
            this.system.setText("BINARY");
        else if(system == 10)
            this.system.setText("DECIMAL");
        else if (system == 16)
            this.system.setText("HEXADECIMAL");

    }

    public static void outOfRange(){

        screen.setText("I can't store such a huge number");
        answer.setText("");

    }

    public static void outOfRangeInteger(String field) {

        screen.setText("This " + field + " is too big");
        answer.setText("");

    }

    public static void illegalExpressionInfo(){

        screen.setText("That's illegal!");
        answer.setText("");

    }

}
