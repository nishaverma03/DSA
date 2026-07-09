class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        int n = robot.size();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;

        for (int[] f : factory) {
            long[] next = dp.clone();

            for (int i = 1; i <= n; i++) {
                long distance = 0;

                for (int k = 1; k <= f[1] && k <= i; k++) {
                    distance += Math.abs((long) robot.get(i - k) - f[0]);
                    next[i] = Math.min(next[i], dp[i - k] + distance);
                }
            }

            dp = next;
        }

        return dp[n];
    }
}