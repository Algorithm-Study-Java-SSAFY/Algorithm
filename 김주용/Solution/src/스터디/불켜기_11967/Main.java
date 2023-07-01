package 스터디.불켜기_11967;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Pair {
    int y;
    int x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}

public class Main {
    static int N;
    static int M;
    static boolean[][] visited;
    static int[][] board;
    static ArrayList<Pair>[][] onInform;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        board = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        onInform = new ArrayList[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                onInform[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            int[] input = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Pair value = new Pair(input[2], input[3]);
            onInform[input[0]][input[1]].add(value);
        }
        solution();
    }

    public static void solution() {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(1, 1));
        visited[1][1] = true;
        board[1][1] = 1;

        int answer = 0;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            // 불 키고 인접한 곳에 내가 간 적이 있다면 Queue 추가해주기
            ArrayList<Pair> curInform = onInform[cur.y][cur.x];
            for (Pair on : curInform) {
                if(board[on.y][on.x] == 1) {
                    continue;
                }
                if(board[on.y][on.x] == 2) {
                    queue.add(on);
                    visited[on.y][on.x] = true;
                }
                board[on.y][on.x] = 1;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i], nx = cur.x + dx[i];
                if (0 < ny && ny <= N && 0 < nx && nx <= N) {
                    if (board[ny][nx] == 1 && !visited[ny][nx]) {
                        queue.add(new Pair(ny, nx));
                        visited[ny][nx] = true;
                    } else if(board[ny][nx] == 0 && !visited[ny][nx]) { // 갈 수 있지만 불이 안 켜져서 못 가는 경우
                        board[ny][nx] = 2;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

}