class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;
        int[][] maxScore = new int[n][n];
        long[][] ways = new long[n][n];
        for (int[] row : maxScore) {
            Arrays.fill(row, -1);
        }
        maxScore[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1)) {
                    continue;
                }
                int best = -1;
                long count = 0;
                int[][] directions = {{1, 0}, {0, 1}, {1, 1}};
                for (int[] dir : directions) {
                    int ni = i + dir[0];
                   int nj = j + dir[1];
                    if (ni < n && nj < n && maxScore[ni][nj] != -1) {
                        if (maxScore[ni][nj] > best) {
                            best = maxScore[ni][nj];
                            count = ways[ni][nj];
                        } else if (maxScore[ni][nj] == best) {
                            count = (count + ways[ni][nj]) % mod;
                        }
                    }
                }
                if (best == -1) {
                    continue;
                }
                char ch = board.get(i).charAt(j);
                int value = (ch == 'E') ? 0 : ch - '0';

                maxScore[i][j] = best + value;
                ways[i][j] = count;
            }
        }
        if (maxScore[0][0] == -1) {
            return new int[]{0, 0};
        }
        return new int[]{maxScore[0][0], (int) ways[0][0]};
    }
}