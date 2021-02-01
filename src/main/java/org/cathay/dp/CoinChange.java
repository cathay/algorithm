package org.cathay.dp;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        System.out.println(solution.coinChange(new int[]{3, 10}, 14));
        System.out.println(solution.coinChange(new int[]{2}, 3));
        System.out.println(solution.coinChange(new int[]{186, 419, 83, 408}, 6249));

    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        if (dp[amount] > 10_000) return -1; // because amount is max 10_000
        return dp[amount];
    }
}
