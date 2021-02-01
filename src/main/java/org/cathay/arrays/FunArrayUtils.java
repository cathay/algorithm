package org.cathay.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunArrayUtils {

    public static void main(String[] args) {
        FunArrayUtils solution = new FunArrayUtils();
        //System.out.println(solution.findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        System.out.println(solution.findKthPositive(new int[]{1, 2, 3, 4}, 2));
        System.out.println(System.currentTimeMillis() );
    }

    public int findKthPositive(int[] arr, int k) {
        List<Integer> missing = new ArrayList<>();
        addMissingNumbers(missing, 1, arr[0]);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(missing);
            if (missing.size() >= k) {
                return missing.get(k - 1);
            }
            if (i < arr.length - 1)
                addMissingNumbers(missing, arr[i] + 1, arr[i + 1]);
        }
        int gap = k - missing.size();
        int lastElement = arr[arr.length - 1];
        return lastElement + gap;
    }

    private void addMissingNumbers(List<Integer> missing, int start, int endExclusive) {
        List<Integer> ranges = IntStream.range(start, endExclusive)
                .boxed()
                .collect(Collectors.toList());
        missing.addAll(ranges);
    }


}
