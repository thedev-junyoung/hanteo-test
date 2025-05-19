package org.example.problem2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChangeTest {

    @Test
    @DisplayName("case 1 - 동전 {1, 2, 3}으로 금액 4를 만드는 경우의 수")
    void case1() {
        int[] coinValues = {1, 2, 3};
        int targetAmount = 4;
        int expectedCombinations = 4; // {1,1,1,1}, {1,1,2}, {2,2}, {1,3}
        int actualCombinations = CoinChange.countWays(coinValues, targetAmount);
        assertEquals(expectedCombinations, actualCombinations);
    }

    @Test
    @DisplayName("case 2 - 동전 {2, 5, 3, 6}으로 금액 10을 만드는 경우의 수")
    void case2() {
        int[] coinValues = {2, 5, 3, 6};
        int targetAmount = 10;
        int expectedCombinations = 5; // {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5}, {5,5}
        int actualCombinations = CoinChange.countWays(coinValues, targetAmount);
        assertEquals(expectedCombinations, actualCombinations);
    }

    @Test
    @DisplayName("case 3 - 동전 {1, 2, 5}로 금액 5를 만드는 경우의 수")
    void case3() {
        int[] coinValues = {1, 2, 5};
        int targetAmount = 5;
        int expectedCombinations = 4; // {1,1,1,1,1}, {1,1,1,2}, {1,2,2}, {5}
        int actualCombinations = CoinChange.countWays(coinValues, targetAmount);
        assertEquals(expectedCombinations, actualCombinations);
    }
}
