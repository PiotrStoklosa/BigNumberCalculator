package Calculations;

import java.math.BigInteger;

public class CalculatorModel {

    BigInteger firstArgument;
    BigInteger secondArgument;
    char sign;
    boolean calculate = false;
    int system = 10;

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public BigInteger insertNumber(int number){
        if (!calculate){

            if (firstArgument == null)
                firstArgument = BigInteger.valueOf(number);

            else{
                firstArgument = firstArgument.multiply(BigInteger.valueOf(system));
                firstArgument = firstArgument.add(BigInteger.valueOf(number));
            }

            return firstArgument;
        }
        else{
            if (secondArgument == null)
                secondArgument = BigInteger.valueOf(number);

            else{
                secondArgument = secondArgument.multiply(BigInteger.valueOf(system));
                secondArgument = secondArgument.add(BigInteger.valueOf(number));
            }

            return secondArgument;
        }


    }

    public BigInteger calculate(){

        if (secondArgument != null) {
            BigInteger result;

            result = switch (sign) {
                case '+' -> firstArgument.add(secondArgument);
                case '-' -> firstArgument.subtract(secondArgument);
                case 'X' -> firstArgument.multiply(secondArgument);
                case '/' -> firstArgument.divide(secondArgument);
                case '^' -> firstArgument.pow(secondArgument.intValue());
                case 'N' -> nCr(firstArgument.intValue(), secondArgument.intValue());
                case 'M' -> firstArgument.mod(secondArgument);
                default -> throw new IllegalStateException("Unexpected value: " + sign);
            };

            firstArgument = result;
            secondArgument = null;
            calculate = false;

            return result;
        }

        if (firstArgument != null)
            return firstArgument;

        return BigInteger.ZERO;

    }


    private BigInteger nCr(int firstNumber, int secondNumber) {
        BigInteger result = BigInteger.ONE;
        
        for (int i = firstNumber - secondNumber + 1; i <= firstNumber; i++)
            result = result.multiply(BigInteger.valueOf(i));
        
        for (int i = 1; i <= secondNumber; i++)
            result = result.divide(BigInteger.valueOf(i));
        
        return result;
    }

    public BigInteger factorial(int value){
        BigInteger result = BigInteger.ONE;

        for (int i=2; i<=value; i++)
            result = result.multiply(BigInteger.valueOf(i));

        return result;

    }

    public void operate(char sign) {
        if (firstArgument != null) {
            this.sign = sign;
            calculate = true;
        }
    }

    public BigInteger calculateOneArgumentOperation() {
        BigInteger result;
        if (calculate)
            result = factorial(secondArgument.intValue());
        else if(firstArgument != null)
            result = factorial(firstArgument.intValue());
        else
            result = BigInteger.ZERO;

        firstArgument = result;
        secondArgument = null;
        calculate = false;

        return result;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public int getSystem() {
        return system;
    }
}
