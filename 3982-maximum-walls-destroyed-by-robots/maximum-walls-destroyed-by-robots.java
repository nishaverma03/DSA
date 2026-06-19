import java.util.*;
class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] r = new int[n][2];
        for (int i = 0; i < n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }
        Arrays.sort(r, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(walls);
        long[] left = new long[n];
        long[] right = new long[n];
        for (int i = 0; i < n; i++) {
            long pos = r[i][0];
            long dist = r[i][1];
            long L = pos - dist;
            if (i > 0) {
                L = Math.max(L, (long) r[i - 1][0] + 1);
            }
            left[i] = countWalls(walls, L, pos);
            long R = pos + dist;
            if (i < n - 1) {
                R = Math.min(R, (long) r[i + 1][0] - 1);
            }
            right[i] = countWalls(walls, pos, R);
        }
        long[] overlap = new long[Math.max(0, n - 1)];
        for (int i = 0; i < n - 1; i++) {
            long pos1 = r[i][0];
            long pos2 = r[i + 1][0];
            long rL = pos1;
            long rR = Math.min(pos1 + (long) r[i][1], pos2 - 1L);
            long lL = Math.max(pos2 - (long) r[i + 1][1], pos1 + 1L);
            long lR = pos2;
            long start = Math.max(rL, lL);
            long end = Math.min(rR, lR);
            if (start <= end) {
                overlap[i] = countWalls(walls, start, end);
            }
        }
        long dpLeft = left[0];
        long dpRight = right[0];
        for (int i = 1; i < n; i++) {
            long newLeft = Math.max(
                    dpLeft + left[i],
                    dpRight + left[i] - overlap[i - 1]
            );
            long newRight = Math.max(
                    dpLeft + right[i],
                    dpRight + right[i]
            );
            dpLeft = newLeft;
            dpRight = newRight;
        }
        return (int) Math.max(dpLeft, dpRight);
    }
    private long countWalls(int[] walls, long left, long right) {
        if (left > right) return 0;
        int l = lowerBound(walls, left);
        int r = upperBound(walls, right);
        return r - l;
    }
    private int lowerBound(int[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int upperBound(int[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}