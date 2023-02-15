package codingtest;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(stk.nextToken());
		}
		;
		Arrays.sort(A); // 1. 탐색할 배열을 오름차순으로 정렬

		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(stk.nextToken());
		}
		;

		for (int i : B) {
			sb.append(binarySearch(A, i)).append('\n');
		}
		
		System.out.println(sb.toString());
	

	}

	static int binarySearch( int[] arr, int key) {
		int start = 0;
		int end = arr.length-1;
		int mid = (start+end)/2;
		
		while(start <= end) {
			mid = (start+end)/2;
			if(arr[mid] == key) {
				return 1;
			}
			else if(arr[mid] < key) {
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		return 0;
	}

}