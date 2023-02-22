package 스터디.스타트와링크_14889;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] board;

    static List<boolean[]> teamList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        board = new int[N][N];

        for(int i = 0; i < N; i++) {
            String[] line = in.readLine().split(" ");
            board[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution());
        in.close();
    }

    public static int solution() {
        int answer = Integer.MAX_VALUE;

        makeTeam(new boolean[N], 0, N, N/2);

        for (boolean[] curTeam : teamList) {
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();
            for(int i = 0; i < curTeam.length; i++) {
                if (curTeam[i]) {
                    teamA.add(i);
                } else {
                    teamB.add(i);
                }
            }
            int ret = getScore(teamA, teamB);
            answer = Math.min(answer, ret);
        }
        return answer;
    }
    // 조합: 두 개의 팀으로 나누는 경우
    public static void makeTeam(boolean[] visited, int cur, int n, int r) {
        if (r == 0) {
            teamList.add(visited.clone());
            return;
        }

        for (int i = cur; i < n; i++) {
            visited[i] = true;
            makeTeam(visited, i+1, n, r-1);
            visited[i] = false;
        }
    }
    // 점수 계산
    public static int getScore(List<Integer> teamA, List<Integer> teamB){
        int scoreA = 0;
        int scoreB = 0;
        for(int i = 0; i < N/2 - 1; i++) {
            for(int j = i; j < N/2; j++) {
                scoreA += board[teamA.get(i)][teamA.get(j)];
                scoreA += board[teamA.get(j)][teamA.get(i)];
                scoreB += board[teamB.get(i)][teamB.get(j)];
                scoreB += board[teamB.get(j)][teamB.get(i)];
            }
        }
        return Math.abs(scoreA - scoreB);
    }
}
