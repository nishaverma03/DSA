class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];
        int ans = 1;
        for (int j = 0; j < cols; j++) {
            dp[j] = 1;
            for (int i = 0; i < j; i++) {
                boolean ok = true;
                for (int r = 0; r < rows; r++) {
                    if (Math.abs(grid[r][j] - grid[r][i]) > limit) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[j]);
        }
        return ans;
    }
}