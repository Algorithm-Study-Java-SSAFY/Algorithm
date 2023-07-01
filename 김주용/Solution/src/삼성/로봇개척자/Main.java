package 삼성.로봇개척자;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

class Seed {
    int K;
    int day;

    public Seed(int k, int day) {
        K = k;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "K=" + K +
                ", day=" + day +
                '}';
    }
}

class Robot {
    int y;
    int x;
    int d;

    public Robot(int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "y=" + y +
                ", x=" + x +
                ", d=" + d +
                '}';
    }
}

public class Main {

    static int N;
    static int M;
    static int[][] board;
    static Seed[][] seeds;

    static int ret = 0;
    static int answer;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int i = 1; i <= T; i++) {
            int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = line[0];
            M = line[1];
            board = new int[N][N];
            seeds = new Seed[N][N];
            answer = 0;
            for (int j = 0; j < N; j++) {
                board[j] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            solution();
        }
    }

    public static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    ret = 0;
                    init();
                    dfs(new Robot(i, j, k), 1);
                    answer = Math.max(answer, ret);
                }
            }
        }
        dfs(new Robot(3, 3, 2), 1);
        System.out.println(answer);
    }

    // board[y][x] == 3 : 곡식,  == 2: 싹, == 1 : 산, == 0 : 빈 칸
    public static void dfs(Robot cur, int day) {
        if (day > M) {
            return;
        }
        growSeed();

//        System.out.println(day - 1 + " " + cur);
////        for (Seed[] s : seeds) {
////            System.out.println(Arrays.toString(s));
////        }
//        for (int[] b : board) {
//            System.out.println(Arrays.toString(b));
//        }
//        System.out.println();
        //오전 활동
        Robot next = canMove(cur);
        if (board[cur.y][cur.x] == 0 && seeds[cur.y][cur.x] == null && next != null) {
            seeds[cur.y][cur.x] = new Seed(day, -1);
        } else if (board[cur.y][cur.x] == 3) {
            seeds[cur.y][cur.x] = null;
            board[cur.y][cur.x] = 0;
            ret++;
        }

        // 오후 활동
        if (next != null) {
            dfs(next, day + 1);
        } else {
            dfs(cur, day + 1);
        }
    }

    public static Robot canMove(Robot cur) {
        int[] nextDirections = getNextDirect(cur.d);
        for (int nd : nextDirections) {
            int ny = cur.y + dy[nd], nx = cur.x + dx[nd];
            if (inRange(ny, nx) && (board[ny][nx] == 0 || board[ny][nx] == 3)) {
                if(seeds[ny][nx] != null && board[ny][nx] != 3) {
                    continue;
                }
                return new Robot(ny, nx, nd);
            }
        }
        return null;
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < N;
    }

    public static void growSeed() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seeds[i][j] != null) {
                    seeds[i][j].day++;
                    if (seeds[i][j].day >= 2 + seeds[i][j].K) {
                        board[i][j] = 3;
                    }
                }
            }
        }
    }

    public static int[] getNextDirect(int d) {
        if (d == 0) {
            return new int[]{1, 0, 3, 2};
        }
        if (d == 1) {
            return new int[]{2, 1, 0, 3};
        }
        if (d == 2) {
            return new int[]{3, 2, 1, 0};
        }
        return new int[]{0, 3, 2, 1};
    }

    public static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(board[i][j] == 0 || board[i][j] == 1)) {
                    board[i][j] = 0;
                }
                seeds[i][j] = null;
            }
        }
    }
}
