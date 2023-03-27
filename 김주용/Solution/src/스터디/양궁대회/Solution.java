package 스터디.양궁대회;

import java.util.Arrays;

public class Solution {

	static int maxScore = 0;
	static int[] ret;

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 });
		s.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 });
		s.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 });

	}

	public int[] solution(int n, int[] info) {
		int[] answer = {-1};
		dfs(n, info, new int[11], 0);
		System.out.println(maxScore);
		System.out.println(Arrays.toString(ret));
		if(maxScore < 0) {
			return answer;
		}
		answer = ret.clone();
		return answer;
	}

	public static void dfs(int n, int[] apeachInfo, int[] lionInfo, int cur) {
		if (n <= 0 || cur > 10) {
			lionInfo[10] += n;
			int curScore = getScore(lionInfo, apeachInfo);
			if (maxScore <= curScore) {
				if (maxScore == curScore && !isAnswer(lionInfo)) {
					return;
				}
				maxScore = curScore;
				ret = lionInfo.clone();
			}
			lionInfo[10] -= n;
			return;
		}

		if (n > apeachInfo[cur]) { // 남은 화살로 점수 먹을 수 있는 경우
			lionInfo[cur] = apeachInfo[cur] + 1;
			dfs(n - lionInfo[cur], apeachInfo, lionInfo, cur + 1);
		}
		// 무시하는 경우
		lionInfo[cur] = 0;
		dfs(n, apeachInfo, lionInfo, cur + 1);
	}

	public static int getScore(int[] lionArrows, int[] apeachArrows) {
		int lionScore = 0, apeachScore = 0;
		for (int i = 0; i < 11; i++) {
			if (lionArrows[i] > apeachArrows[i]) {
				lionScore += (10 - i);
			} else if (apeachArrows[i] > 0) {
				apeachScore += (10 - i);
			}
		}
		return lionScore - apeachScore;
	}

	public static boolean isAnswer(int[] lionInfo) {
		for (int i = 10; i >= 0; i--) {
			if (ret[i] > lionInfo[i]) {
				return false;
			} else if (ret[i] < lionInfo[i]) {
				return true;
			}
		}
		return false;
	}

}
