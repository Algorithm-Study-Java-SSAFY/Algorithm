package 스터디.무기공학_18430;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;
    static int answer = 0;

    static int[][] dy = {{0, 1}, {0, -1}, {0, -1}, {0, 1}};
    static int[][] dx = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        solution(0, 0);
        System.out.println(answer);
    }

    public static void solution(int point, int score) {
        answer = Math.max(answer, score);
        if(point >= N * M) {
            return;
        }
        int y = point / M;
        int x = point % M;

        if(!visited[y][x]) {
            for (int i = 0; i < 4; i++) {
                int ny1 = y + dy[i][0], nx1 = x + dx[i][0];
                int ny2 = y + dy[i][1], nx2 = x + dx[i][1];
                if(inRange(ny1, nx1) && inRange(ny2, nx2)) {
                    visited[y][x] = true;
                    visited[ny1][nx1] = true;
                    visited[ny2][nx2] = true;
                    int sum = board[y][x] * 2 + board[ny1][nx1] + board[ny2][nx2];

                    solution(point + 1, score + sum);
                    visited[y][x] = false;
                    visited[ny1][nx1] = false;
                    visited[ny2][nx2] = false;
                }
            }
        }
        solution(point + 1, score);
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < M && !visited[y][x];
    }
}
