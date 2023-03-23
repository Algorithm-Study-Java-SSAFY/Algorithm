package 스터디.양궁대회;

import java.util.Arrays;

public class Solution {

	static int answer = 0;
	static int maxScore = Integer.MIN_VALUE;
	static int[] ret;

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 });
		s.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 });

	}

	public int[] solution(int n, int[] info) {
		int[] answer = {};
		ret = new int[11];
		int apeachScore = 0;
		for (int i = 0; i < 11; i++) {
			if (info[i] > 0) {
				apeachScore += (10 - i);
			}
		}
		dfs(n, info, new boolean[11], new int[11], n, 0, apeachScore);
		System.out.println(Solution.maxScore);
		System.out.println(Arrays.toString(ret));
		System.out.println(Solution.answer);
		answer = ret;
		return answer;
	}

	public static void dfs(int n, int[] info, boolean[] visited, int[] arrows, int arrow, int lionScore,
			int apeachScore) {
		if (arrow == 0) {
			answer = Math.max(answer, lionScore - apeachScore);
			// System.out.println(Arrays.toString(arrows));
			if (maxScore <= lionScore - apeachScore && isAnswer(arrows) >= 0) {
				maxScore = lionScore - apeachScore;
				ret = arrows.clone();
			}

			return;
		}

		for (int i = 0; i < 11; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (info[i] > 0 && arrow > info[i]) { // 어피치가 맞춘 화살보다 1개 더 많이 맞추기
					arrows[i] = info[i] + 1;
					dfs(n, info, visited, arrows, arrow - arrows[i], lionScore + (10 - i), apeachScore - (10 - i));
				} else if (arrow == info[i]) { // 똑같이 맞춰서 어피치 점수 무효화 시키기
					arrows[i] = info[i];
					dfs(n, info, visited, arrows, arrow - arrows[i], lionScore, apeachScore);
				} else if (info[i] == 0) { // 1개 맞춰서 점수 먹는 경우
					arrows[i] = 1;
					dfs(n, info, visited, arrows, arrow - arrows[i], lionScore + (10 - i), apeachScore);
				} else { // 점수 포기
					arrows[i] = 0;
					dfs(n, info, visited, arrows, arrow - arrows[i], lionScore, apeachScore);
				}
				arrows[i] = 0;
				visited[i] = false;
			}
		}
	}
	
	public static int isAnswer(int[] newArrows) {
		int originIdx = -1;
		int newIdx = -1;

		for(int i = 0; i < 11; i++) {
			if(ret[i] > 0) {
				originIdx = i;
			}
			if(newArrows[i] > 0) {
				newIdx = i;
			}
		}
		if(answer == 29) {
			System.out.println(Arrays.toString(ret));
			System.out.println(Arrays.toString(newArrows));
			System.out.println(originIdx + " " + newIdx);
			System.out.println(" ");
		}
		return newIdx - originIdx;
	}

}
