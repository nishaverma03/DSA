import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            seen.add(num);
        }

        List<Integer> ans = new ArrayList<>();

        for (int x = min; x <= max; x++) {
            if (!seen.contains(x)) {
                ans.add(x);
            }
        }

        return ans;
    }
}