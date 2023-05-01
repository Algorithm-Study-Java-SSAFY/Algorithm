import java.util.*;
import java.io.*;


public class Main {
	
	static int N,M;
	static int[][] GRAPH;
	static long ANSWER;
	
	
	public static void main(String[] args) throws Exception{
		init();
		//첫 번째 네모가 가로 또는 세로 가득 못 채우는 경우
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-1;j++) {
				int[] start1 = {0,0};
				int[] end1 = {i,j};
				getHorizon(start1, end1);
				getVertical(start1, end1);
			}
		}
		//이제 첫 번째 네모가 세로 또는 가로로 가득 차는 경우
		//가로로 가득 채우는 경우
		horizon();
		
		
		//세로로 가득 채우는 경우
		vertical();
		
		
		System.out.println(ANSWER);
	}
	
	static void vertical() {
		for(int i=0;i<M-1;i++) {
			int[] start1 = {0,0};
			int[] end1 = {N-1,i};
			if(i!=M-2) {
				for(int j=i+1;j<M-1;j++) {
					int[] start2 = {0,i+1};
					int[] end2 = {N-1,j};
					int[] start3 = {0,end2[1]+1};
					int[] end3 = {N-1,M-1};
	//				System.out.println("<---vertical--->");
	//				debug(start1,end1,start2,end2,start3,end3);
					ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
	//				System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
	//				System.out.println();
				}
			}
			for(int j=0;j<N-1;j++) {
				int[] start2 = {0,i+1};
				int[] end2 = {j,M-1};
				int[] start3 = {end2[0]+1,start2[1]};
				int[] end3 = {N-1,M-1};
//				System.out.println("<---vertical--->");
//				debug(start1,end1,start2,end2,start3,end3);
				ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//				System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//				System.out.println();
			}
		}
	}
	
	static void horizon() {
		for(int i=0;i<N-1;i++) {
			int[] start1 = {0,0};
			int[] end1 = {i,M-1};
			if(i!=N-2) {
				for(int j=i+1;j<N-1;j++) {
					int[] start2 = {i+1,0};
					int[] end2 = {j,M-1};
					int[] start3 = {end2[0]+1,0};
					int[] end3 = {N-1,M-1};
	//				System.out.println("<---horizon--->");
	//				debug(start1,end1,start2,end2,start3,end3);
					ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
	//				System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
	//				System.out.println();
				}
			}
			for(int j=0;j<M-1;j++) {
				int[] start2 = {i+1,0};
				int[] end2 = {N-1,j};
				int[] start3 = {start2[0],end2[1]+1};
				int[] end3 = {N-1,M-1};
//				System.out.println("<---horizon--->");
//				debug(start1,end1,start2,end2,start3,end3);
				ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//				System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//				System.out.println();
			}
		}
	}
	
	static void debug(int[] start1, int[] end1, int[] start2, int[] end2, int[] start3, int[] end3) {
		System.out.println("one-->"+Arrays.toString(start1)+", "+Arrays.toString(end1));
		System.out.println("two-->"+Arrays.toString(start2)+", "+Arrays.toString(end2));
		System.out.println("three-->"+Arrays.toString(start3)+", "+Arrays.toString(end3));
		System.out.println("--------------------------------------------");
	}
	
	
	static void getVertical(int[] start1, int[] end1) {
		int[] start2 = {end1[0]+1,start1[1]};
		int[] end2 = {N-1,end1[1]};
		int[] start3 = {start1[0],end1[1]+1};
		int[] end3 = {N-1,M-1};
//		System.out.println("<---getvertical-->");
//		debug(start1,end1,start2,end2,start3,end3);
		ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//		System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//		System.out.println();
	}
	
	static void getHorizon(int[] start1, int[] end1) {
		int[] start2 = {start1[0],end1[1]+1};
		int[] end2 = {end1[0],M-1};
		int[] start3 = {end1[0]+1,start1[1]};
		int[] end3 = {N-1,M-1};
//		System.out.println("<---gethorizon-->");
//		debug(start1,end1,start2,end2,start3,end3);
		ANSWER = Math.max(ANSWER, SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//		System.out.println(SUM(start1,end1)*SUM(start2,end2)*SUM(start3,end3));
//		System.out.println();
	}
	
	
	static long SUM(int[] start, int[] end) {
		long result = 0;
		for(int i=start[0];i<end[0]+1;i++) {
			for(int j=start[1];j<end[1]+1;j++) {
				result += GRAPH[i][j];
			}
		}
		return result;
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		GRAPH = new int[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				GRAPH[i][j] = input.charAt(j)-'0';
			}
		}
	}
}