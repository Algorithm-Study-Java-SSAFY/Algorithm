import java.util.*;
import java.io.*;



public class Main{
	
	static int N,M;
	static int[][] GRAPH;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception {
		init();
		
		solution();
		
		System.out.println(ANSWER);
	}
	
	static void solution() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(check1_1(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j+1]+GRAPH[i][j+2]+GRAPH[i][j+3]);
				if(check1_2(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+2][j]+GRAPH[i+3][j]);
				if(check2(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i][j+1]+GRAPH[i+1][j+1]); 
				if(check3_1(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+2][j]+GRAPH[i+2][j+1]); 
				if(check3_1_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+2][j]+GRAPH[i+2][j-1]); 
				if(check3_2(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j+1]+GRAPH[i][j+2]+GRAPH[i-1][j+2]); 
				if(check3_2_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j-1]+GRAPH[i][j-2]+GRAPH[i-1][j-2]); 
				if(check3_3(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i-1][j]+GRAPH[i-2][j]+GRAPH[i-2][j-1]); 
				if(check3_3_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i-1][j]+GRAPH[i-2][j]+GRAPH[i-2][j+1]); 
				if(check3_4(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j-1]+GRAPH[i][j-2]+GRAPH[i+1][j-2]); 
				if(check3_4_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j+1]+GRAPH[i][j+2]+GRAPH[i+1][j+2]); 
				if(check4_1(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+1][j+1]+GRAPH[i+2][j+1]); 
				if(check4_1_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+1][j-1]+GRAPH[i+2][j-1]); 
				if(check4_2(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j-1]+GRAPH[i+1][j-1]+GRAPH[i+1][j-2]); 
				if(check4_2_mirror(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j+1]+GRAPH[i+1][j+1]+GRAPH[i+1][j+2]); 
				if(check5_1(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j+1]+GRAPH[i+1][j+1]+GRAPH[i][j+2]); 
				if(check5_2(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+1][j-1]+GRAPH[i+2][j]); 
				if(check5_3(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i][j-1]+GRAPH[i-1][j-1]+GRAPH[i][j-2]); 
				if(check5_4(i,j)) ANSWER = Math.max(ANSWER, GRAPH[i][j]+GRAPH[i+1][j]+GRAPH[i+1][j+1]+GRAPH[i+2][j]); 
			}
			
		}
	}
	
	static boolean check1_1(int x, int y) {
		if(y+3<M) return true;
		else return false;
	}
	
	static boolean check1_2(int x, int y) {
		if(x+3<N) return true;
		else return false;
	}
	
	static boolean check2(int x, int y) {
		if(x+1<N && y+1<M) return true;
		else return false;
	}
	
	static boolean check3_1(int x, int y) {
		if(x+2<N && y+1<M) return true;
		else return false;
	}
	
	static boolean check3_1_mirror(int x, int y) {
		if(x+2<N && y-1>=0) return true;
		else return false;
	}
	
	static boolean check3_2(int x, int y) {
		if(x-1>=0 && y+2<M) return true;
		else return false;
	}
	
	static boolean check3_2_mirror(int x, int y) {
		if(x-1>=0 && y-2>=0) return true;
		else return false;
	}

	static boolean check3_3(int x, int y) {
		if(x-2>=0 && y-1>=0) return true;
		else return false;
	}
	
	static boolean check3_3_mirror(int x, int y) {
		if(x-2>=0 && y+1<M) return true;
		else return false;
	}
	
	static boolean check3_4(int x, int y) {
		if(x+1<N && y-2>=0) return true;
		else return false;
	}
	
	static boolean check3_4_mirror(int x, int y) {
		if(x+1<N && y+2<M) return true;
		else return false;
	}
	
	static boolean check4_1(int x, int y) {
		if(x+2<N && y+1<M) return true;
		else return false;
	}
	
	static boolean check4_1_mirror(int x, int y) {
		if(x+2<N && y-1>=0) return true;
		else return false;
	}
	
	static boolean check4_2(int x, int y) {
		if(x+1<N && y-2>=0) return true;
		else return false;
	}
	
	static boolean check4_2_mirror(int x, int y) {
		if(x+1<N && y+2<M) return true;
		else return false;
	}
	
	static boolean check5_1(int x, int y) {
		if(x+1<N && y+2<M) return true;
		else return false;
	}
	
	static boolean check5_2(int x, int y) {
		if(x+2<N && y-1>=0) return true;
		else return false;
	}
	
	static boolean check5_3(int x, int y) {
		if(x-1>=0 && y-2>=0) return true;
		else return false;
	}
	
	static boolean check5_4(int x, int y) {
		if(x+2<N && y+1<M) return true;
		else return false;
	}
	
	
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
		
		ANSWER = 0;
	}
	
}