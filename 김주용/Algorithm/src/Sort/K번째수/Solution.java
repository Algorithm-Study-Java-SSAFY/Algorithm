package Sort.K번째수;

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i = 0; i < commands.length; i++) {
            int[] com = commands[i];
            int[] temp = array.clone();
            Arrays.sort(temp, com[0] - 1, com[1]);
            answer[i] = temp[com[0] + com[2] - 2];
        }
        return answer;
    }
}