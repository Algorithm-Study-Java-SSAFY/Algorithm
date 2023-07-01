package 스터디.개똥벌레_3020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int H;
	static int[] topHeights;
	static int[] botHeights;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		H = Integer.parseInt(line[1]);
		topHeights = new int[N/2];
		botHeights = new int[N/2];
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(in.readLine());
			if(i % 2 == 0) { botHeights[i/2] = height; }
			else { topHeights[(i-1)/2] = height; }
		}
		solution();
	}
	
	public static void solution() {
		Arrays.sort(topHeights);	
		Arrays.sort(botHeights);
		
		/*
		 * 장애물 정렬 : 현재 높이에서 얼만큼 부수는지 이분 탐색으로 찾아야 함. 
		 * -> 현재 높이보다 장애물이 커지는 구간. 
		 */
		
		int minCrush = Integer.MAX_VALUE;
		int[] crushs = new int[H+1];
		for(int i = 1; i <= H; i++) {
			int botCrush = N/2 - binarySearch(botHeights, i);
			int topCrush = N/2 - binarySearch(topHeights, H-i+1);
			int crush = topCrush + botCrush;
			crushs[i] = crush;
			
			minCrush = Math.min(minCrush, crush);
		}
		int ret = 0;
		for(int i = 1; i <= H; i++) {
			if(crushs[i] == minCrush) {
				ret++;
			}
		}
		System.out.println(minCrush + " " + ret);
	}
	
	//logN(N): 높이보다 크거나 같아지는 idx-1 지점 탐색 
	public static int binarySearch(int[] heights, int height) {	 
		int left = 0, right = N / 2;
		while(left < right) {
			int mid = (left + right) / 2;
			if(heights[mid] < height) {
				left = mid + 1;
			} else if(heights[mid] >= height) {	
				right = mid;
			} 
		}
	
		return right;
	}

}
