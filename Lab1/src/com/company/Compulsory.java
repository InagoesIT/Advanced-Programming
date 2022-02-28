package com.company;

public class Compulsory {
    final private static String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript",
            "PHP", "Swift", "Java"};

    private static int computeResFromN(int n) {
        int result = n * 3;
        // 0b / 0B -> binary number (when added to n it is converted to int)
        result += 0B10101;
        // 0x / 0X -> hexadecimal number (when added to n it is converted to int)
        result += 0XFF;
        result *= 6;
        return result;
    }

    private static int addDigits(int result) {
		// add the digits of res_aux in final_res,
        // if res_aux is 0 and final_res has more than one digit sum the digits again
        // otherwise, break

        int res_aux = result;
        int final_res = 0;

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