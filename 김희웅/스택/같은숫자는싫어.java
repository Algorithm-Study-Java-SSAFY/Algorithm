import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer;

        Stack<Integer> stack = new Stack<>();

        for (int i : arr) {
            if (stack.isEmpty() || stack.peek() != i)
                stack.push(i);
        }

        answer = new int[stack.size()];

        for (int i = answer.length - 1; i > -1; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}