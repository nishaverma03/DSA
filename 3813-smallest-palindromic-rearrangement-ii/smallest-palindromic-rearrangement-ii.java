class Solution {

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        String mid = "";

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
        }

        int halfLen = s.length() / 2;

        if (countWays(half, halfLen, k) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1, k);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String right = new StringBuilder(left).reverse().toString();
        return left.toString() + mid + right;
    }

    private long countWays(int[] cnt, int remaining, int cap) {
        long res = 1;
        int rem = remaining;

        for (int x : cnt) {
            if (x == 0) continue;

            long comb = combCap(rem, x, cap);
            res *= comb;

            if (res > cap) return cap;

            rem -= x;
        }

        return res;
    }

    private long combCap(int n, int r, int cap) {
        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            res *= num;

            if (res > cap) return cap;
        }

        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}