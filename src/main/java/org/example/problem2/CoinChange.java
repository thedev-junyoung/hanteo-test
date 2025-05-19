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

    public static void main(String[] args) {

        System.out.println(CoinChange.countWays(new int[]{1, 2, 3}, 4));  // → 4
        System.out.println(CoinChange.countWays(new int[]{2, 5, 3, 6}, 10));  // → 5

    }
}