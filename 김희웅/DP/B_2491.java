package DP;

import java.io.*;
import java.util.*;

class B_2491 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	
		st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[N];
		int upMax = 0;
		int downMax = 0;
		int upCnt = 1;
		int downCnt = 1;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N - 1; i++) {
			//오름차순 부분
			if (arr[i] > arr[i + 1]) { //오름차순 인데? 내림 만나면
				upCnt = 1;
			} else {
				upCnt++;
			}

			//내림차순 부분
			if (arr[i] < arr[i + 1]) { //내림차순 인데? 오름 만나면
				downCnt = 1;
			} else {
				downCnt++;
			}
			
			if (upCnt >= upMax) {
				upMax = upCnt;
			}
			if (downCnt >= downMax) {
				downMax = downCnt;
			}
		}
		if (N == 1)
			System.out.println(1);
		else
			System.out.println(upMax > downMax ? upMax : downMax);
	}

}