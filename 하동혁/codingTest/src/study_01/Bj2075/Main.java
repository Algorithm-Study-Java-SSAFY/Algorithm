package study_01.Bj2075;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 최대힙 
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i=0; i<n; i++) {
			// 한 줄 입력받아서 " " 공백 하나 기준으로나누어 토근에 담기 
			StringTokenizer stk = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<n; j++) {
				// 토근을 하나씩 빼서 큐에 담기 
				pq.add(Integer.parseInt(stk.nextToken()));
			}
		}
		
		for(int i=0; i<n-1; i++) {
			pq.remove();
		}
		System.out.println(pq.remove());
	}

}
