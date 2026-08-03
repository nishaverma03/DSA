class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int best = Integer.MIN_VALUE;
            int sum = 0;

            for (int take = 0; take < 3 && i + take < n; take++) {
                sum += stoneValue[i + take];
                best = Math.max(best, sum - dp[i + take + 1]);
            }

            dp[i] = best;
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        }
        return "Tie";
    }
}