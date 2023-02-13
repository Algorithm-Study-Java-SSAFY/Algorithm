package com.study.self.Bj2075;

import java.io.*;
import java.util.*;

public class Bj2075 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String[] data = br.readLine().split(" "); //공백 기준으로 문자열 배열로 입력 받는다. ["45","34","12"] 이런 식
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(data[j]); // 각각의 문자열을 int로 변환하여 2차원 배열 각각 자리에 넣어준다.
			}
		}
		
		
		PriorityQueue<Integer> maxHip = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				maxHip.add(graph[i][j]);
			}
		}
		
		int answer = 0;
		for(int k=0;k<N;k++) {
			answer = maxHip.poll();
		}
		System.out.println(answer);
	}
}
