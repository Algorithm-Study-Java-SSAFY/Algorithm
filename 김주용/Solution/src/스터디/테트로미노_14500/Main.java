package 스터디.테트로미노_14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);


        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        solution();
    }

    public static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, board[i][j]);
                visited[i][j] = false;
                exception(i, j);
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int y, int x, int depth, int sum) {
        if (depth == 3) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (inRange(ny, nx) && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny, nx, depth + 1, sum + board[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }

    static int[][][] exceptD = {{{-1,0},{1,0},{0,1}}, /* ├  */
            {{0,-1},{0,1},{1,0}},
            {{0,-1},{-1,0},{1,0}},
            {{0,-1},{-1,0},{0,1}}};
    private static void exception(int y, int x) {
        for(int i = 0; i < 4; i++) {
            int sum = board[y][x];
            boolean flag = false;
            for(int j = 0; j < 3; j++) {
                int ny = y + exceptD[i][j][0], nx = x + exceptD[i][j][1];
                if(!inRange(ny, nx)) {
                    flag = true;
                    continue;
                }
                sum += board[ny][nx];
            }
            if(!flag) {
                answer = Math.max(answer, sum);
            }
        }

    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < M;
    }
}
