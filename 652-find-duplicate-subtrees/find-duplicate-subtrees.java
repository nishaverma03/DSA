class Solution {
    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode node) {
        if (node == null) {
            return "#";
        }

        String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right);

        int count = map.getOrDefault(serial, 0);
        if (count == 1) {
            ans.add(node);
        }
        map.put(serial, count + 1);

        return serial;
    }
}