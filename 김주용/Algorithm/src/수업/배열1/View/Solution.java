package 수업.배열1.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int[] heights = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int answer = solution(heights, N);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static int solution(int [] heights, int N) {
		int ret = 0;
		for(int i = 2; i < N-2; i++) {
			ArrayList<Integer> diffs = getDiffs(heights, i);
			
			if(diffs.size() == 4) {
				ret += getMax(diffs);
			}

		}
		return ret;
	}
	
	public static ArrayList<Integer> getDiffs(int[] heights, int idx) {
		int[] dists = new int[] {-2, -1, 1, 2};
		ArrayList<Integer> diffs = new ArrayList<>();
		for(int dist : dists) {
			int diff = heights[idx] - heights[idx + dist];
			if(diff > 0) {
				diffs.add(diff);
			}
		}
		return diffs;
	}

	public static int getMax(ArrayList<Integer> diffs) {
		int ret = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			ret = Math.min(ret, diffs.get(i));
		}
		return ret;
	}
}
