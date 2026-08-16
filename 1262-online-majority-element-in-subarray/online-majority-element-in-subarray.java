import java.util.*;

class MajorityChecker {
    private int[] tree;
    private int[] count;
    private int[] arr;
    private Map<Integer, List<Integer>> positions;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        tree = new int[4 * n];
        count = new int[4 * n];
        positions = new HashMap<>();

        for (int i = 0; i < n; i++) {
            positions.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        build(1, 0, n - 1);
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            count[node] = 1;
            return;
        }

        int mid = left + (right - left) / 2;
        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        merge(node, node * 2, node * 2 + 1);
    }

    private void merge(int node, int a, int b) {
        if (tree[a] == tree[b]) {
            tree[node] = tree[a];
            count[node] = count[a] + count[b];
        } else if (count[a] > count[b]) {
            tree[node] = tree[a];
            count[node] = count[a] - count[b];
        } else {
            tree[node] = tree[b];
            count[node] = count[b] - count[a];
        }
    }

    private int[] queryTree(int node, int left, int right, int ql, int qr) {
        if (ql <= left && right <= qr) {
            return new int[]{tree[node], count[node]};
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return queryTree(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return queryTree(node * 2 + 1, mid + 1, right, ql, qr);
        }

        int[] a = queryTree(node * 2, left, mid, ql, qr);
        int[] b = queryTree(node * 2 + 1, mid + 1, right, ql, qr);

        if (a[0] == b[0]) {
            return new int[]{a[0], a[1] + b[1]};
        } else if (a[1] > b[1]) {
            return new int[]{a[0], a[1] - b[1]};
        } else {
            return new int[]{b[0], b[1] - a[1]};
        }
    }

    public int query(int left, int right, int threshold) {
        int candidate = queryTree(1, 0, arr.length - 1, left, right)[0];

        List<Integer> list = positions.get(candidate);
        int start = lowerBound(list, left);
        int end = upperBound(list, right);

        if (end - start >= threshold) {
            return candidate;
        }

        return -1;
    }

    private int lowerBound(List<Integer> list, int target) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (list.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (list.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}