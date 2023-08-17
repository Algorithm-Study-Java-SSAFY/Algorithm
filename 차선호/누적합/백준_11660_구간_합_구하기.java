package code_test;

import java.util.*;
import java.io.*;


public class Main {
	
	static int N, M;
	static int[][] graph;
	static int answer = 0;
	static int[][] mArr;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws Exception{
		init();
		
		//행 기준 먼저 누적 합
		for(int i=0;i<N;i++) {
			for(int j=1;j<N;j++) {
				graph[i][j] += graph[i][j-1]; 
			}
		}
		
		//열 기준 먼저 누적 합
		for(int j=0;j<N;j++) {
			for(int i=1;i<N;i++) {
				graph[i][j] += graph[i-1][j];
			}
		}
		
		for(int i=0;i<M;i++) {
			sb.append(calculation(mArr[i][0],mArr[i][1],mArr[i][2],mArr[i][3])+"\n");
		}
		
		System.out.println(sb);
		
		
	}
	
	static int calculation(int x1, int y1, int x2, int y2) {
		int answer = 0;
		if(x1-1>=0 && y1-1>=0) answer = graph[x2][y2]-graph[x1-1][y2]-graph[x2][y1-1]+graph[x1-1][y1-1];
		else if(x1-1>=0) answer = graph[x2][y2] - graph[x1-1][y2];
		else if(y1-1>=0) answer = graph[x2][y2] - graph[x2][y1-1];
		else answer = graph[x2][y2];
		return answer;
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		graph = new int[N][N];
		mArr = new int[M][4];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<4;j++) {
				mArr[i][j] = Integer.parseInt(input[j])-1;
			}
		}
		
		
	}
}