package 기출문제.톱니바퀴;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static String[] gears = new String[4];
	static int K;
	static int[][] commands;

	static int[] dd = { -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			gears[i] = in.readLine();
		}

		K = Integer.parseInt(in.readLine());
		commands = new int[K][2];
		for (int i = 0; i < K; i++) {
			String[] line = in.readLine().split(" ");
			commands[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}

		solution();
	}

	public static void solution() {
		for (int i = 0; i < K; i++) {
			int[] cur = commands[i];
			int idx = cur[0] - 1, direct = cur[1];
			int nearDirect = (cur[1] == 1) ? -1 : 1;

			int[] visited = getVisited(idx, direct);
			for (int near = 0; near < 4; near++) {
				if (visited[near] != 0 && near != idx) {
					rotateController(near, visited[near]);
				}
			}
			rotateController(idx, direct);
		}

		int answer = 0;
		for (int i = 0; i < 4; i++) {
			char cur = gears[i].charAt(0);
			if (cur == '1') {
				answer += Math.pow(2, i);
			}
		}
		System.out.println(answer);
	}

	// 시계 방향 회전
	public static void rotate(int idx) {
		gears[idx] = gears[idx].charAt(7) + gears[idx].substring(0, 7);
	}

	// 반시계 방향 회전
	public static void rotateReverse(int idx) {
		gears[idx] = gears[idx].substring(1) + gears[idx].charAt(0);
	}

	// 회전 유무 판단
	public static boolean isRotate(int idx1, int idx2) {
		char a = gears[idx1].charAt(2);
		char b = gears[idx2].charAt(6);
		if (a != b) {
			return true;
		}
		return false;
	}

	public static void rotateController(int idx, int direct) {
		if (direct == 1) {
			rotate(idx);
		} else {
			rotateReverse(idx);
		}
	}

	public static int[] getVisited(int idx, int direct) {
		// 영향 받는 우측 톱니바퀴
		int[] visited = new int[4];
		visited[idx] = direct;

		for (int near = idx + 1; near < 4; near++) {
			if (isRotate(near - 1, near)) {
				visited[near] = -visited[near - 1];
			} else {
				break;
			}
		}
		// 영향 받는 좌측 톱니바퀴
		for (int near = idx - 1; near >= 0; near--) {
			if (isRotate(near, near + 1)) {
				visited[near] = -visited[near + 1];
			} else {
				break;
			}
		}
		return visited;
	}

}
