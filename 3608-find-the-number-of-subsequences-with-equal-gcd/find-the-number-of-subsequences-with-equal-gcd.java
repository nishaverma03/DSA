class Solution {
    static final int MOD = 1_000_000_007;
    public int subsequencePairCount(int[] nums) {
        final int MAX = 200;
        final int SIZE = MAX + 1;
        int[][] gcd = new int[SIZE][SIZE];
        for (int i = 0; i <= MAX; i++) {
            for (int j = 0; j <= MAX; j++) {
                if (i == 0) gcd[i][j] = j;
                else if (j == 0) gcd[i][j] = i;
                else gcd[i][j] = getGcd(i, j);
            }
        }
        long[] dp = new long[SIZE * SIZE];
        long[] ndp = new long[SIZE * SIZE];
        dp[0] = 1;
        for (int x : nums) {
            java.util.Arrays.fill(ndp, 0);
            for (int g1 = 0; g1 <= MAX; g1++) {
                int row = g1 * SIZE;
                for (int g2 = 0; g2 <= MAX; g2++) {
                    long ways = dp[row + g2];
                    if (ways == 0) continue;
                    ndp[row + g2] = (ndp[row + g2] + ways) % MOD;
                    int ng1 = gcd[g1][x];
                    ndp[ng1 * SIZE + g2] =
                            (ndp[ng1 * SIZE + g2] + ways) % MOD;
                    int ng2 = gcd[g2][x];
                    ndp[row + ng2] =
                            (ndp[row + ng2] + ways) % MOD;
                }
            }
            long[] tmp = dp;
            dp = ndp;
            ndp = tmp;
        }
        long ans = 0;
        for (int g = 1; g <= MAX; g++) {
            ans = (ans + dp[g * SIZE + g]) % MOD;
        }
        return (int) ans;
    }
    private int getGcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}