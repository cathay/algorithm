package org.cathay.arrays;

import java.util.Arrays;

/**
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1, 2]:
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 */
public class BeautifulArrangement {

    public static void main(String[] args) {
        BeautifulArrangement solution = new BeautifulArrangement();
        System.out.println(solution.countArrangement(12));
    }

    public int countArrangement(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return countArrangement(arr);
    }

    private int countArrangement(int[] arr) {
        int count = 0;
        do {
            if (isBeautiful(arr)) count++;
            System.out.println(Arrays.toString(arr));
        } while (nextPermutation(arr));
        return count;
    }

    private boolean isBeautiful(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = i + 1;
            if (arr[i] % num != 0 && num % arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i] <= a[i - 1]) i--;

        if (i <= 0) return false;

        int pivot = a[i - 1];
        int j = a.length - 1;
        while (a[j] <= pivot) j--;
        a[i - 1] = a[j];
        a[j] = pivot;

        j = a.length - 1;
        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

}
