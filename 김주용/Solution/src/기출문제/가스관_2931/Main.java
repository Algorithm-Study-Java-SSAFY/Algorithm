package 기출문제.가스관_2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int R;
	static int C;
	
	static char[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System. in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		board = new char[R][C];
		for(int i = 0; i < R; i++) {
			board[i] = in.readLine().toCharArray();
		}
	}

}
