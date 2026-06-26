class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        int pigs = 0;
        int combinations = 1;
        while (combinations < buckets) {
            combinations *= states;
            pigs++;
        }
        return pigs;
    }
}