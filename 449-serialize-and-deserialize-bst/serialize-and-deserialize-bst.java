public class Codec {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        sb.append(root.val).append(",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    int idx = 0;

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] arr = data.split(",");
        idx = 0;
        return build(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(String[] arr, int min, int max) {
        if (idx == arr.length) return null;

        int val = Integer.parseInt(arr[idx]);

        if (val < min || val > max) return null;

        idx++;

        TreeNode root = new TreeNode(val);
        root.left = build(arr, min, val);
        root.right = build(arr, val, max);

        return root;
    }
}