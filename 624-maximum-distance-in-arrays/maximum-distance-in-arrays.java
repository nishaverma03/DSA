class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> first = arrays.get(0);

        int min = first.get(0);
        int max = first.get(first.size() - 1);

        int ans = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> curr = arrays.get(i);

            int currMin = curr.get(0);
            int currMax = curr.get(curr.size() - 1);

            ans = Math.max(ans, currMax - min);
            ans = Math.max(ans, max - currMin);

            min = Math.min(min, currMin);
            max = Math.max(max, currMax);
        }

        return ans;
    }
}