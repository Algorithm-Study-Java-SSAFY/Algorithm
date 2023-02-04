import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int scov : scoville) {
            heap.add(scov);
        }
        int firstScov, secondScov;
        while (true) {
            firstScov = heap.poll();
            if (firstScov >= K) {
                break;
            } else if (!heap) {
                answer = -1;
                break;
            }
            secondScov = heap.poll();
            heap.add(firstScov + (secondScov * 2));
            answer++;
        }
        return answer;
    }
}