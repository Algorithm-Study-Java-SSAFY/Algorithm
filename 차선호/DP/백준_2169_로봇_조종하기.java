import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static int[][] GRAPH,DP;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(DP[N-1][M-1]);
	}
	
	static void solution() {
		
		makeDp();
		
//		for(int[] a:DP) System.out.println(Arrays.toString(a));
		
	}
	
	static void makeDp() {
		DP = new int[N][M];
		DP[0][0] = GRAPH[0][0];
		for(int i=1;i<M;i++) {
			DP[0][i] = DP[0][i-1]+GRAPH[0][i];
		}
		
		
		int[] leftDp, rightDp;
		for(int i=1;i<N;i++) {
			leftDp = new int[M]; //왼쪽방향 진행 과정 기록
			rightDp = new int[M]; //오른쪽 방향 진행 과정 기록
			leftDp[0] = DP[i-1][0] + GRAPH[i][0];
			rightDp[M-1] = DP[i-1][M-1] + GRAPH[i][M-1];
			for(int j=1;j<M;j++) {
				leftDp[j] = GRAPH[i][j] +Math.max(leftDp[j-1], DP[i-1][j]); //나부터 새로 시작, 진행해오던 것 중 큰 값 기록
				rightDp[M-1-j] = GRAPH[i][M-1-j] + Math.max(rightDp[M-1-j+1], DP[i-1][M-1-j]); //나부터 새로 시작, 진행해오던 것 중 큰 값 기록
			}
			for(int j=0;j<M;j++) {
				if(M==1) { //m이 1이면 좌우 진행과정 필요 없음
					DP[i][j] = DP[i-1][j]+GRAPH[i][j];
					continue;
				}
				//위에서 온 거, 왼쪽에서 온 거, 오른쪽에서 온 거 중 가장 큰 값으로 기록
				if(j==0) DP[i][j] = GRAPH[i][j] + Math.max(DP[i-1][j], rightDp[j+1]);
				else if(j==M-1) DP[i][j] = GRAPH[i][j] + Math.max(DP[i-1][j], leftDp[j-1]);
				else {
					DP[i][j] = Math.max(GRAPH[i][j]+DP[i-1][j], GRAPH[i][j]+rightDp[j+1]);
					DP[i][j] = Math.max(DP[i][j], GRAPH[i][j]+leftDp[j-1]);
				}
			}
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		GRAPH = new int[N][M];
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = Integer.parseInt(input[j]);
			}
		}
	}
}