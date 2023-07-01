package 수업.배열1.Flatten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int dumpCnt = Integer.parseInt(in.readLine());
			int[] heights = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int answer = solution(heights, dumpCnt);
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
	
	public static int solution(int[] heights, int dumpCnt) {
		int ret = 0;
		int min = getMin(heights);
		int max = getMax(heights);
		int cnt = 1;
		while(cnt <= dumpCnt) {
			int maxIndex = maxIndexOf(heights, max);
			int minIndex = minIndexOf(heights, min);
			if(maxIndex == -1) {
				max = getMax(heights);
				continue;
			}
			if(minIndex == -1) {
				min = getMin(heights);
				continue;
			}
			heights[maxIndex] -= 1;
			heights[minIndex] += 1;
			cnt += 1;
		}
		
		int retMin = getMin(heights);
		int retMax = getMax(heights);
		
		
		return retMax - retMin; 
	}
	
	public static int minIndexOf(int[] heights, int min) {
		for(int i = 0; i < 100; i++) {
			if(heights[i] == min) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static int maxIndexOf(int[] heights, int max) {
		for(int i = 0; i < 100; i++) {
			if(heights[i] == max) {
				return i;
			}
		}
		return -1;
	}
	
	public static int getMin(int [] heights) {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 100; i++) {
			min = Math.min(min, heights[i]);
		}
		return min;
	}
	
	public static int getMax(int [] heights) {
		int max = 0;
		for(int i = 0; i < 100; i++) {
			max = Math.max(max, heights[i]);
		}
		return max;
	}
	
}
