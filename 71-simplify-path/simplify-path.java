class Solution {
    public String simplifyPath(String path) {
        ArrayDeque<String> dq = new ArrayDeque<>();
        for (String part : path.split("/")) {
            if (part.length() == 0 || part.equals(".")) continue;
            if (part.equals("..")) {
                if (!dq.isEmpty()) dq.removeLast();
            } else {
                dq.addLast(part);
            }
        }
        if (dq.isEmpty()) return "/";

        StringBuilder ans = new StringBuilder();

        while (!dq.isEmpty()) {
            ans.append('/').append(dq.removeFirst());
        }
        return ans.toString();
    }
}