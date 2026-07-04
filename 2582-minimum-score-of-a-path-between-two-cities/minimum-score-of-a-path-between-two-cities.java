class Solution {
    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] road : roads) {
            union(road[0], road[1], parent);
        }

        int ans = Integer.MAX_VALUE;
        int root = find(1, parent);

        for (int[] road : roads) {
            if (find(road[0], parent) == root) {
                ans = Math.min(ans, road[2]);
            }
        }

        return ans;
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    private void union(int a, int b, int[] parent) {
        int rootA = find(a, parent);
        int rootB = find(b, parent);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}