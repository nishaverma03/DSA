class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;

        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        if (checkHorizontal(grid, total)) return true;
        return checkVertical(grid, total);
    }

    private boolean checkHorizontal(int[][] grid, long total) {
        int m = grid.length, n = grid[0].length;
        long sum = 0;
        HashMap<Integer, Integer> top = new HashMap<>();
        HashMap<Integer, Integer> bottom = new HashMap<>();

        for (int[] row : grid) {
            for (int val : row) {
                bottom.put(val, bottom.getOrDefault(val, 0) + 1);
            }
        }

        for (int r = 0; r < m - 1; r++) {
            for (int val : grid[r]) {
                sum += val;
                top.put(val, top.getOrDefault(val, 0) + 1);
                bottom.put(val, bottom.get(val) - 1);
            }

            long other = total - sum;

            if (sum == other) return true;

            if (sum > other && valid(grid, top, sum - other, 0, r, 0, n - 1)) {
                return true;
            }

            if (other > sum && valid(grid, bottom, other - sum, r + 1, m - 1, 0, n - 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkVertical(int[][] grid, long total) {
        int m = grid.length, n = grid[0].length;
        long sum = 0;
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();

        for (int[] row : grid) {
            for (int val : row) {
                right.put(val, right.getOrDefault(val, 0) + 1);
            }
        }

        for (int c = 0; c < n - 1; c++) {
            for (int r = 0; r < m; r++) {
                int val = grid[r][c];
                sum += val;
                left.put(val, left.getOrDefault(val, 0) + 1);
                right.put(val, right.get(val) - 1);
            }

            long other = total - sum;

            if (sum == other) return true;

            if (sum > other && valid(grid, left, sum - other, 0, m - 1, 0, c)) {
                return true;
            }

            if (other > sum && valid(grid, right, other - sum, 0, m - 1, c + 1, n - 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean valid(int[][] grid, HashMap<Integer, Integer> map, long diff,
                          int r1, int r2, int c1, int c2) {
        if (diff > Integer.MAX_VALUE) return false;

        int val = (int) diff;

        if (r1 < r2 && c1 < c2) {
            return map.getOrDefault(val, 0) > 0;
        }

        if (r1 == r2) {
            return grid[r1][c1] == val || grid[r1][c2] == val;
        }

        return grid[r1][c1] == val || grid[r2][c1] == val;
    }
}