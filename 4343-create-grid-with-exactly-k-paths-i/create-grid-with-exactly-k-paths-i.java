class Solution {
    public String[] createGrid(int m, int n, int k) {
        char[][] grid = new char[m][n];
        for (char[] row : grid) Arrays.fill(row, '#');
        
        if (k == 1) {
            buildStraightFrom(grid, 0, 0, m, n);
            return toResult(grid);
        }
        
        if (m < 2 || n < 2) return new String[0];
        
        if (k == 2) {
            openBlock(grid, 0, 0, 2, 2);
            buildStraightFrom(grid, 1, 1, m, n);
            return toResult(grid);
        }
        
        if (k == 3) {
            if (m < 3 && n < 3) return new String[0];
            if (n >= 3) {
                openBlock(grid, 0, 0, 2, 3);
                buildStraightFrom(grid, 1, 2, m, n);
            } else {
                openBlock(grid, 0, 0, 3, 2);
                buildStraightFrom(grid, 2, 1, m, n);
            }
            return toResult(grid);
        }
        if (n >= 4) {
            openBlock(grid, 0, 0, 2, 4);
            buildStraightFrom(grid, 1, 3, m, n);
        } else if (m >= 4) {
            openBlock(grid, 0, 0, 4, 2);
            buildStraightFrom(grid, 3, 1, m, n);
        } else if (m >= 3 && n >= 3) {
            openBlock(grid, 0, 0, 2, 2);
            openBlock(grid, 1, 1, 2, 2);
            buildStraightFrom(grid, 2, 2, m, n);
        } else {
            return new String[0];
        }
        return toResult(grid);
    }
    
    private void openBlock(char[][] grid, int r0, int c0, int rows, int cols) {
        for (int i = r0; i < r0 + rows; i++)
            for (int j = c0; j < c0 + cols; j++)
                grid[i][j] = '.';
    }
    
    private void buildStraightFrom(char[][] grid, int r0, int c0, int m, int n) {
        for (int j = c0; j < n; j++) grid[r0][j] = '.';
        for (int i = r0; i < m; i++) grid[i][n - 1] = '.';
    }
    
    private String[] toResult(char[][] grid) {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) result[i] = new String(grid[i]);
        return result;
    }
}