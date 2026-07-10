class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            for (char digit : String.valueOf(num).toCharArray()) {
                list.add(digit - '0');
            }
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}