package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 연결요소의개수 {
    public static boolean[] visited;
    public static HashMap<Integer, ArrayList<Integer>> paths;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);

        visited = new boolean[N + 1];
        paths = new HashMap<>();

        for (int i = 0; i < N + 1; i++) {
            paths.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            paths.get(from).add(to);
            paths.get(to).add(from);
        }

        int answer = 0;
        for (int index = 1; index <= N; index++) {
            if (visited[index] == false) {
                visited[index] = true;
                bfs(index);
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void bfs(int index) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(index);

        while (!que.isEmpty()) {
            int num = que.poll();
            for (int j = 0; j < paths.get(num).size(); j++) {
                if (visited[paths.get(num).get(j)] == false) {
                    que.offer(paths.get(num).get(j));
                    visited[paths.get(num).get(j)] = true;
                }
            }

        }
    }
}
