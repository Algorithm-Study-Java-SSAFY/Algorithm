package BFS_DFS_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 적록색약 {
    public static String[][] picture;
    public static boolean[][] visited;
    public static int N;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        picture = new String[N][N];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++)
                picture[i][j] = "" + tmp.charAt(j);
        }

        // 일반인 조회
        int normal_area = 0;
        visited = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (visited[row][col] == false) {
                    dfs(row, col, picture[row][col], "normal");
                    normal_area++;
                }
            }
        }
        // 적록색약 조회
        int color_weak_area = 0;
        visited = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (visited[row][col] == false) {
                    dfs(row, col, picture[row][col], "color_weaknes");
                    color_weak_area++;
                }
            }
        }

        System.out.println(normal_area + " " + color_weak_area);
    }

    public static void dfs(int y, int x, String color, String type) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            if (cx < 0 || cx >= N || cy < 0 || cy >= N)
                continue;
            if (type == "normal") {
                // 정상적인 경우
                if (picture[cy][cx].equals(color) && visited[cy][cx] == false)
                    dfs(cy, cx, color, "normal");
            } else {
                // 적록색약인 경우 -> R과 G는 같다고 판단
                // B인 경우와 B가 아닌 경우로 나눔
                if (color.equals("B") && picture[cy][cx].equals(color) && visited[cy][cx] == false)
                    dfs(cy, cx, color, "color_weakness");
                else if (!color.equals("B") && !picture[cy][cx].equals("B") && visited[cy][cx] == false) {
                    dfs(cy, cx, color, "color_weakness");
                }

            }

        }
    }
}
