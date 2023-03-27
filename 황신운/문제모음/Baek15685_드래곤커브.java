package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 규칙을 발견해서 풀어야하는 문제 
public class Baek15685_드래곤커브 {
	public static int[][] temp_map;
	public static int[][] final_map;
	public static int[][] testMap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][4];
		
		for(int i=0; i<N; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				info[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		final_map = new int[100][100];
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			temp_map = new int[100][100];
			int x = info[i][0];
			int y = info[i][1];
			int d = info[i][2];
			int generation = info[i][3];
			
			// 0세대인 경우
			temp_map[y][x] = 1;
			startDir(x, y, d);
			
			// 세대 수 만큼 for
			for(int turn=0; turn<generation; turn++) {
				testMap = new int[100][100];
				turnNinty();	// temp map에 있는 값을 90도로 돌려 testMap에 입력
				addOnMap();		// test map에 구한 다음 generation 값을 temp map에 합치
			}
			
			// 하나의 드래콘 커브 완료 -> 최종 map에 입력
			putOnFinal();
		}
		// 최종 맵에서 1*1 정사각형 갯수 구하기
		answer = countSqr();
		System.out.println(answer);
	}
	
	public static void startDir(int x, int y, int dir) {
		if(dir == 0) {
			temp_map[y][x+1] = 1;
		} else if(dir == 1) {
			temp_map[y-1][x] = 1;
		} else if(dir == 2) {
			temp_map[y][x-1] = 1;
		} else {
			temp_map[y+1][x] = 1;
		}
	}
	
	// 90도로 바꾸는 부분 다시
	public static void turnNinty() {
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				testMap[j][99-i] = temp_map[i][j];
			}
		}
	}
	
	public static void addOnMap() {
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(testMap[i][j] == 1)
					temp_map[i][j] = 1;
			}
		}
	}
	
	public static void putOnFinal() {
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				final_map[i][j] = temp_map[i][j];
			}
		}
	}
	
	public static int countSqr() {
		int sqrCnt = 0;
		boolean[][] visited = new boolean[100][100];
		int[] dx = {1,1,0};
		int[] dy = {0,1,1};
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(final_map[i][j] == 1) {
					// 1인 경우 주변 점 4개 조회
					boolean check = true;
					for(int p=0; p<3; p++) {
						int cx = j + dx[p];
						int cy = i + dy[p];
						if(cx>=0 && cx<100 && cy>=0 && cy<100) {
							if(final_map[cy][cx] != 1)
								check = false;
						}
					}
					if(check)
						sqrCnt++;
				}
			}
		}
		return sqrCnt;
	}
}
