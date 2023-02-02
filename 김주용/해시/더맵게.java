import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int element : scoville) {
            minHeap.add(element);
        }
        
        while (minHeap.peek() >= K) {
            Integer firstMin = minHeap.poll();
            Integer secondMin = minHeap.poll();
            if(firstMin == null || secondMin == null) {
                answer = -1;
                break;
            }
            int newScoville = firstMin + 2 * secondMin;
            minHeap.add(newScoville);
            answer += 1;
        }
        return answer;
    }
}