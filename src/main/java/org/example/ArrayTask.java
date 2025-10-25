package org.example;

import java.util.Arrays;
import java.util.Random;

public class ArrayTask {
    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(101) - 50;
        }

        System.out.println("Массив: " + Arrays.toString(arr));

        int minAbs = arr[0];
        for (int num : arr) {
            if (Math.abs(num) < Math.abs(minAbs)) {
                minAbs = num;
            }
        }

        System.out.println("Минимальное по модулю значение: " + minAbs);
    }
}
