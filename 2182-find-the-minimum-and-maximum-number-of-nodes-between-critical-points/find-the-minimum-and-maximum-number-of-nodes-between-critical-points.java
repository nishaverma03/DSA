class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int min = Integer.MAX_VALUE;
        int index = 1;

        ListNode before = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            if ((curr.val > before.val && curr.val > curr.next.val) ||
                (curr.val < before.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    first = index;
                }

                if (prev != -1) {
                    min = Math.min(min, index - prev);
                }

                prev = index;
            }

            before = curr;
            curr = curr.next;
            index++;
        }

        if (first == -1 || prev == first) {
            return new int[]{-1, -1};
        }

        int max = prev - first;

        return new int[]{min, max};
    }
}