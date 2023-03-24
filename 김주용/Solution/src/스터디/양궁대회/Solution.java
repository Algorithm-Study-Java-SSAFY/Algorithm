package 스터디.양궁대회;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	static class Result implements Comparable<Result> {
		int score;
		int[] arrows;

		public Result(int score, int[] arrows) {
			this.score = score;
			this.arrows = arrows;
		}

		@Override
		public int compareTo(Result o) {
			if (this.score > o.score) {
				return -1;
			}
			if (this.score == o.score) {
				if (getIndex(o) <= 0) {
					return -1;
				}
			}
			return 1;
		}

		public int getIndex(Result o) {
			int thisIdx = -1;
			int otherIdx = -1;

			for (int i = 0; i < 11; i++) {
				if (this.arrows[i] > 0) {
					thisIdx = i;
				}
				if (o.arrows[i] > 0) {
					otherIdx = i;
				}
			}
			return otherIdx - thisIdx;
		}

		@Override
		public String toString() {
			return "Result [score=" + score + ", arrows=" + Arrays.toString(arrows) + "]";
		}

	}

	static PriorityQueue<Result> rets = new PriorityQueue<>();
	static int x = 0;

	public static void main(String[] args) {
		Solution s = new Solution();
		 s.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 });
		// s.solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 });
		//s.solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 });

	}

	public int[] solution(int n, int[] info) {
		int[] answer = {};

		int apeachScore = 0;
		for (int i = 0; i < 11; i++) {
			if (info[i] > 0) {
				apeachScore += (10 - i);
			}
		}

		dfs(0, info, new boolean[11], new int[11], n, 0, apeachScore);

		Result ret = rets.peek();
		if (ret.score <= 0) {
			return new int[] { -1 };
		}
		System.out.println(ret);
		System.out.println(x);
		answer = ret.arrows;
		return answer;
	}

	public static void dfs(int n, int[] info, boolean[] visited, int[] arrows, int arrow, int lionScore,
			int apeachScore) {
		if (arrow == 0) {
			x = Math.max(x, lionScore - apeachScore);
			rets.add(new Result(lionScore - apeachScore, arrows.clone()));
			System.out.println(Arrays.toString(arrows));
			return;
		}

		for (int i = n; i < 11; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (arrow >= info[i]) { // 어피치가 맞춘 화살보다 1개 더 많이 맞추는 경우 
					arrows[i] = info[i] + 1;
					dfs(i+1, info, visited, arrows, arrow - arrows[i], lionScore + (10 - i), apeachScore - (10 - i));
				} 
				if (info[i] == 0 && arrow > 0) { // 1개 맞춰서 점수 먹는 경우
					arrows[i] = 1;
					dfs(i+1, info, visited, arrows, arrow - arrows[i], lionScore + (10 - i), apeachScore);
				} 
				// 무시하고 하는 경우 
				arrows[i] = 0;
				dfs(i+1, info, visited, arrows, arrow - arrows[i], lionScore, apeachScore);
				
				arrows[i] = 0;
				visited[i] = false;
			}
		}
	}

}
