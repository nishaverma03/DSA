import java.util.*;
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Character> maxHeap =
            new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));
        maxHeap.addAll(freq.keySet());
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            int count = freq.get(ch);
            result.append(String.valueOf(ch).repeat(count));
        }
        return result.toString();
    }
}