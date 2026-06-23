class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", ans);
        return ans;
    }
    private void dfs(String num, int target, int index,
                     long value, long prev,
                     String expr, List<String> ans) {
        if (index == num.length()) {
            if (value == target) {
                ans.add(expr);
            }
            return;
        }
        for (int i = index; i < num.length(); i++) {
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            String currStr = num.substring(index, i + 1);
            long curr = Long.parseLong(currStr);
            if (index == 0) {
                dfs(num, target, i + 1, curr, curr,
                        currStr, ans);
            } else {
                dfs(num, target, i + 1,
                        value + curr,
                        curr,
                        expr + "+" + currStr,
                        ans);
                dfs(num, target, i + 1,
                        value - curr,
                        -curr,
                        expr + "-" + currStr,
                        ans);
                dfs(num, target, i + 1,
                        value - prev + prev * curr,
                        prev * curr,
                        expr + "*" + currStr,
                        ans);
            }
        }
    }
}