package 스터디.주사위굴리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;


public class Main {
	static HashMap<Character, Integer> dice = new HashMap<Character, Integer>() {
		{
			put('D', 0);
			put('U', 0);
			put('N', 0);
			put('E', 0);
			put('W', 0);
			put('S', 0);
		}
	};
	
	static int N;
	static int M;
	static int y;
	static int x;
	static int K;

	static int[][] board;
	static int[] directs;

	static int[] dy = { 0, 0, 0, -1, 1 };
	static int[] dx = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		y = line[2];
		x = line[3];
		K = line[4];
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		directs = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		solution();
	}
	
	public static void solution() {
		for(int d : directs) {
			int ny = y + dy[d], nx = x + dx[d];
			if(-1 < ny && ny < N && -1 < nx && nx < M) {
				rotateDice(d);
				if(board[ny][nx] == 0) {
					board[ny][nx] = dice.get('D');
				} else {
					dice.put('D', board[ny][nx]);
					board[ny][nx] = 0;
				}
				System.out.println(dice.get('U'));
				y = ny;
				x = nx;
			}
			
		}
	}
	
	public static void rotateDice(int curD) {
		HashMap<Character, Integer> newDice = (HashMap<Character, Integer>) dice.clone();
		/*
		 * 진행방향 = 바닥 
		 * 동<->서 남 <-> 북 은 서로 반대
		 */
		if (curD == 1) { // 동
			dice.put('E', newDice.get('U'));
			dice.put('U', newDice.get('W'));
			dice.put('W', newDice.get('D'));
			dice.put('D', newDice.get('E'));
		} else if (curD == 2) { // 서
			dice.put('E', newDice.get('D'));
			dice.put('U', newDice.get('E'));
			dice.put('W', newDice.get('U'));
			dice.put('D', newDice.get('W'));
		} else if (curD == 3) { // 남
			dice.put('S', newDice.get('U'));
			dice.put('U', newDice.get('N'));
			dice.put('N', newDice.get('D'));
			dice.put('D', newDice.get('S'));
		} else { // 북
			dice.put('S', newDice.get('D'));
			dice.put('U', newDice.get('S'));
			dice.put('N', newDice.get('U'));
			dice.put('D', newDice.get('N'));
		}
	}
	
}
