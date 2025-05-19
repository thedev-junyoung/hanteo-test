package org.example.problem2;

public class CoinChange {

    public static int countWays(int[] coins, int target) {
        return search(coins, target, 0);
    }

    public static int search(int[] coins, int remain, int index) {
        if (remain == 0) return 1;
        if (remain < 0) return 0;

        int total = 0;
        for (int i = index; i < coins.length; i++) {
            total += search(coins, remain - coins[i], i); // 중복 허용, 순서는 무시
        }

        return total;
    }

}