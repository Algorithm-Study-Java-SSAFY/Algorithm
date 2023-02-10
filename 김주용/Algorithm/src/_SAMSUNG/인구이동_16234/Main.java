package _SAMSUNG.인구이동_16234;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    Queue<int []> starts;
    static int[][] board;
    static boolean[][] visit;
    static int N, R, L;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        // input
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        L = Integer.parseInt(line[1]);
        R = Integer.parseInt(line[2]);
        board = new int[N][N];
        visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            line = in.readLine().split(" ");
            board[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
        }
        solution();
    }

    public static void solution() {
        int c = 0;
        bfs(0, 0);
//        while (c < 2) {
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (!visit[i][j]) {
//                        bfs(i, j);
//                    }
//                }
//            }
//            c++;
//        }


    }
    public static void bfs(int y, int x) {
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {y, x});
        Queue<int []> group = new LinkedList<>();
        int people = 0;

        while (!queue.isEmpty()) {
            int [] cur = queue.poll();
            int cy = cur[0], cx = cur[1];
            people += board[cy][cx];
            group.add(new int[] {cy, cx});
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i], nx = cx + dx[i];
                if (ny < 0 || ny > N-1 || nx < 0 || nx > N-1 || visit[ny][nx]) {
                    continue;
                }
                int diff = Math.abs(board[y][x] - board[ny][nx]);
                if (diff >= L && diff <= R) {
                    System.out.println("2: " + ny + " " + nx);
                    queue.add(new int[] {ny, nx});
                    visit[ny][nx] = true;
                }
            }
        }

        System.out.println(group.size());
        int newPeople = people / group.size();
        while(!group.isEmpty()) {
            int [] cur = group.poll();
            System.out.println(Arrays.toString(cur));
            int cy = cur[0], cx = cur[1];
            board[cy][cx] = newPeople;
        }
        for(int[] b : board) {
            System.out.println(Arrays.toString(b));
        }
    }

    public static boolean isSatisfy(int y, int x, int ny, int nx) {
        if (ny < 0 || ny > N-1 || nx < 0 || nx >= N-1 || visit[ny][nx]) {
            return false;
        }
        int diff = Math.abs(board[y][x] - board[ny][nx]);
        return diff >= L && diff <= R;
    }

}
