package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTask {
    public static void main(String[] args) {
        int n = 10;
        List<Double> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            list.add(rand.nextDouble() * 100);
        }

        System.out.println("Исходный список:");
        System.out.println(list);

        quickSort(list, 0, list.size() - 1);

        System.out.println("Отсортированный список (Quick Sort):");
        System.out.println(list);
    }

    private static void quickSort(List<Double> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Double> list, int low, int high) {
        double pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Double> list, int i, int j) {
        double temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
