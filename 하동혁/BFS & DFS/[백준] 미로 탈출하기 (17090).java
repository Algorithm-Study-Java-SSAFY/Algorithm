import java.util.*;
import java.io.*;

/*
 * U : 위 
 * R : 오 
 * D : 다운
 * L : 왼 
 * 각 칸마다 출발하여 칸에 적힌대로 이동한다. 
 * 만약 이동하다가 미로 밖, 즉 벽으로 이동하면 미로탈출이다. 
 * 탈출 가능한 미로의 개수를 구해야 한다. 
 * 
 * BFS 탐색을 수행한다. 
 * BFS를 하며 시작노드가 다른 노드를 지나 밖으로 탈출한다는 것은 그 지나는 노드들을 모두 그 노드들 
 * 부터 시작해도 나갈 수 있다는 뜻이기 때문이다. 
 * 따라서 반대로 지나가는 노드들을 밝고 나갈 수 없다면 그 노드들도 모두 나갈 수 없다. 
 * 왜냐? 주변 노드의 영향을 받지 않고 자기 자신이 가리키는 방향으로만 가기 때문이다. 
 */

public class Main {

	static int N, M;
	static String[][] board;
	static int[][] visited;
	static List<int[]> visitList;
	static int[][] outBoard; // 탈출에 성공한 노드를 기록
	static List<int[]> passRoute = new ArrayList<int[]>();
	

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		board = new String[N][M];
		outBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().split("");
		}
		visited = new int[N][M];

		// -----------------------------------------------------------

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (outBoard[i][j] == 0) {
					
					visitList = new ArrayList<int[]>();
					boolean tf = BFS(i, j);
					
					if (tf) {
						successRoute();
					} else {
						failedRoute();
					}
				}
			}
		}
		
		int cnt=0; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (outBoard[i][j] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	// 미로 찾기 ㄱㄱ
	static boolean BFS(int y, int x) {
		Deque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] { y, x });
		visited[y][x] = 1;

		while (!dq.isEmpty()) {
			int[] out = dq.removeFirst();
			visitList.add(out);
			int dy = out[0];
			int dx = out[1];
			visited[dy][dx] = 1;
			if(outBoard[dy][dx] == 1) {
				System.out.println(dy+"/"+dx);
				return true; 
			}else if(outBoard[dy][dx] == -1) {
				System.out.println(dy+"/"+dx);
				return false;
			}

			if (board[dy][dx].equals("U")) { // 위
				if(dy-1<0) return true;
				else if(dy-1>=0 && visited[dy-1][dx]==0) dq.add(new int[] { dy - 1, dx });
				else return false;
			} else if (board[dy][dx].equals("R")) { // 오른쪽
				if(dx+1>=M) return true;
				else if(dx+1<M && visited[dy][dx+1]==0) dq.add(new int[] { dy, dx + 1 });
				else return false; 
			} else if (board[dy][dx].equals("D")) { // 아래
				if(dy+1>=N) return true;
				else if(dy+1<N && visited[dy+1][dx]==0) dq.add(new int[] { dy + 1, dx });
				else return false; 
			} else if (board[dy][dx].equals("L")) { // 왼쪽
				if(dx-1<0) return true; 
				else if(dx-1>=0 && visited[dy][dx-1]==0) dq.add(new int[] { dy, dx - 1 });
				else return false;
			}

		}
		return false;

	}

	// 탈출에 성공했다면 성공한 루트(visited)를 outBoard에 기록
	static void successRoute() {
		for (int i = 0; i < visitList.size(); i++) {
			outBoard[visitList.get(i)[0]][visitList.get(i)[1]] = 1;
			visited[visitList.get(i)[0]][visitList.get(i)[1]] = 0;
		}
	}

	static void failedRoute() {
		for (int i = 0; i < visitList.size(); i++) {
			outBoard[visitList.get(i)[0]][visitList.get(i)[1]] = -1;
			visited[visitList.get(i)[0]][visitList.get(i)[1]] = 0;
		}
	}
	

}