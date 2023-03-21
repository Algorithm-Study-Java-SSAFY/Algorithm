package 스터디.함께블록쌓기_18427;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int M;
	static int H;

	static int[][] blocks;
	static boolean[][] visited;

	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		H = line[2];

		blocks = new int[N][];
		visited = new boolean[N][];
		for (int i = 0; i < N; i++) {
			int[] init = { 0 };
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] block = new int[line.length + 1];
			System.arraycopy(init, 0, block, 0, 1);
			System.arraycopy(line, 0, block, 1, line.length);

			blocks[i] = block;
			visited[i] = new boolean[blocks[i].length + 1];
		}

		solution();
	}

	private static void solution() {
		long[][] heights = new long[N][H + 1];
		for(int block : blocks[0]) {
			heights[0][block] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			for(int h = 1; h <= H; h++) {	
				heights[i][h] += heights[i-1][h];	// 이전 까지 학생들이 만든 경우 
			}
			for(int cur : blocks[i]) {	// 현재 학생이 가진 블럭으로 만드는 경우
				heights[i][cur] += 1;
				if(cur == 0) {
					continue;
				}
				System.out.println("this: " + cur);
				for(int j = 1; j < H+1; j++) { // 현재 블록과 조합해서 만드는 경우 
						
					if(heights[i-1][j] > 0 && j + cur <= H) {
						 System.out.print(j + cur + " ");
						heights[i][cur + j] += 1;
					}
				}
				System.out.println("");
			}
		}
		for(long[] h : heights) {
			System.out.println(Arrays.toString(h));
		}
	}

}
