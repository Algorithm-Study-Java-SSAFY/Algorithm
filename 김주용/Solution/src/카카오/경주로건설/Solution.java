package 카카오.경주로건설;

import java.util.PriorityQueue;

class Path implements  Comparable<Path> {
    int y;
    int x;
    int d;
    int cost;

    public Path(int y, int x, int d, int cost) {
        this.y = y;
        this.x = x;
        this.d = d;
        this.cost = cost;
    }

    @Override
    public int compareTo(Path o) {
        return this.cost - o.cost;
    }
}
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}});
    }

    // DFS + 바운드 최솟값보다 커지면 진행이 안되게 -> 11 19
    // 다익스트라 14 24
    //

    static int N;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        int[][][] costs = new int[N][N][4]; // 방향 별로 필요함

        for(int i = 0; i < 2; i++) {
            bfs(board, costs, i);

            for(int j = 0; j < 4; j++) {
                answer = Math.min(answer, costs[N-1][N-1][i]);
            }
        }

        return answer;
    }

    public static void bfs(int[][] board, int[][][] costs, int d) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 4; k++) {
                    if(i == 0 && j == 0) {
                        costs[i][j][k] = 0;
                        continue;
                    }
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.add(new Path(0, 0, d, 0));

        while (!pq.isEmpty()) {
            Path cur = pq.poll();

            if(cur.y == N-1 && cur.x == N-1) {
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i], nx = cur.x + dx[i];
                int addCost = (cur.d == i) ? 100 : 600; // 코너를 돌고 직진 해야하기 때문에 600 원

                if(inRange(ny, nx) && board[ny][nx] == 0 && cur.cost + addCost <= costs[ny][nx][i]) {
                    pq.add(new Path(ny, nx, i, cur.cost + addCost));
                    costs[ny][nx][i] = cur.cost + addCost;
                }
            }
        }
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < N && -1 < x && x < N;
    }
}
