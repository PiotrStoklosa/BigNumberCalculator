package Calculations;

import java.math.BigInteger;

public class CalculatorModel {

    BigInteger firstArgument;
    BigInteger secondArgument;
    char sign;
    boolean calculate = false;

    public BigInteger insertNumber(int number){
        if (!calculate){

            if (firstArgument == null)
                firstArgument = BigInteger.valueOf(number);

            else{
                firstArgument = firstArgument.multiply(BigInteger.valueOf(10));
                firstArgument = firstArgument.add(BigInteger.valueOf(number));
            }

            return firstArgument;
        }
        else{
            if (secondArgument == null)
                secondArgument = BigInteger.valueOf(number);

            else{
                secondArgument = secondArgument.multiply(BigInteger.valueOf(10));
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
                case '%' -> firstArgument.mod(secondArgument);
                case 'N' -> nCr(firstArgument.intValue(), secondArgument.intValue());
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
}
