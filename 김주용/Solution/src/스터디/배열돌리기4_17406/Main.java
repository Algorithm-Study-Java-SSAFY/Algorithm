package 스터디.배열돌리기4_17406;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    static int N;
    static int M;
    static int K;

    static int[][] board;
    static int[][] workBoard;
    static int[][] commands;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        board = new int[N][M];
        workBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        commands = new int[K][3];
        for (int i = 0; i < K; i++) {
            commands[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        solution();
        System.out.println(answer);
    }

    public static void solution() {
        permutation(new int[K], new boolean[K], 0);
    }

    public static void permutation(int[] order, boolean[] visited, int r) {
        if (r == K) {
            init(board, workBoard);
            rotation(order);
//            System.out.println(Arrays.toString(order));
//            for(int[] a : workBoard) {
//                System.out.println(Arrays.toString(a));
//            }
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[r] = i;
                permutation(order, visited, r + 1);
                visited[i] = false;
            }
        }
    }

    public static void rotation(int[] order) {
        for (int idx : order) {
            int[] command = commands[idx];
            int startY = command[0] - command[2] - 1, startX = command[1] - command[2] - 1;
            int endY = command[0] + command[2] - 1, endX = command[1] + command[2] - 1;
            for(int i = 0; i < command[2]; i++) {
                rotate(startY, startX, endY, endX);
                startY++;
                startX++;
                endY--;
                endX--;
            }
        }
        getResult();
    }

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void rotate(int startY, int startX, int endY, int endX) {
        int direct = 0;
        int curY = startY, curX = startX;
        int[][] temp = new int[N][M];
        init(workBoard, temp);
        do {
            int ny = curY + dy[direct];
            int nx = curX + dx[direct];

            if (!inRange(startY, startX, endY, endX, ny, nx)) {
                direct = (direct + 1) % 4;
                continue;
            }

            workBoard[ny][nx] = temp[curY][curX];
            curY = ny;
            curX = nx;
        } while (curY != startY || curX != startX);
//
//        for(int [] a : workBoard) {
//            System.out.println(Arrays.toString(a));
//        }
//        System.out.println();
    }

    public static boolean inRange(int startY, int startX, int endY, int endX, int y, int x) {
        return startY <= y && y <= endY && startX <= x && x <= endX;
    }

    public static void init(int[][] originBoard, int[][] tempBoard) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                tempBoard[i][j] = originBoard[i][j];
            }
        }
    }

    public static void getResult() {
        for(int i = 0; i < N; i++) {
            int ret = 0;
            for(int j = 0; j < M; j++) {
                ret += workBoard[i][j];
            }
            answer = Math.min(answer, ret);
        }
    }

}
