package 스터디.상범빌딩_6593;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

class Point implements Comparable<Point> {
    int z;
    int y;
    int x;

    int time;

    public Point(int z, int y, int x, int time) {
        this.z = z;
        this.y = y;
        this.x = x;
        this.time = time;
    }

    @Override
    public int compareTo(Point o) {
        return this.time - o.time;
    }
}

public class Main {
    static int L;
    static int R;
    static int C;

    static char[][][] board;
    static boolean[][][] visited;

    static Point start;
    static Point dest;

    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};

    static PriorityQueue<Point> queue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            L = line[0];
            R = line[1];
            C = line[2];
            if(L == 0 && R == 0 && C == 0) {
                break;
            }
            board = new char[L][R][C];
            visited = new boolean[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    char[] cur = in.readLine().toCharArray();
                    board[i][j] = cur;
                    for (int k = 0; k < C; k++) {
                        if (cur[k] == 'S') {
                            start = new Point(i, j, k, 0);
                        }
                        if (cur[k] == 'E') {
                            dest = new Point(i, j, k, 0);
                        }
                    }
                }
                in.readLine();
            }
            System.out.println(solution());
            queue.clear();
        }
    }

    public static String solution() {
        queue.add(start);
        visited[start.z][start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();


            for(int i = 0; i < 6; i++) {
                int nz = cur.z + dz[i];
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if(!inRange(nz, ny, nx)) {
                    continue;
                }
                if (board[nz][ny][nx] != '#' && !visited[nz][ny][nx]) {
                    queue.add(new Point(nz, ny ,nx, cur.time + 1));
                    visited[nz][ny][nx] = true;
                    if(board[nz][ny][nx] == 'E') {
                        return String.format("Escaped in %d minute(s).", cur.time + 1);
                    }
                }
            }
        }
        return "Trapped!";
    }

    public static boolean inRange(int z, int y, int x) {
        return -1 < z && z < L && -1 < y && y < R && -1 < x && x < C;
    }
}
