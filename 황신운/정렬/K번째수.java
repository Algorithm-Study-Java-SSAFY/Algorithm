import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] tmp;
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int target = commands[i][2] - 1;

            tmp = new int[end - start + 1];
            System.arraycopy(array, start, tmp, 0, tmp.length);
            Arrays.sort(tmp);
            answer[i] = tmp[target];
        }
        return answer;
    }
}