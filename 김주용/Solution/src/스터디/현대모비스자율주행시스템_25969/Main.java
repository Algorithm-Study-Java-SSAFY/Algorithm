package 스터디.현대모비스자율주행시스템_25969;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class Path implements Comparable<Path> {
    int y, x;
    int cnt;
    int cost;
    boolean isVisited;

    public Path(int y, int x, int cnt, int cost, boolean isVisited) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
        this.cost = cost;
        this.isVisited = isVisited;
    }

    @Override
    public int compareTo(@NotNull Path o) {
        return this.cost - o.cost;
    }
}

public class Main {

    static int N, M, K;
    static int[][] board;
    static boolean[][] visited;
    static int[][] pattern;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            board[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        pattern = new int[5][5];
        for(int i = 0; i < 5; i++) {
            pattern[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solution(0, 0);
    }

    public static void solution(int y, int x) {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.add(new Path(0, 0, 0, 0, false));

        while(!pq.isEmpty()) {
            Path cur = pq.poll();

            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i], nx = cur.x + dx[i];
                if(!inRange(ny, nx)) {
                    continue;
                }
                if(board[ny][nx] != 1 && !visited[ny][nx]) {
                    if(board[ny][nx] == 2) {
                        pq.add(new Path(ny, nx, 0, cur.cost + 1, true));
                    }

                    visited[ny][nx] = cur.isVisited;
                }
            }

//            for(int i = cur.y - 2; i <= cur.y + 2; i++) {
//                for(int j = cur.x - 2; j < cur.x + 2; j++) {
//                    if(!inRange(i, j)) {
//                        continue;
//                    }
//
//                }
//            }
        }
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < M;
    }
}
