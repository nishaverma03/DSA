class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];
        int i = 0;

        while (i < n) {
            int t = target.charAt(i) - 'a';

            if (freq[t] > 0) {
                ans[i] = target.charAt(i);
                freq[t]--;
                i++;
            } else {
                for (int c = t + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        ans[i] = (char) ('a' + c);
                        freq[c]--;

                        int pos = i + 1;

                        for (int j = 0; j < 26; j++) {
                            while (freq[j] > 0) {
                                ans[pos++] = (char) ('a' + j);
                                freq[j]--;
                            }
                        }

                        return new String(ans);
                    }
                }

                break;
            }
        }

        for (int j = i - 1; j >= 0; j--) {
            freq[ans[j] - 'a']++;

            int t = target.charAt(j) - 'a';

            for (int c = t + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    ans[j] = (char) ('a' + c);
                    freq[c]--;

                    int pos = j + 1;

                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            ans[pos++] = (char) ('a' + k);
                            freq[k]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}