/**
 * <h1>CSCI 2336 Programming Fundamentals III Fall 2017</h1>
 * <p>
 * Implemented algorithms of Insertion Sort and Merge Sort.
 *
 * @author Artem Skitenko
 * @version 1.0
 * @since 2/2/2018
 */


import java.util.Arrays;

public class Assignment1 {
    static void insertionSort(int[] arr) {
        int key, i;
        for (int j = 0; j < arr.length; j++) {
            key = arr[j];
            i = j - 1;
            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

    static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1, n2 = r - q;
        int[] L = new int[n1], R = new int[n2];
        System.arraycopy(arr, p, L, 0, n1);
        System.arraycopy(arr, q + 1, R, 0, n2);
        int i = 0, j = 0, k = p;
        for (; i < n1 && j < n2; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

    static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = p + (r - p) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    static void generateRandArrays(int[] arr, int[] arr1) {
        for (int i = 0, rand; i < arr.length; i++) {
            rand = (int) (Math.random() * 501);
            arr[i] = rand;
            arr1[i] = rand;
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[100], arr1[] = new int[100];
        generateRandArrays(arr, arr1);
        long endTime, startTime, mergeSortTime[] = new long[5], insertionSortTime[] = new long[5];
        //Test run
        mergeSort(arr);
        insertionSort(arr1);
        for (int i = 0; i < 5; i++) {
            generateRandArrays(arr, arr1);
            //  Store those integers in an array and display them on screen.
            System.out.println(Arrays.toString(arr));
            System.out.println();
            startTime = System.nanoTime();
            mergeSort(arr);
            endTime = System.nanoTime();
            mergeSortTime[i] = endTime - startTime;
            startTime = System.nanoTime();
            insertionSort(arr1);
            endTime = System.nanoTime();
            insertionSortTime[i] = endTime - startTime;
        }

        // Output the header of the table
        System.out.println("\t\t\t\t\t+----------------+---------------------------------------------------------------+");
        System.out.println("\t\t\t\t\t|                |       Time to sort the array of 100 elements 5 times (ms)     |");
        System.out.println("\t\t\t\t\t| Sort iteration |-------------------------------+-------------------------------+");
        System.out.println("\t\t\t\t\t|                |           Merge Sort          |          Insertion Sort       |");
        System.out.println("\t\t\t\t\t+----------------+-------------------------------+-------------------------------+");

        for (int i = 0; i < 5; i++) {
            System.out.format("\t\t\t\t\t|       #%d       |%17.3f              |%17.3f              |\n", i + 1, ((double) mergeSortTime[i]) / 1000000, ((double) insertionSortTime[i]) / 1000000);
            System.out.println("\t\t\t\t\t+----------------+-------------------------------+-------------------------------+");
            // Summarize running time of the sorts to find average
            if (i != 0) {
                mergeSortTime[0] += mergeSortTime[i];
                insertionSortTime[0] += insertionSortTime[i];
            }
        }
        System.out.format("\t\t\t\t\t|  Average time  |%17.3f              |%17.3f              |\n", ((double) mergeSortTime[0]) / 5000000, ((double) insertionSortTime[0]) / 5000000);
        System.out.println("\t\t\t\t\t+----------------+-------------------------------+-------------------------------+");

    }
}
