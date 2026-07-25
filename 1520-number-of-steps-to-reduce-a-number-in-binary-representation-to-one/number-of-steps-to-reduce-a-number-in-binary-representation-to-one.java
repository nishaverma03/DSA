class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        int n = s.length();
        for (int i = n - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';

            if (bit + carry == 1) {
                steps += 2;  
                carry = 1;
            } else {
                steps += 1;  
            }
        }
        return steps + carry;
    }
}