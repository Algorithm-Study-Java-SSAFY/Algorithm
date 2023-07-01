package 삼성.나무박멸;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Tree implements Comparable<Tree> {
	int y;
	int x;
	int height;

	public Tree(int y, int x, int height) {
		this.y = y;
		this.x = x;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Tree [y=" + y + ", x=" + x + ", height=" + height + "]";
	}

	@Override
	public int compareTo(Tree o) {
		if(this.height > o.height) {
			return -1;
		} else if (this.height == o.height) {
			if(this.y < this.y) {
				return -1;
			} else if (this.y == o.y) {
				if(this.x < this.x) {
					return -1;
				}
			}
		}
		return 1;
	}
	
	

}

public class Main {

	static int N;
	static int M;
	static int K;
	static int C;

	static int[][] board;
	static boolean[][] wall;
	static ArrayList<Tree> trees;

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int[] crossDy = { 1, 1, -1, -1 };
	static int[] crossDx = { 1, -1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = line[0];
		M = line[1];
		K = line[2];
		C = line[3] + 1;
		board = new int[N][N];
		trees = new ArrayList<>();
		wall = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			board[i] = line;
			for (int j = 0; j < N; j++) {
				if (board[i][j] > 0) {
					trees.add(new Tree(i, j, board[i][j]));
				}
				if(board[i][j] == -1) {
					wall[i][j] = true;
				}
			}
		}
		solution();
	}

	public static void solution() {
		int answer = 0;
		while (M-- > 0) {
			growTree();
			increaseTree();
			mapTree();
			Tree killTree = getMaxPoint();
			answer += setKiller(killTree.y, killTree.x);
			mapTree();
			decreaseKiller();
		}
		System.out.println(answer);
	}

	public static void growTree() {
		for (Tree cur : trees) {
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && board[ny][nx] > 0) {
					cur.height++;
				}
			}
			board[cur.y][cur.x] = cur.height;
		}
	}

	public static void increaseTree() {
		ArrayList<Tree> newTree = new ArrayList<>();
		for (Tree cur : trees) {
			int can = 0;
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && board[ny][nx] == 0) {
					can++;
				}
			}
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i], nx = cur.x + dx[i];
				if (-1 < ny && ny < N && -1 < nx && nx < N && board[ny][nx] == 0) {
					newTree.add(new Tree(ny, nx, cur.height / can));
				}
			}
		}

		for (Tree cur : newTree) {
			board[cur.y][cur.x] += cur.height;
		}
	}

	public static void mapTree() {
		trees.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] > 0) {
					trees.add(new Tree(i, j, board[i][j]));
				}
			}
		}
	}
	

	public static Tree getMaxPoint() {
		PriorityQueue<Tree> queue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] <= 0) {
					queue.add(new Tree(i, j, 0));
					continue;
				}

				int kill = getKiller(i, j);
				queue.add(new Tree(i, j, kill));
			}
		}
		return queue.poll();
	}

	public static int getKiller(int sy, int sx) {
		int kill = board[sy][sx];
		HashSet<Integer> cannotGo = new HashSet<>();
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i < 4; i++) {
				int ny = sy + crossDy[i] * k, nx = sx + crossDx[i] * k;
				if (!(-1 < ny && ny < N && -1 < nx && nx < N) || cannotGo.contains(i)) {
					continue;
				}
				if (board[ny][nx] > 0) {
					kill += board[ny][nx];
				} else {
					cannotGo.add(i);
				}

			}
		}
		return kill;
	}
	
	public static int setKiller(int sy, int sx) {
		HashSet<Integer> cannotGo = new HashSet<>();
		int next  = (board[sy][sx] > 0) ? 0 : board[sy][sx];
		int ret = board[sy][sx];
		board[sy][sx] = next -C;
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i < 4; i++) {
				int ny = sy + crossDy[i] * k, nx = sx + crossDx[i] * k;
				if (!(-1 < ny && ny < N && -1 < nx && nx < N) || cannotGo.contains(i)) {;
					continue;
				}
				if(wall[ny][nx]) {
					cannotGo.add(i);
					continue;
				}
				if (board[ny][nx] > 0) {
					ret += board[ny][nx];
					board[ny][nx] = -C;
				} else {
					board[ny][nx] = -C;
					cannotGo.add(i);
				}
			}
		}
		if(ret < 0) {
			return 0;
			
		}
		return ret;
	}
	
	public static void decreaseKiller() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!wall[i][j] && board[i][j] < 0) {
					board[i][j]++;
				}
			}
		}
	}

	public static void debug() {
		System.out.println(" ");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
