class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int zeros = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        
        for (int t = 0; t <= n; t++) {
            long total = 1L * t * k;
            
            if (total < zeros) continue;
            if (((total - zeros) & 1) == 1) continue;
            
            long maxOdd = (t % 2 == 1) ? t : Math.max(0, t - 1);
            long maxEven = (t % 2 == 0) ? t : Math.max(0, t - 1);
            
            long capacity = 1L * zeros * maxOdd + 1L * (n - zeros) * maxEven;
            
            if (total <= capacity) {
                return t;
            }
        }
        
        return -1;
    }
}