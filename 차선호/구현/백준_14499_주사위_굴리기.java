import java.util.*;
import java.io.*;



public class Main {
	
	
	static int N,M,K;
	static int[] NOW, OPS;
	static int[][] GRAPH;
	static int[][] MARBLE = {{0,0,0}
							,{0,0,0}
							,{0,0,0}
							,{0,0,0}
							}; // 초기 주사위
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	static StringBuffer sb;
	
	
	public static void main(String[] args) throws Exception{
		init();
		
		for(int op: OPS) {
			solution(op);
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}
	
	static void solution(int op) {
		int mx = NOW[0]+dx[op];
		int my = NOW[1]+dy[op];
		if(0<=mx&&mx<N&&0<=my&&my<M) {
			rotate(op);
			if(GRAPH[mx][my]==0) {
				GRAPH[mx][my] = MARBLE[1][1];
			}else {
				MARBLE[1][1] = GRAPH[mx][my];
				GRAPH[mx][my] = 0;
			}
			sb.append(MARBLE[3][1]+"\n");
			NOW[0] = mx;
			NOW[1] = my;
		}else {
			return;
		}
	}
	
	static void rotate(int dir) {
		if(dir==1) { //동쪽(오른쪽)
			int temp = MARBLE[1][0];
			for(int i=1;i<3;i++) {
				MARBLE[1][i-1] = MARBLE[1][i];
			}
			MARBLE[1][2] = MARBLE[3][1];
			MARBLE[3][1] = temp;
		}else if(dir==2) { //서쪽(왼쪽)
			int temp = MARBLE[1][2];
			for(int i=2;i>0;i--) {
				MARBLE[1][i] = MARBLE[1][i-1];
			}
			MARBLE[1][0] = MARBLE[3][1];
			MARBLE[3][1] = temp;
		}else if(dir==3) { //북쪽(위쪽)
			int temp = MARBLE[3][1];
			for(int i=3;i>0;i--) {
				MARBLE[i][1] = MARBLE[i-1][1];
			}
			MARBLE[0][1] = temp;
		}else { //남쪽(아래쪽)
			int temp = MARBLE[0][1];
			for(int i=0;i<3;i++) {
				MARBLE[i][1] = MARBLE[i+1][1];
			}
			MARBLE[3][1] = temp;
		}
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMKNOW = br.readLine().split(" ");
		N = Integer.parseInt(NMKNOW[0]);
		M = Integer.parseInt(NMKNOW[1]);
		NOW = new int[2];
		NOW[0] = Integer.parseInt(NMKNOW[2]);
		NOW[1] = Integer.parseInt(NMKNOW[3]);
		K = Integer.parseInt(NMKNOW[4]);
		GRAPH = new int[N][M];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = Integer.parseInt(input[j]);
			}
		}
		OPS = new int[K];
		String[] ops = br.readLine().split(" ");
		for(int i=0;i<K;i++) {
			OPS[i] = Integer.parseInt(ops[i]);
		}
		sb = new StringBuffer();
	}
}