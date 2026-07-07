class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        int sum = 0;

        for (char digit : String.valueOf(n).toCharArray()) {
            if (digit != '0') {
                int d = digit - '0';
                x = x * 10 + d;
                sum += d;
            }
        }

        return x * sum;
    }
}