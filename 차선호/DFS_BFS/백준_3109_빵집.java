import java.util.*;
import java.io.*;


public class Main {
	
	static int R,C;
	static char[][] GRAPH;
	static int[] DX = {-1,0,1};
	static int[] DY = {1,1,1};
	static int FINISH;
	static int ANSWER;
	
	public static void main(String[] args) throws Exception{
		init();
		for(int i=0;i<R;i++) {
			FINISH = 0; 
			GRAPH[i][0] = 'A';
			dfs(i,0);
		}
		System.out.println(ANSWER);
	}
	
	static void dfs(int x, int y) {
		for(int i=0;i<3;i++) {
			if(FINISH==1) return;
			int mx = x+DX[i];
			int my = y+DY[i];
			if(inGraph(mx,my) && GRAPH[mx][my]=='.') {
				GRAPH[mx][my] = 'A';
//				for(char[] a:GRAPH) System.out.println(Arrays.toString(a));
//				System.out.println("-----------------------");
				if(my==C-1) {
					ANSWER++;
					FINISH = 1;
				}
				dfs(mx, my);
			}
		}
	}
	
	static boolean inGraph(int x, int y) {
		if(0<=x&&x<R&&0<=y&&y<C) return true;
		else return false;
	}
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]);
		C = Integer.parseInt(RC[1]);
		GRAPH = new char[R][C];
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				GRAPH[i][j] = input.charAt(j);
			}
		}
		FINISH = 0;
		ANSWER = 0;
	}
}