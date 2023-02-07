package _Programmers;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[]{93, 30, 55}, new int[] {1, 30, 5})));
        System.out.println(Arrays.toString(s.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[] {1, 1, 1, 1, 1, 1})));
    }

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<int []> queue = new LinkedList<int[]>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add(new int[] {progresses[i], speeds[i]});
        }

        while (!queue.isEmpty()) {
            for (int[] e : queue) {
                e[0] += e[1];
            }
            int cnt = 0;
            while (queue.peek() != null && queue.peek()[0] >= 100) {
                queue.poll();
                cnt++;
            }

            if (cnt > 0){
                int[] newAnswer = new int[answer.length + 1];
                System.arraycopy(answer, 0, newAnswer, 0, answer.length);
                newAnswer[answer.length] = cnt;
                answer = newAnswer;
            }
        }

        return answer;
    }
}
