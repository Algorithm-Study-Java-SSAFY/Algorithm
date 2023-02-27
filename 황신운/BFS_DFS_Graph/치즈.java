package BFS_DFS_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Baek2636_치즈 {
    public static int N;
    public static int M;
    public static boolean[][] visited;
    public static int[][] map;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        List<Integer> cheeseList = new ArrayList<>();
        int turn = 0;
        while (true) {
            visited = new boolean[N][M];
            int cheeseCnt = 0;

            // cheese 칸 개수. 이전 turn에서 사라질 부분은 0으로 변경
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1)
                        cheeseCnt++;
                    else if (map[i][j] == 2)
                        map[i][j] = 0;
                }
            }

            // 0이면 끝남
            if (cheeseCnt == 0)
                break;

            // 0이 아닐 경우 치즈 개수 값 저장
            cheeseList.add(cheeseCnt);

            dfs(0, 0); // X 부분은 치즈가 없으니 무조건 0 -> 0,0부터 탐색 시작
            turn++; // 시간 + 1
        }

        System.out.println(turn);
        System.out.println(cheeseList.get(turn - 1));
    }

    public static void dfs(int x, int y) {

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cx < M && cy >= 0 && cy < N) {
                // 조회한 곳이 공기라면
                if (map[cy][cx] == 0 && visited[cy][cx] == false)
                    dfs(cx, cy);
                // 조회한 곳이 공기에 접촉된 치즈라면 사라질 부분이니까 값을 2로 변경
                else if (map[cy][cx] == 1)
                    map[cy][cx] = 2;
            }
        }
    }
}
