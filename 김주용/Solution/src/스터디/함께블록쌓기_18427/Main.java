package 스터디.함께블록쌓기_18427;

import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.plaf.SliderUI;

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
		for(int i = 0; i < N; i++) {
			int[] init = {0};
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
		dfs(new int[N], 0);
		System.out.println(answer);
	}
	
	private static void dfs(int[] selectedBlock, int curStudent) {
		if(curStudent == N) {
			if(getHeight(selectedBlock) == H) {
				answer++;
			}
			return;
		}
		
		for(int i = 0; i < blocks[curStudent].length; i++) {
			if(!visited[curStudent][i]) {
				visited[curStudent][i] = true;
				selectedBlock[curStudent] = i;
				dfs(selectedBlock, curStudent + 1);
				visited[curStudent][i] = false;
			}
		}
	}
	
	public static int getHeight(int[] selectedBlock) {
		int height = 0;
		for(int i = 0; i < N; i++) {
			int blockIdx = selectedBlock[i];
			height += blocks[i][blockIdx];
		}
		return height;
	}
}
