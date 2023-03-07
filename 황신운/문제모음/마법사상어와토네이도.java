package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 마법사상어와토네이도 {
	public static int N, cntSand = 0;
	public static int[][] map;
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,1,0,-1};
	// 5%, 10%, 10%, 2%, 2%, 7%, 7%, 1%, 1%, a
	public static int[][] sx = {{-2,-1,-1,0,0,0,0,1,1,-1}, {0,-1,1,-2,2,-1,1,-1,1,0}, {2,1,1,0,0,0,0,-1,-1,1}, {0,-1,1,-2,2,-1,1,-1,1,0}};
	public static int[][] sy = {{0,-1,1,-2,2,-1,1,-1,1,0}, {2,1,1,0,0,0,0,-1,-1,1}, {0,-1,1,2,-2,1,-1,-1,1,0}, {-2,-1,-1,0,0,0,0,1,1,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		tornado(N/2, N/2);
		System.out.println(cntSand);
	}
	
	public static void tornado(int r, int c) {
		int d = 0;
		for(int i=0; i<N; i++) {
			for(int turn=0; turn<2; turn++) {
				for(int j=0; j<=i; j++) {
					c += dx[d];
					r += dy[d];
					if(c == -1)
						break;

					sand(r,c,map[r][c], d);
					map[r][c] = 0;
				}
				d = (d+1)%4;
			}
		}
	}
	
	public static void sand(int r, int c, int sandAmount, int d) {
		int finalSand = sandAmount;
		
		int amount = (sandAmount * 5) /100;
		if(check(r + sy[d][0],c + sx[d][0])) map[r + sy[d][0]][c + sx[d][0]] += amount;
		else cntSand += amount;
		finalSand -= amount;
		
		amount = (sandAmount * 10)/100;
		if(check(r + sy[d][1],c + sx[d][1])) map[r + sy[d][1]][c + sx[d][1]] += amount;
		else cntSand += amount;
		if(check(r + sy[d][2],c + sx[d][2])) map[r + sy[d][2]][c + sx[d][2]] += amount;
		else cntSand += amount;
		finalSand -= amount*2;
		
		amount = (sandAmount * 2)/100;
		if(check(r + sy[d][3],c + sx[d][3])) map[r + sy[d][3]][c + sx[d][3]] += amount;
		else cntSand += amount;
		if(check(r + sy[d][4],c + sx[d][4])) map[r + sy[d][4]][c + sx[d][4]] += amount;
		else cntSand += amount;
		finalSand -= amount*2;
		
		amount = (sandAmount * 7)/100;
		if(check(r + sy[d][5],c + sx[d][5])) map[r + sy[d][5]][c + sx[d][5]] += amount;
		else cntSand += amount;
		if(check(r + sy[d][6],c + sx[d][6])) map[r + sy[d][6]][c + sx[d][6]] += amount;
		else cntSand += amount;
		finalSand -= amount*2;
		
		amount = (sandAmount * 1)/100;
		if(check(r + sy[d][7],c + sx[d][7])) map[r + sy[d][7]][c + sx[d][7]] += amount;
		else cntSand += amount;
		if(check(r + sy[d][8],c + sx[d][8])) map[r + sy[d][8]][c + sx[d][8]] += amount;
		else cntSand += amount;
		finalSand -= amount*2;
		
		if(check(r + sy[d][9],c + sx[d][9])) map[r + sy[d][9]][c + sx[d][9]] += finalSand;
		else cntSand += finalSand;
	}
	
	public static boolean check(int cr, int cc) {
		if(cr<0 || cr>=N || cc<0 || cc>=N) return false;
		return true;
	}
	
}
