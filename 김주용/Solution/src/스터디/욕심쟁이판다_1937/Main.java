package 스터디.욕심쟁이판다_1937;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Pair implements Comparable<Pair> {
    int y;
    int x;
    int value;

    public Pair(int y, int x, int value) {
        this.y = y;
        this.x = x;
        this.value = value;
    }

    @Override
    public int compareTo(Pair o) {
        return o.value - this.value;
    }
}
public class Main {

    static int N;
    static PriorityQueue<Pair> starts = new PriorityQueue<>();
    static int[][] board;
    static int[][] dp;
    static int maxCnt;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        dp = new int[N][N];
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < N; j++) {
                starts.add(new Pair(i, j, board[i][j]));
            }
        }
        solution();
    }

    public static void solution() {
        int answer = 0;
        while (!starts.isEmpty()) {
            Pair cur = starts.poll();
            maxCnt = 1;
            dfs(cur.y, cur.x, 1);
            dp[cur.y][cur.x] = maxCnt;
            answer = Math.max(answer, maxCnt);
        }

        System.out.println(answer);
    }

    //dp[y][x] : y, x 에서 가장 많이 갈 수 있는 칸 수
    public static void dfs(int y, int x, int cnt) {
        if(dp[y][x] > 0) {
            maxCnt = Math.max(cnt + dp[y][x] - 1, maxCnt);
            return;
        }

        maxCnt = Math.max(maxCnt, cnt);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (!(-1 < ny && ny < N && -1 < nx && nx < N)) {
                continue;
            }
            if (board[y][x] < board[ny][nx]) {
                dfs(ny, nx, cnt + 1);
            }
        }
    }
}
