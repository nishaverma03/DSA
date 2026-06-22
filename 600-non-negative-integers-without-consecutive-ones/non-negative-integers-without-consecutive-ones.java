class Solution {
    public int findIntegers(int n) {
        int[] fib = new int[32];

        fib[0] = 1;
        fib[1] = 2;

        for (int i = 2; i < 32; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        int ans = 0;
        int prevBit = 0;

        for (int i = 30; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                ans += fib[i];

                if (prevBit == 1) {
                    return ans;
                }

                prevBit = 1;
            } else {
                prevBit = 0;
            }
        }

        return ans + 1;
    }
}