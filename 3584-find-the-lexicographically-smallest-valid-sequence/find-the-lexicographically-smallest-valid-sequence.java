class Solution {
    public int[] validSequence(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int n = c1.length;
        int m = c2.length;

        int[] dp = new int[n + 1];
        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && c1[i] == c2[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];
        int i = 0;
        j = 0;

        while (i < n && j < m) {
            if (c1[i] == c2[j]) {
                ans[j++] = i;
            } else {
                if (dp[i + 1] >= m - j - 1) {
                    ans[j++] = i;
                    i++;
                    break;
                }
            }
            i++;
        }

        if (j < m && i == n) {
            return new int[0];
        }

        while (i < n && j < m) {
            if (c1[i] == c2[j]) {
                ans[j++] = i;
            }
            i++;
        }

        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}