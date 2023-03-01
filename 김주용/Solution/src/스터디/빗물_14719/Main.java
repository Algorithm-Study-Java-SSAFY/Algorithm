package 스터디.빗물_14719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int M;
	
	static int[] heights;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		line = in.readLine().split(" ");
		heights = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		solution();
	}
	/*
	 * 현재 높이 기준: Min(왼쪽 max 높이, 오른쪽 max 높이) - 현재 높이 
	 */
	public static void solution() {
		int ret = 0;
		int left = heights[0];
		int right = getRight(1);
		
		for(int i = 1; i < M-1; i++) {
			int cur = heights[i];
			left = (cur > left) ? cur : left;
			right = getRight(i);
			
			if(left <= cur || right <= cur) {
				continue;
			}
			ret += (Math.min(left, right) - cur);
		}
		System.out.println(ret);
	}
	
	public static int getRight(int idx) {
		int ret = 0;
		for(int i = idx + 1; i < M; i++) {
			ret = Math.max(ret, heights[i]);
		}
		return ret;
	}
}
