class Solution {
    private static final long MOD = 1_000_000_007L;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) {
            return m;
        }
        long[] up = new long[m];
        long[] down = new long[m];
        for (int v = 0; v < m; v++) {
            up[v] = v;              
            down[v] = m - 1 - v;    
        }
        if (n == 2) {
            long ans = 0;
            for (int v = 0; v < m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
            return (int) ans;
        }
        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];
            long prefix = 0;
            for (int v = 0; v < m; v++) {
                newUp[v] = prefix;
                prefix = (prefix + down[v]) % MOD;
            }
            long suffix = 0;
            for (int v = m - 1; v >= 0; v--) {
                newDown[v] = suffix;
                suffix = (suffix + up[v]) % MOD;
            }
            up = newUp;
            down = newDown;
        }
        long ans = 0;
        for (int v = 0; v < m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }
        return (int) ans;
    }
}