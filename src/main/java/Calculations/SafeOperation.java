package Calculations;

import java.math.BigInteger;

public class SafeOperation {

    private static final int RANGE_OF_NUMBER = 39;

    public static BigInteger safeAddition(BigInteger number1, BigInteger number2){

        BigInteger result = number1.add(number2);

        if (outOfRange(result)) {
            outOfRange();
            return null;
        }

        return result;
    }

    public static BigInteger safeSubtraction(BigInteger number1, BigInteger number2){

        BigInteger result = number1.subtract(number2);

        if (outOfRange(result)) {
            outOfRange();
            return null;
        }

        return result;
    }

    public static BigInteger safeMultiplication (BigInteger number1, BigInteger number2){

        BigInteger result = number1.multiply(number2);

        if (outOfRange(result)) {

            outOfRange();
            return null;

        }

        return result;
    }

    public static BigInteger safeDivision(BigInteger number1, BigInteger number2){

        if (number2.equals(BigInteger.ZERO)) {

            illegalExpressionInfo();
            return null;

        }

        return number1.divide(number2);
    }

    public static BigInteger safeModulo(BigInteger number1, BigInteger number2){

        if (number2.compareTo(BigInteger.ZERO) <= 0) {

            illegalExpressionInfo();
            return null;

        }

        return number1.mod(number2);
    }

    public static BigInteger safeExp(BigInteger number1, BigInteger number2){

        if (number2.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {

            outOfRangeInteger();
            return null;

        }
        if (number1.equals(BigInteger.ZERO) && number2.equals(BigInteger.ZERO)){

            illegalExpressionInfo();
            return null;

        }

        BigInteger result = BigInteger.ONE;

        for (int i=0; i < number2.intValue(); i++){

            result = result.multiply(number1);

            if (outOfRange(result)) {

                outOfRange();
                return null;

            }
        }

        return result;
    }

    public static boolean safeNCr(BigInteger number1, BigInteger number2){

        if (number1.compareTo(BigInteger.ZERO) < 0 || number2.compareTo(BigInteger.ZERO) < 0) {

            illegalExpressionInfo();
            return false;

        }

        if (number2.compareTo(number1) > 0) {

            illegalExpressionInfo();
            return false;

        }

        return true;
    }

    public static boolean safeFactorial(BigInteger number){

        if (number.compareTo(BigInteger.ZERO) < 0) {

            illegalExpressionInfo();
            return false;

        }

        return true;
    }

    public static boolean outOfRange(BigInteger number, int system){

        if (number == null)
            return false;

        if (number.toString(system).length() > RANGE_OF_NUMBER) {

            outOfRange();
            return true;

        }
        return false;
    }

    public static boolean outOfRange(BigInteger number){

        if (number == null)
            return false;

        if (number.toString().length() > RANGE_OF_NUMBER) {

            outOfRange();
            return true;

        }

        return false;
    }

    private static void illegalExpressionInfo(){
        CalculatorView.illegalExpressionInfo();
   }

    private static void outOfRange(){
        CalculatorView.outOfRange();
    }

    private static void outOfRangeInteger(){
        CalculatorView.outOfRangeInteger("exponent");
    }

}
