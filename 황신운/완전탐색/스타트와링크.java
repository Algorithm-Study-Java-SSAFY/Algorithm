import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[][] power;
    static boolean[] visited;
    static int[] team;
    static int N;
    static int min_diff = 1001;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        power = new int[N][N];
        visited = new boolean[N];

        team = new int[N];
        for (int i = 0; i < N; i++) {
            team[i] = i + 1;
        }

        for (int i = 0; i < N; i++) {
            String[] tmpStr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(tmpStr[j]);
            }
        }

        // 팀 구성 조합
        combination(team, visited, 0, N, N / 2);

        // 팀 내에
        System.out.println(min_diff);
    }

    static void combination(int[] team, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            calcPower();
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(team, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static void calcPower() {
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();
        int startPower = 0;
        int linkPower = 0;

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                start.add(team[i]);
            } else {
                link.add(team[i]);
            }
        }

        for (int i = 0; i < N / 2 - 1; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                startPower += power[start.get(i) - 1][start.get(j) - 1] + power[start.get(j) - 1][start.get(i) - 1];
                linkPower += power[link.get(i) - 1][link.get(j) - 1] + power[link.get(j) - 1][link.get(i) - 1];
            }
        }

        min_diff = Math.min(min_diff, Math.abs(startPower - linkPower));
    }
}