package 스터디.상어초등학교_21608;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Student {
	int num;
	int[] like;

	public Student(int num, int[] like) {
		this.num = num;
		this.like = like;
	}

}

class Point implements Comparable<Point> {
	int y;
	int x;
	int adj;
	int empty;

	public Point(int y, int x, int adj, int empty) {
		super();
		this.y = y;
		this.x = x;
		this.adj = adj;
		this.empty = empty;
	}

	@Override
	public int compareTo(Point o) {
		if (this.adj > o.adj) {
			return -1;
		} else if (this.adj == o.adj) {
			if (this.empty > o.empty) {
				return -1;
			} else if (this.empty == o.empty) {
				if (this.y < o.y) {
					return -1;
				} else if (this.y == o.y) {
					if (this.x < o.x) {
						return -1;
					}
				}
			}
		}
		return 1;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", adj=" + adj + ", empty=" + empty + "]";
	}

}

public class Main {
	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int N;
	static int[][] board;
	static int[][][] adjBoard;
	static ArrayList<Student> table = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		for (int i = 0; i < N * N; i++) {
			String[] line = in.readLine().split(" ");
			int[] like = new int[4];
			for (int j = 1; j < 5; j++) {
				like[j - 1] = Integer.parseInt(line[j]);
			}
			table.add(new Student(Integer.parseInt(line[0]), like));
		}
		solution();
	}

	public static void solution() {
		for (Student student : table) {
			Point cur = getPoint(student);
			board[cur.y][cur.x] = student.num;
		}
		getScore();
		
	}

	public static Point getPoint(Student student) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (board[y][x] != 0) {
					continue;
				}
				int adj = 0;
				int empty = 0;
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d], nx = x + dx[d];
					if (-1 < ny && ny < N && -1 < nx && nx < N) {
						if (board[ny][nx] == 0) {
							empty++;
						}
						if (indexOf(student.like, board[ny][nx]) > -1) {
							adj++;
						}
					}
				}
				queue.add(new Point(y, x, adj, empty));
			}
		}

		return queue.poll();
	}
	
	public static void getScore() {
		int ret = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				int adj = 0;
				int idx = indexOfStudents(board[y][x]);
				Student student = table.get(idx);
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d], nx = x + dx[d];
					if (-1 < ny && ny < N && -1 < nx && nx < N) {
						if (indexOf(student.like, board[ny][nx]) > -1) {
							adj++;
						}
					}
				}
				ret += (int)Math.pow(10, adj - 1);
			}
		}
		System.out.println(ret);
	}
	
	public static int indexOfStudents(int num) {
		for (int i = 0; i < N*N; i++) {
			if (table.get(i).num == num) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(int[] like, int num) {
		for (int i = 0; i < 4; i++) {
			if (like[i] == num) {
				return i;
			}
		}
		return -1;
	}
}
