import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;

		Arrays.sort(lost);
		boolean[] check_lost = new boolean[n];
		for (int i = 0; i < lost.length; i++) {
			check_lost[lost[i] - 1] = true;
		}
		boolean[] check_reserve = new boolean[n];
		for (int i = 0; i < reserve.length; i++) {
			check_reserve[reserve[i] - 1] = true;
		}

		for (int i = 0; i < lost.length; i++) {
			int index = lost[i] - 1;
			if (check_reserve[index]) {
				check_reserve[index] = false;
				continue;
			} else if (index > 0 && check_reserve[index - 1]) {
				check_reserve[index - 1] = false;
				continue;
			} else if (index < n - 1 && check_reserve[index + 1]) {
				if (check_lost[index + 1]) {
					answer -= 1;
					continue;
				}
				check_reserve[index + 1] = false;
				continue;
			} else {
				answer -= 1;
			}
		}

        return answer;
    }
}
