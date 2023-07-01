package 스터디.수영장만들기_1113;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int y;
    int x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static int N;
    static int M;
    static int[][] board;
    static int[][] originBoard;
    static boolean[][] visited;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static Queue<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        board = new int[N][M];
        originBoard = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] cur = in.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                board[i][j] = (int) (cur[j] - '0');
                originBoard[i][j] = (int) (cur[j] - '0');
            }
        }
        solution();
    }

    public static void solution() {
        int answer = 0;
        for (int y = 1; y < N-1; y++) {
            for (int x = 1; x < M-1; x++) {
                for (int i = board[y][x]; i <= 9; i++) {
                    init();
                    if(!bfs(new Pair(y, x), i)) {
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += board[i][j] - originBoard[i][j];
            }
        }
        System.out.println(answer);
    }


    // 현재 높이에서 바깥으로 빠지는지
    public static boolean bfs(Pair start, int height) {
        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i], nx = cur.x + dx[i];
                if (inRange(ny, nx) && board[ny][nx] < height) {
                    if (inBorder(ny, nx)) {
                        return false;
                    }
                    queue.add(new Pair(ny, nx));
                    visited[ny][nx] = true;
                }

            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j]) {
                    board[i][j] = height;
                }
            }
        }
        return true;
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < M && !visited[y][x];
    }

    public static boolean inBorder(int y, int x) {
        return y == 0 || x == 0 || y == N - 1 || x == M - 1;
    }

    public static void init() {
        queue.clear();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }
    }
}