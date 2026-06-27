class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> freq = new HashMap<>();
        for (int x : nums) {
            long v = x;
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        int ans = 1;
        if (freq.containsKey(1L)) {
            int c = freq.get(1L);
            ans = Math.max(ans, (c % 2 == 1) ? c : c - 1);
        }
        for (long start : freq.keySet()) {
            if (start == 1L) continue;
            long cur = start;
            int len = 0;
            while (true) {
                long next = cur * cur;
                if (freq.getOrDefault(cur, 0) >= 2 &&
                    next <= 1_000_000_000L &&
                    freq.containsKey(next)) {
                    len += 2;
                    cur = next;
                } else {
                    break;
                }
            }
            if (freq.getOrDefault(cur, 0) >= 1) {
                ans = Math.max(ans, len + 1);
            }
        }
        return ans;
    }
}