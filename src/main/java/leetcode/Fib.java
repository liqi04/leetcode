package leetcode;

public class Fib {
    public static int fib(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int dp1 = 1, dp2 = 0, dp = 0;
        for (int i = 2; i < n + 1; i++) {
            dp = dp1 + dp2;
            dp2 = dp1;
            dp1 = dp;
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(fib2(10));
    }
}
