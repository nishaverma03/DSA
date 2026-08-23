class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftQ = 0;
        int rightQ = 0;
        int diff = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                diff += c - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                diff -= c - '0';
            }
        }

        if (leftQ == rightQ) {
            return diff != 0;
        }

        if ((leftQ - rightQ) % 2 != 0) {
            return true;
        }

        return diff != 9 * (rightQ - leftQ) / 2;
    }
}