import java.util.*;

class Solution {
    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            minHeap.add(scoville[i]);
        }

        while (minHeap.peek() < K) {
            Integer fir = minHeap.poll();
            Integer sec = minHeap.poll();
            if (fir != null && sec != null) {
                minHeap.add(fir + (sec * 2));
                answer++;
            } else {
                answer = -1;
            }
        }
        // minHeap.forEach(System.out::println);
        return answer;
    }
}