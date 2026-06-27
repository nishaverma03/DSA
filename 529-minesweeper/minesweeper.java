class Solution {

    int[][] dirs = {
        {-1,-1}, {-1,0}, {-1,1},
        {0,-1},          {0,1},
        {1,-1},  {1,0},  {1,1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        dfs(board, r, c);
        return board;
    }

    private void dfs(char[][] board, int r, int c) {
        int m = board.length;
        int n = board[0].length;

        if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'E')
            return;

        int mines = countMines(board, r, c);

        if (mines > 0) {
            board[r][c] = (char)(mines + '0');
            return;
        }

        board[r][c] = 'B';

        for (int[] d : dirs) {
            dfs(board, r + d[0], c + d[1]);
        }
    }

    private int countMines(char[][] board, int r, int c) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
                board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }
}