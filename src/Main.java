import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.print("Введите математическое выражение:");
        Scanner sd = new Scanner(System.in);
        String input = sd.nextLine();
        sd.close();
        System.out.println(calc(input));
    }
    public static String calc(String input) {
        Calculator calc = new Calculator();
        try {
            calc.parseInput(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            calc.calculation();
        } catch (Exception e) {
        throw new RuntimeException(e);
    } if (calc.roman){
            return calc.romanNumbers[calc.result];
    } else {
        return Integer.toString(calc.result);
       }
    }
}

class Calculator {
    boolean roman = false;
    int num1;
    int num2;
    char operator;
    int result;
    String[] romanNumbers = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
            "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
            "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
            "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
            "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    void parseInput(String tern) throws Exception {
        tern = deleteWhiteSpases(tern);
        String[] decTern = decompositionTern(tern);
        if (!checkAmountNumArray(decTern)) throw new Exception("Ошибка!Введен один или больше трех операндов.");
        getNumbers(decTern);
        getOperator(tern);
    }

    String deleteWhiteSpases(String tern) {
        return tern.replace(" ", "");
    }

    String[] decompositionTern(String tern) {
        return tern.split("([+\\-/*])");
    }

    boolean checkAmountNumArray(String[] numbers) {
        return numbers.length == 2;
    }

    int romanNumberToInt(String romanNumber) {
        for (int i = 0; i < romanNumbers.length; i++) {
            if (Objects.equals(romanNumbers[i], romanNumber)) {
                return i;
            }
        }
        return 0;
    }

    void getNumbers(String[] decTern) throws Exception {
        try {
            num1 = Integer.parseInt(decTern[0]);
            num2 = Integer.parseInt(decTern[1]);
        } catch (NumberFormatException e) {
            num1 = romanNumberToInt(decTern[0]);
            num2 = romanNumberToInt(decTern[1]);
            if (num1 == 0 || num2 == 0) {
                throw new Exception("Ошибка!Используются разные системы счисления");
            }
            roman = true;
        }
        if (num1 < 1 || num2 < 1 || num1 > 10 || num2 > 10) {
            throw new Exception("Ошибка!Число больше 10");
        }
    }

    void getOperator(String input) {
        for (int i = 0; i < input.length(); i++) {
            if ("+ - * /".contains(Character.toString(input.charAt(i)))) {
                operator = input.charAt(i);
            }
        }
    }

    void calculation() throws Exception {

        switch (operator) {
            case '+' -> result = num1 + num2;
            case '-' -> {
                if (roman && (num1 - num2) < 1) {
                    throw new Exception("У римских цифр нет отрицательных чисел");
                }
                result = num1 - num2;
            }
            case '/' -> result = num1 / num2;
            case '*' -> result = num1 * num2;
        }
        }

    }
