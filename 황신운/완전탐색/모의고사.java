import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] count = { 0, 0, 0 };
        int[] first = { 1, 2, 3, 4, 5 };
        int[] second = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] third = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        for (int i = 0; i < answers.length; i++) {
            if (first[i % 5] == answers[i]) {
                count[0] += 1;
            }
            if (second[i % 8] == answers[i]) {
                count[1] += 1;
            }
            if (third[i % 10] == answers[i]) {
                count[2] += 1;
            }
        }
        int max_num = Arrays.stream(count).max().getAsInt();
        int[] tmp = new int[3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            if (count[i] == max_num)
                tmp[index++] = i + 1;
        }
        answer = new int[index];
        System.arraycopy(tmp, 0, answer, 0, index);
        return answer;
    }
}