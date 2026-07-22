import java.util.*;
class Group {
    int start;
    int length;

    public Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}

class SparseTable {
    private final int[][] st;

    public SparseTable(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            this.st = new int[1][0];
            return;
        }
        int maxLog = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        this.st = new int[maxLog][n];

        System.arraycopy(nums, 0, st[0], 0, n);
        for (int i = 1; i < maxLog; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
            }
        }
    }

    public int query(int l, int r) {
        if (l > r || st[0].length == 0) return 0;
        int i = Integer.SIZE - Integer.numberOfLeadingZeros(r - l + 1) - 1;
        return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
    }
}

public class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }
            zeroGroupIndex[i] = zeroGroups.size() - 1;
        }

        if (zeroGroups.size() < 2) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                res.add(ones);
            }
            return res;
        }

        int m = zeroGroups.size();
        int[] zeroMergeLengths = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            zeroMergeLengths[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable st = new SparseTable(zeroMergeLengths);
        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];

            int left = (zeroGroupIndex[l] == -1) ? -1 : (zeroGroups.get(zeroGroupIndex[l]).start + zeroGroups.get(zeroGroupIndex[l]).length - l);
            int right = (zeroGroupIndex[r] == -1) ? -1 : (r - zeroGroups.get(zeroGroupIndex[r]).start + 1);

            int startAdjacentGroupIndex = zeroGroupIndex[l] + 1;
            int endAdjacentGroupIndex = (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1) - 1;

            int activeSections = ones;
            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, ones + left + right);
            } 
            else if (startAdjacentGroupIndex <= endAdjacentGroupIndex) {
                activeSections = Math.max(activeSections, ones + st.query(startAdjacentGroupIndex, endAdjacentGroupIndex));
            }
            int limitR = (s.charAt(r) == '1') ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1;
            if (s.charAt(l) == '0' && zeroGroupIndex[l] + 1 <= limitR) {
                activeSections = Math.max(activeSections, ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }
            if (s.charAt(r) == '0' && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                activeSections = Math.max(activeSections, ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(activeSections);
        }

        return ans;
    }
}