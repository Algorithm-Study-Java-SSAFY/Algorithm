import java.io.*;
import java.util.*;

class Solution {
    static int[] apeach;
	static int[] ryan = new int[11];
	static int[] answer = new int[11];
	static int maxGap, arrowCnt;
    
    public int[] solution(int n, int[] info) {
        if (info[0] == n) {
			System.out.println(-1);
			return new int[] {-1};
		} else {

			apeach = info;
			arrowCnt = n;

			boolean[] win = new boolean[11];

			dfs(win, 0, n);

			if(maxGap == 0)
				return new int[] {-1};
			else
				return answer;
		}
    }
    
    public static void dfs(boolean[] win, int depth, int arrow) {
		if (depth == 11 || arrow == 0) {
			int ryanScore = 0, apeachScore = 0;
			arrow = arrowCnt;

			for (int i = 0; i < 11; i++) {
				ryan[i] = 0;
				if (i == 10 && arrow > 0) {
					ryan[i] = arrow;
					break;
				}

				if (win[i] && arrow > apeach[i]) {
					ryan[i] = apeach[i] + 1;
					arrow -= apeach[i] + 1;
				}

				if (ryan[i] > apeach[i])
					ryanScore += 10 - i;
				else if (apeach[i] >= ryan[i] && (apeach[i] != 0 || ryan[i] != 0))
					apeachScore += 10 - i;
			}

			if (apeachScore < ryanScore) {
				checkGap(ryanScore - apeachScore);
			}

			return;
		}

		if (apeach[depth] < arrow) {
			// 지는 경우
			win[depth] = false;
			dfs(win, depth + 1, arrow);

			// 이기는 경우
			win[depth] = true;
			arrow -= apeach[depth] + 1;
			dfs(win, depth + 1, arrow);

		} else {
			win[depth] = false;
			dfs(win, depth + 1, arrow);
		}
	}

	public static void checkGap(int gap) {
		if (maxGap < gap) {
			for (int i = 0; i < 11; i++) {
				answer[i] = ryan[i];
			}
			maxGap = gap;
		} else if (maxGap == gap) {
			for (int i = 10; i >= 0; i--) {
				if (answer[i] > ryan[i])
					break;
				else if (answer[i] < ryan[i]) {
					for (int j = 0; j < 11; j++) {
						answer[j] = ryan[j];
					}
					break;
				}
			}
		}
	}
}
