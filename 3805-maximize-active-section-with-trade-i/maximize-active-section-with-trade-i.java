class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int originalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') originalOnes++;
        }
        String t = "1" + s + "1";
        int m = t.length();
        java.util.ArrayList<Character> chars = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> lens = new java.util.ArrayList<>();
        int i = 0;
        while (i < m) {
            int j = i;
            while (j < m && t.charAt(j) == t.charAt(i)) j++;
            chars.add(t.charAt(i));
            lens.add(j - i);
            i = j;
        }
        int maxZeroRun = 0;
        for (int k = 0; k < chars.size(); k++) {
            if (chars.get(k) == '0') {
                maxZeroRun = Math.max(maxZeroRun, lens.get(k));
            }
        }
        int ans = originalOnes;
        for (int k = 1; k + 1 < chars.size(); k++) {
            if (chars.get(k) != '1') continue;
            if (chars.get(k - 1) == '0' && chars.get(k + 1) == '0') {
                int leftZero = lens.get(k - 1);
                int rightZero = lens.get(k + 1);
                int oneLen = lens.get(k);

                int gain = Math.max(leftZero + rightZero, maxZeroRun - oneLen);
                ans = Math.max(ans, originalOnes + gain);
            }
        }
        return ans;
    }
}