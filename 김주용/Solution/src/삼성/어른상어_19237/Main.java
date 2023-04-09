package 삼성.어른상어_19237;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Shark implements Comparable<Shark> {
	int num;
	int y;
	int x;
	int d;
	int[][] directInfo;

	public Shark(int num, int y, int x) {
		this.num = num;
		this.y = y;
		this.x = x;
	}

	public void setNext(int y, int x, int d) {
		this.y = y;
		this.x = x;
		this.d = d;
	}

	@Override
	public int compareTo(Shark o) {
		return this.num - o.num;
	}

	@Override
	public String toString() {
		return "Shark [num=" + num + ", y=" + y + ", x=" + x + ", d=" + d + ", directInfo="
				+ Arrays.toString(directInfo[0]) + "]";
	}

}

class Smell {
	int num;
	int time;

	public Smell(int num, int time) {
		this.num = num;
		this.time = time;
	}
}

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] board; // 상어 위치 저장
	static PriorityQueue<Shark>[][] sharkBoard;
	static Smell[][] smells; // 냄새 정보 저장
	static Shark[] sharkInfo;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		K = line[2];
		board = new int[N][N];
		smells = new Smell[N][N];
		sharkInfo = new Shark[M + 1];
		sharkBoard = new PriorityQueue[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				sharkBoard[i][j] = new PriorityQueue<>();
				if (board[i][j] == 0) {
					continue;
				}
				sharkInfo[board[i][j]] = new Shark(board[i][j], i, j);
			}
		}
		// 상어 초기 방향
		int[] initDirect = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int idx = 0;
		// 방향 정보
		for (Shark cur : sharkInfo) {
			if (cur == null) {
				continue;
			}
			cur.d = initDirect[idx++] - 1;
			cur.directInfo = new int[4][4];
			for (int i = 0; i < 4; i++) {
				line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < 4; j++) {
					cur.directInfo[i][j] = line[j] - 1;
				}
			}
		}
		int answer = solution();
		System.out.println(answer);
	}

	public static int solution() {
		int answer = 0;
		while (answer <= 1000 && !isFinish()) {
			setSmell();
			moveShark();
			HashSet<Integer> remain = getRemainShark();
			removeShark(remain);
			removeSmell();
			answer++;
		}
		if (answer > 1000) {
			return -1;
		}
		return answer;
	}

	// 상어가 자신의 위치에 냄새 뿌림
	public static void setSmell() {
		for (Shark cur : sharkInfo) {
			if (cur == null) {
				continue;
			}
			smells[cur.y][cur.x] = new Smell(cur.num, K);
		}
	}

	// 냄새 제거
	public static void removeSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (smells[i][j] == null) {
					continue;
				}
				smells[i][j].time--;
				if (smells[i][j].time == 0) {
					smells[i][j] = null;
				}
			}
		}
	}

	// 상어가 인접 칸으로 이동
	public static void moveShark() {
		for (int i = 1; i <= M; i++) {
			Shark cur = sharkInfo[i];
			if (cur == null) {
				continue;
			}
			int[] next = findEmpty(cur);
			if (next == null) {
				next = findMySmell(cur);
			}
			// 원래 있던 곳은 초기화
			board[cur.y][cur.x] = 0;
			int ny = next[0], nx = next[1], nd = next[2];
			cur.setNext(ny, nx, nd);
			// 우선 순위 큐 안에 넣기
			sharkBoard[ny][nx].add(cur);
		}
	}

	// 우선 순위에 따라 이동할 칸 찾기 1. 빈칸
	public static int[] findEmpty(Shark cur) {
		int[] curDirectInfo = cur.directInfo[cur.d];
		int y = cur.y, x = cur.x;
		for (int nd : curDirectInfo) {
			int ny = y + dy[nd], nx = x + dx[nd];
			if (!inRange(ny, nx)) {
				continue;
			}
			if (board[ny][nx] == 0 && smells[ny][nx] == null) { // 빈칸
				return new int[] { ny, nx, nd };
			}
		}
		return null;
	}

	// 우선 순위에 따라 이동할 칸 찾기 2. 자기 냄새
	public static int[] findMySmell(Shark cur) {
		int[] curDirectInfo = cur.directInfo[cur.d];
		int y = cur.y, x = cur.x;
		for (int nd : curDirectInfo) {
			int ny = y + dy[nd], nx = x + dx[nd];
			if (!inRange(ny, nx)) {
				continue;
			}
			if (smells[ny][nx].num == cur.num) { // 자신의 냄새
				return new int[] { ny, nx, nd };
			}
		}
		return null;
	}

	// 가장 작은 번호 상어만 남기고 남은 상어 번호 리턴
	public static HashSet<Integer> getRemainShark() {
		HashSet<Integer> remainShark = new HashSet<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (sharkBoard[i][j].size() == 0) {
					continue;
				}
				board[i][j] = sharkBoard[i][j].poll().num;
				// 남은 상어들
				remainShark.add(board[i][j]);
				sharkBoard[i][j].clear();
			}
		}
		return remainShark;
	}

	// 남은 상어 외에 퇴출 당한 상어 지우기
	public static void removeShark(HashSet<Integer> remain) {
		ArrayList<Integer> removeNums = new ArrayList<>();
		for (Shark cur : sharkInfo) {
			if (cur == null) {
				continue;
			}
			if (!remain.contains(cur.num)) {
				removeNums.add(cur.num);
			}
		}
		for (Integer idx : removeNums) {
			sharkInfo[idx] = null;
		}

	}

	public static boolean isFinish() {
		int cnt = 0;
		for (int i = 1; i <= M; i++) {
			if (sharkInfo[i] != null) {
				cnt++;
			}
		}
		return cnt == 1;
	}

	public static boolean inRange(int y, int x) {
		return -1 < y && y < N && -1 < x && x < N;
	}

	public static void debug(String msg) {
		System.out.println(msg);
		for (int[] i : board) {
			System.out.println(Arrays.toString(i));
		}
		System.out.println(" ");
	}
}
