package 스터디.공유기설치_2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int C;
    static int[] points;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);

        points = new int[N];
        for(int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(in.readLine());
        }
        solution();
    }

    // 최소 길이를 이분 탐색
    public static void solution() {
        Arrays.sort(points);
        int left = 1;
        int right = points[N-1] - points[0];
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(install(mid) >= C) { // 거리 늘리기
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else { // 거리 좁히기
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    public static int install(int dist) {
        int cnt = 1;
        int lastPoint = points[0];
        for(int i = 1; i < N; i++) {
            if(points[i] - lastPoint >= dist) { // 최소 길이보다 커지는 포인트에 공유기 설치
                cnt++;
                lastPoint = points[i];
            }
        }
        return cnt;
    }
}
