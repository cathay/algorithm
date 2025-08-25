package org.cathay;

public class Sum3 {

    public static void main(String[] args) {
        System.out.println(closedst3Sum(new int[]{-1, 2, 1, -4}, 1));
    }

    /*
    Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
    Return the sum of the three integers.
     */
    public static int closedst3Sum(int[] nums, int target) {
        int closest = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        if (nums.length < 3) return 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    if (closest == Integer.MIN_VALUE || diff < closest) {
                        closest = diff;
                        sum = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }

        return sum;
    }
}
