package 현대.동계테스트시점예측;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Pair {
    int y, x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {

    static int N;
    static int M;
    static int[][] board;
    static boolean[][] melted;

    static int ice;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        board = new int[N][M];
        for(int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < M; j++) {
                if(board[i][j] == 1) {
                    ice++;
                }
            }
        }
        solution();
    }

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    public static void solution() {
        int answer = 0;
        while (ice > 0) {
            bfs(0, 0);
            Queue<Pair> queue = new LinkedList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(board[i][j] == 1 && isMelt(i, j)) {
                        queue.add(new Pair(i, j));
                    }
                }
            }

            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                board[cur.y][cur.x] = 0;
                melted[cur.y][cur.x] = true;
                ice--;
            }

//            for(int[] i : board) {
//                System.out.println(Arrays.toString(i));
//            }
//            System.out.println();
            answer++;
        }
        System.out.println(answer);
    }

    public static void bfs(int y, int x) {
        melted = new boolean[N][M];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(y, x));
        melted[y][x] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i], nx = cur.x + dx[i];
                if(inRange(ny, nx) && !melted[ny][nx] && board[ny][nx] == 0) {
                    queue.add(new Pair(ny, nx));
                    melted[ny][nx] = true;
                }
            }
        }
    }

    public static boolean isMelt(int y, int x) {
        int cnt = 0;
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if(inRange(ny, nx) && melted[ny][nx]) {
                cnt++;
            }
            if(cnt >= 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < M;
    }
}
