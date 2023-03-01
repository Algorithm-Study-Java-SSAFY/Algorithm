import java.util.*;
import java.io.*;


public class Main {

	/*
	 *  7명으로 구성
	 *  7명은 가로 세로 반드시 인접해 있어야 함 
	 *   7명 중 이다솜파가 적어도 4명 이상은 포함되어야 한다. 
	 *   이다솜파 : S (2)
	 *   임도연파 : Y (5)
	 */
	
	static int[][] board = new int[5][5]; // 이다솜 2 / 임도연 5
	static int[][] numberBoard = new int[5][5];
	static HashSet<String> visitSet = new HashSet<>();
	static String path;
	static int[][] visited = new int[5][5];
	static int[] DY = { 1, -1, 0, 0 };
	static int[] DX = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < 5; j++) {
				if (s[j].equals("Y"))
					board[i][j] = 5;
				else {
					board[i][j] = 2;
				}
			}
		}
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				numberBoard[i][j] = k;
				k++;
			}
		}

		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				visited[i][j] = 1;
				String p ="";
				p = p + i+ j;
				DFS(new int[] {i,j}, 0, 0, p);
				visited[i][j] = 0;
			}
		}
		
		System.out.println(visitSet.size());
	}
	
	static void DFS(int[] p ,int deep ,int cnt ,String path) {
		
		deep++; 
		if(board[p[0]][p[1]] == 2) cnt++;
		
		
		if(deep == 7) {
			if(cnt >= 4) {
				
				List<Integer> list = new ArrayList<Integer>();
				String res = "";
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (visited[i][j] == 1) {
							list.add(numberBoard[i][j]);
						}
					}
				}
				Collections.sort(list);
				for(int i : list) {
					res += String.valueOf(i);
				}
				visitSet.add(res);
			}
			return; 
		}
		
		for(int i=0; i<path.length(); i+=2) {
			int y = Integer.parseInt(String.valueOf(path.charAt(i)));
			int x = Integer.parseInt(String.valueOf(path.charAt(i+1)));
		
			for(int j=0; j<4; j++) {
				int dy = y + DY[j];
				int dx = x + DX[j];
				if(0 <= dy && dy < 5 && 0 <= dx && dx < 5 && visited[dy][dx]==0) {
					visited[dy][dx] = 1;
					DFS(new int[] {dy,dx}, deep, cnt, (path+dy+dx));
					visited[dy][dx] = 0;
				}
				
			}
		}
		
		

		
		
		
	}
	
	

}