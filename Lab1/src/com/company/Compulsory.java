package com.company;

public class Compulsory {
    static String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript",
            "PHP", "Swift", "Java"};

    public static int computeResFromN(int n) {
        int result = n * 3;
        // 0b / 0B -> binary number (when added to n it is converted to int)
        result += 0B10101;
        // 0x / 0X -> hexadecimal number (when added to n it is converted to int)
        result += 0XFF;
        result *= 6;
        return result;
    }

    public static int addDigits(int result) {
        int res_aux = result;
        int new_res = 0;

        // adding the last digit to new_res and then deleting it from result
        while (res_aux != 0) {
            new_res += res_aux % 10;
            res_aux /= 10;
        }

        /*if final_res > 9 then it has more than 1 digit
         in while perform the operations from the 'while' above*/
        int final_res = new_res;
        res_aux = new_res;

		// add the digits of res_aux in final_res
        while (res_aux != 0) {
            final_res += res_aux % 10;
            res_aux /= 10;
            if (res_aux == 0) {
                if (final_res > 9) // iterate one more time
                {
                    res_aux = final_res;
                    final_res = 0;
                } else
                    break;
            }
        }
        return final_res;
    }

    public static void solveExercises() {
        System.out.println("Hello World!");

        /* Math.random returns a random number between 0.0 and 1.0
        n will be a number from 0 to 1 million */
        final int N = (int) (Math.random() * 1_000_000);

        int result = computeResFromN(N);

        result = addDigits(result);

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
