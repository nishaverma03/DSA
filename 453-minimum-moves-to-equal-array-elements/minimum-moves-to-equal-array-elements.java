class Solution {
    public int minMoves(int[] nums) {
        // Find the smallest element
        int smallestNum = Integer.MAX_VALUE;

        for(int num : nums){
            smallestNum = Math.min(num, smallestNum);
        }

        // Calculate the total moves required
        int minMoves = 0;

        for(int num : nums){
            minMoves += num - smallestNum;
        }
        
        return minMoves;
    }
}