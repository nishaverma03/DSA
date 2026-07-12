class Solution {
static final long MOD = 1_000_000_007L;
static final long INV2 = 500000004L;
private long needOps(long have, long need, int k) {
if (have >= need) return 0;
return (need - have + k - 1L) / k;
}
private long rangeSum(long first, long cnt) {
long last = first + cnt - 1;
long x = (first + last) % MOD;
long y = cnt % MOD;
 return (((x * y) % MOD) * INV2) % MOD;
}
public int minimumCost(int[] nums, int k) {
long resource = k;
long done = 0;
long ans = 0;
for (int x : nums) {
long add = needOps(resource, x, k);
if (add > 0) {
ans += rangeSum(done + 1, add);
ans %= MOD;
done += add;
resource += add * (long) k;
}
resource -= x;
}
 return (int) ans;
}
}