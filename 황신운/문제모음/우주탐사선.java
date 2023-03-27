package testBaek;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

public class 우주탐사선 {

    public static int N, K, minTime = 100000, cntTime = 0;
    public static int[][] map;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);



        map = new int[N][N];
        for(int i=0; i<N; i++) {
            tmp = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        
        solve();

        visited = new boolean[N];
        visited[K] = true;
        getMinTime(K,1);    // 시작하는 행성은 이미 방문 -> visited 처리 & 현재 방문 level 1
        System.out.println(minTime);

    }
    
    public static void solve() {

        // 플로이드워셜로 최단 거리를 map에 저장
        for(int k=0; k<N; k++) {
            // 행성 k를 거쳐가는 경우와 그냥 바로 가는 경우 비교
            for(int a=0; a<N; a++) {
                for(int b=0; b<N; b++) {
                    map[a][b] = Math.min(map[a][b], map[a][k] + map[k][b]);
                }
            }
        }
    }
    
    // 방문할 수 있는 행성 조회
    public static void getMinTime(int start, int level) {
        if(level == N) {
            minTime = Math.min(minTime, cntTime);   // 방문 시간과 최단 시간 비교
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            cntTime += map[start][i];   // 현재 위치 방문 시간 더해줌
            getMinTime(i, level + 1);   // 다음 행성에서 출발한 행성 값, 방문한 행성 수 보내줌
            cntTime -= map[start][i];
            visited[i] = false;
        }
    }
}