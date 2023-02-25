
import java.util.*;
import java.io.*;

public class Main {

	/*
	 * 1. 치즈 밖 부분을 BFS를 통해 체크 => 치즈 안 공기를 구분 2. 하루 지나면 치즈 제거 후 다시 1번과정 반복 -> 치즈안 공기가
	 * 밖으로 나오는 경우
	 */
	static int N, M; // 세로 가로
	static int[][] board;
	static List<Integer> cheezeCnt = new ArrayList<Integer>(); // 시간별 남아 있는 치즈 개수
	static int[] DY = { 0, 0, -1, 1 };
	static int[] DX = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		board = new int[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String[] data = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(data[j]);
				if (board[i][j] == 1)
					cnt++;
			}
		}
		cheezeCnt.add(cnt);


		while (cheezeCnt.get(cheezeCnt.size() - 1) != 0) { // 치즈가 모두 없어질때 까지
			outArea(); // 외부 영역 표시하고
			cheeze(); // 겉 치즈 제거하고
			cheezeCount(); // 현재 남은 치즈 개수 check
		}
		
		System.out.println(cheezeCnt.size()-1);
		System.out.println(cheezeCnt.get(cheezeCnt.size()-2));
		
	}

	// 외부 영역을 표시 - (2)
	static void outArea() {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		int[][] visited = new int[N][M];
		dq.add(new int[] {0,0});
		visited[0][0] = 1;
		board[0][0] = 2;
		
		// 2면 dq에 넣고 계속 탐색 -> 0 나오면 2로 변경 
		while(!dq.isEmpty()) {
			int[] yx = dq.removeFirst();
			int y = yx[0];
			int x = yx[1];
			for (int i=0; i<4; i++) {
				int dy = y+DY[i];
				int dx = x+DX[i];
				if(0<=dy && dy<N && 0<=dx && dx<M) {
					if(visited[dy][dx]==0 && board[dy][dx]==0) {
						visited[dy][dx] = 1; 
						board[dy][dx] = 2;
						dq.add(new int[] {dy,dx});
					}else if(visited[dy][dx]==0 && board[dy][dx]==2) {
						visited[dy][dx] = 1;
						dq.add(new int[] {dy,dx});
					}
				}
			}
		}
	
	}

	// 겉 치즈 제거
	static void cheeze() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (board[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int dy = i + DY[k];
						int dx = j + DX[k];
						if (0 <= dy && dy < N && 0 <= dx && dx < M) {
							if (board[dy][dx] == 2) // 외부랑 접촉한 치즈라면 ..
								board[i][j] = 0;
						}
					}
				}
			}
		}
		

	}

	// 치즈 개수 확인 -> cheezeCnt에 추가
	static void cheezeCount() {
		int cnt = 0; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j]==1) cnt++;  
			}
		}
		cheezeCnt.add(cnt);
	}
	

}