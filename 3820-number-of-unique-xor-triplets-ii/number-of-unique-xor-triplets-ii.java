class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        int[] freq = new int[MAX];
        boolean[] ans = new boolean[MAX];

        for (int x : nums) {
            freq[x]++;
            ans[x] = true;
        }

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int val = 0; val < MAX; val++) {
            if (freq[val] == 0) continue;

            boolean[][] next = new boolean[4][MAX];

            for (int k = 0; k <= 3; k++) {
                System.arraycopy(dp[k], 0, next[k], 0, MAX);
            }

            int limit = Math.min(freq[val], 3);

            for (int used = 0; used <= 3; used++) {
                for (int xr = 0; xr < MAX; xr++) {
                    if (!dp[used][xr]) continue;

                    for (int take = 1; take <= limit && used + take <= 3; take++) {
                        int contrib = (take % 2 == 1) ? val : 0;
                        next[used + take][xr ^ contrib] = true;
                    }
                }
            }

            dp = next;
        }

        for (int xr = 0; xr < MAX; xr++) {
            if (dp[3][xr]) ans[xr] = true;
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}