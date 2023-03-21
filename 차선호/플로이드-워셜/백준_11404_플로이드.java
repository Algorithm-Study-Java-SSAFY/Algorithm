import java.util.*;
import java.io.*;


public class Main {

	static int N,M;
	static int[][] distance;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws Exception{
		init();
		
		
		floydWarshall();
		
		
		for(int i=0;i<N;i++) {
			String s = "";
			for(int j=0;j<N;j++) {
				if(distance[i][j]==10_000_001) distance[i][j] = 0;
				if(j==0) s += distance[i][j]+"";
				else s += " "+distance[i][j]; 
			}
			sb.append(s+"\n");
		}
		
		System.out.println(sb);
		
	}
	
	
	static void floydWarshall() {
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k]+distance[k][j]);
				}
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		distance = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i==j) distance[i][j] = 0;
				else distance[i][j] = 10_000_001;
			}
		}
		
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0])-1;
			int node2 = Integer.parseInt(input[1])-1;
			int cost = Integer.parseInt(input[2]);
			
			distance[node1][node2] = Math.min(distance[node1][node2], cost);
		}
		
	}
}