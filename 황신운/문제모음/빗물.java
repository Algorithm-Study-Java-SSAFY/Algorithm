package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 빗물 {
	public static int N;
	public static int M;
	public static int startEnd;
	public static int finalEnd;
	public static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		
		arr = new int[M];
		tmp = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		
		int answer = 0;
		for(int i=1; i<M-1; i++) {
			int result = findDrawned(i) - arr[i];
//			System.out.println("i" + i + " result " + result);
			if(result > 0)
				answer += result;
		}
		System.out.println(answer);
	}
	
	public static int findDrawned(int idx) {
		int rainCnt = 0;
		int height1 = 0;
		for(int i=idx-1; i>=0; i--) {
			height1 = Math.max(height1, arr[i]);
		}
		
		int height2 = 0;
		for(int i=idx+1; i<=M-1; i++) {
			height2 = Math.max(height2, arr[i]);
		}
		
		rainCnt = Math.min(height1, height2);
		
		return rainCnt;
	}

}
