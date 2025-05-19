package org.example.problem2;

public class CoinChangeMain {

    public static void main(String[] args) {
        System.out.println("=== [동전 조합 문제 실행 결과] ===");

        int[] coins1 = {1, 2, 3};
        int sum1 = 4;
        int result1 = CoinChange.countWays(coins1, sum1);
        System.out.printf("입력: coins = %s, sum = %d → 가능한 조합 수 = %d%n",
                formatArray(coins1), sum1, result1);

        int[] coins2 = {2, 5, 3, 6};
        int sum2 = 10;
        int result2 = CoinChange.countWays(coins2, sum2);
        System.out.printf("입력: coins = %s, sum = %d → 가능한 조합 수 = %d%n",
                formatArray(coins2), sum2, result2);
    }

    private static String formatArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
