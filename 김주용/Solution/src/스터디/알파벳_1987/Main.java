package 스터디.알파벳_1987;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int R;
    static int C;
    static int[][] board;
    static boolean[][] visited;
    static boolean[] alpha;
    static int answer = 0;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        board = new int[R][C];
        visited = new boolean[R][C];
        alpha = new boolean[26];
        for (int i = 0; i < R; i++) {
            char[] cur = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = cur[j] - 'A';
            }
        }
        visited[0][0] = true;
        alpha[board[0][0]] = true;
        solution(0, 0, 1);
        System.out.println(answer);
    }

    // DFS : 이전 방문한 알파벳과 방문 기록 저장 후 진행
    public static void solution(int y, int x, int cnt) {

        answer = Math.max(answer, cnt);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (!(-1 < ny && ny < R && -1 < nx && nx < C)) {
                continue;
            }
            if (!alpha[board[ny][nx]] && !visited[ny][nx]) {
                alpha[board[ny][nx]] = true;
                visited[ny][nx] = true;
                solution(ny, nx, cnt + 1);
                alpha[board[ny][nx]] = false;
                visited[ny][nx] = false;
            }
        }
    }
}
