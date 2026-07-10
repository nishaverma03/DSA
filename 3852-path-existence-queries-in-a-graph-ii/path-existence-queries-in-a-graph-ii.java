class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] sorted = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[order[i]];
            rank[order[i]] = i;
        }

        int log = 18;
        int[][] jump = new int[log][n];
        int right = 0;

        for (int i = 0; i < n; i++) {
            while (right + 1 < n &&
                   (long) sorted[right + 1] - sorted[i] <= maxDiff) {
                right++;
            }
            jump[0][i] = right;
        }

        for (int k = 1; k < log; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int left = rank[queries[q][0]];
            int target = rank[queries[q][1]];

            if (left > target) {
                int temp = left;
                left = target;
                target = temp;
            }

            if (left == target) {
                answer[q] = 0;
                continue;
            }

            int current = left;
            int distance = 0;

            for (int k = log - 1; k >= 0; k--) {
                if (jump[k][current] < target) {
                    current = jump[k][current];
                    distance += 1 << k;
                }
            }

            if (jump[0][current] >= target) {
                answer[q] = distance + 1;
            } else {
                answer[q] = -1;
            }
        }

        return answer;
    }
}