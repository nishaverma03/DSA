import java.util.*;

class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        left.offer(num);
        right.offer(left.poll());

        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        }

        return ((double) left.peek() + right.peek()) / 2.0;
    }
}