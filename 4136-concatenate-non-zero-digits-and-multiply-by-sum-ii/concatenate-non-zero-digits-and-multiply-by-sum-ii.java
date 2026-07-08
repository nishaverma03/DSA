class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        long mod = 1000000007L;
        int n = s.length();

        int[] count = new int[n + 1];
        long[] sum = new long[n + 1];
        long[] value = new long[n + 1];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            count[i + 1] = count[i];
            sum[i + 1] = sum[i];
            value[i + 1] = value[i];

            if (digit != 0) {
                count[i + 1]++;
                sum[i + 1] += digit;
                value[i + 1] = (value[i] * 10 + digit) % mod;
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int len = count[r + 1] - count[l];
            long digitSum = sum[r + 1] - sum[l];

            long x = (value[r + 1]
                    - value[l] * pow10[len] % mod
                    + mod) % mod;

            answer[i] = (int) (x * digitSum % mod);
        }

        return answer;
    }
}