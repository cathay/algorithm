package org.cathay.dp;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        //System.out.println(solution.coinChange(new int[]{1, 2, 5}, 20));
        System.out.println(solution.coinChange(new int[]{2}, 3));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        if (dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }
}
