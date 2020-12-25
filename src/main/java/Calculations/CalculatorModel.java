package Calculations;

import java.math.BigInteger;

public class CalculatorModel {

    BigInteger firstArgument;
    BigInteger secondArgument;
    char sign;
    boolean calculate = false;
    int system = 10;
    BigInteger answer;

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
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

                if(SafeOperation.outOfRange(firstArgument, system))
                    return null;

            }

            return firstArgument;
        }
        else{
            if (secondArgument == null)
                secondArgument = BigInteger.valueOf(number);

            else{
                secondArgument = secondArgument.multiply(BigInteger.valueOf(system));
                secondArgument = secondArgument.add(BigInteger.valueOf(number));

                if(SafeOperation.outOfRange(firstArgument, system))
                    return null;

            }

            return secondArgument;
        }


    }

    public BigInteger calculate(){

        if (secondArgument != null) {
            BigInteger result;

            result = switch (sign) {
                case '+' -> SafeOperation.safeAddition(firstArgument,secondArgument);
                case '-' -> SafeOperation.safeSubtraction(firstArgument,secondArgument);
                case 'X' -> SafeOperation.safeMultiplication(firstArgument,secondArgument);
                case '/' -> SafeOperation.safeDivision(firstArgument,secondArgument);
                case '^' -> SafeOperation.safeExp(firstArgument, secondArgument);
                case 'N' -> SafeOperation.safeNCr(firstArgument, secondArgument) ? nCr(firstArgument, secondArgument) : null;
                case 'M' -> SafeOperation.safeModulo(firstArgument,secondArgument);
                default -> throw new IllegalStateException("Unexpected value: " + sign);
            };
            answer = calculated(result);
            return answer;
        }

        if (firstArgument != null) {
            calculate = false;
            answer = firstArgument;
            return firstArgument;
        }
        answer = BigInteger.ZERO;
        return BigInteger.ZERO;

    }


    private BigInteger nCr(BigInteger firstNumber, BigInteger secondNumber) {

        BigInteger result = BigInteger.ONE;

        for(BigInteger i = BigInteger.ONE; i.compareTo(secondNumber) <= 0; i = i.add(BigInteger.ONE))
        {
            result = result.multiply(firstNumber.subtract(i.subtract(BigInteger.ONE))).divide(i);
            if (SafeOperation.outOfRange(result, system))
                return null;
        }
        
        return result;
    }

    public BigInteger factorial(BigInteger value){
        BigInteger result = BigInteger.ONE;

        for (BigInteger i= BigInteger.TWO; i.compareTo(value) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
            if (SafeOperation.outOfRange(result, system))
                return null;
        }

        return result;

    }

    public boolean operate(char sign) {
        if (firstArgument != null) {
            this.sign = sign;
            calculate = true;
            return true;
        }
        return false;
    }

    public BigInteger calculateOneArgumentOperation() {
        BigInteger result;
        if (calculate)
            if (secondArgument == null)
                result = BigInteger.ZERO;
            else
             result = SafeOperation.safeFactorial(secondArgument) ? factorial(secondArgument) : null;
        else if(firstArgument != null)
            result = SafeOperation.safeFactorial(firstArgument) ? factorial(firstArgument) : null;
        else
            result = BigInteger.ZERO;

        answer = calculated(result);
        return answer;
    }

    public boolean setSystem(int system) {

        if (!SafeOperation.outOfRange(firstArgument, system) && !SafeOperation.outOfRange(secondArgument, system) && !SafeOperation.outOfRange(answer, system)) {
            this.system = system;
            return true;
        }
        return false;
    }

    public int getSystem() {
        return system;
    }


    public BigInteger delete() {
        if (firstArgument == null || secondArgument == null && calculate)
            return BigInteger.ZERO;
        if (!calculate) {
            firstArgument = firstArgument.divide(BigInteger.valueOf(system));
            return firstArgument;
        }

        secondArgument = secondArgument.divide(BigInteger.valueOf(system));
        return secondArgument;
    }

    public void reset() {
        firstArgument = null;
        secondArgument = null;
        calculate = false;
        system = 10;
    }

    public BigInteger calculated(BigInteger result){
        firstArgument = result;
        secondArgument = null;
        calculate = false;
        return result;
    }

    public BigInteger signChange() {

        BigInteger result;

        if (calculate) {
            if (secondArgument == null)
                result = BigInteger.ZERO;
            else
                result = secondArgument.multiply(BigInteger.valueOf(-1));

            secondArgument = result;
            return result;
        }
        else if(firstArgument != null)
            result = firstArgument.multiply(BigInteger.valueOf(-1));
        else
            result = BigInteger.ZERO;

        firstArgument = result;

        return result;
    }
}
