import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] total = new int[n + 1];

        for (int i = 0; i < reserve.length; i++) {
            total[reserve[i]] += 1;
        }
        for (int i = 0; i < lost.length; i++) {
            total[lost[i]] -= 1;
        }

        for (int i = 1; i < n + 1; i++) {
            if (total[i] == -1) {
                if (total[i - 1] == 1) {
                    total[i] = 0;
                    total[i - 1] = 0;
                } else if (i < n && total[i + 1] == 1) {
                    total[i] = 0;
                    total[i + 1] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (total[i] != -1)
                answer++;
        }
        return answer;
    }
}