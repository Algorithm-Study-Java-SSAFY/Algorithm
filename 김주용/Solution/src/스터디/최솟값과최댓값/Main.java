package 스터디.최솟값과최댓값;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int M;
    static long[] nums;
    static long[] minTree;
    static long[] maxTree;

    /*
    세그먼트 트리 : 특정 구간 내 데이터 연산을 빠르게 구할 수 있는 트리 ex) 구간 합, 최솟값, 최댓값 등
     */
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        nums = new long[N + 1];
        for (int i = 1; i <= N; i++) {   // 1부터 시작한 이유: 2를 곱했을 때 왼쪽 자식 노드를 가르킴
            nums[i] = Long.parseLong(in.readLine());
        }
        maxTree = new long[N * 4]; // 갯수 * 4 의 길이면 모든 범위를 커버
        minTree = new long[N * 4];

        maxInit(1, N, 1);
        minInit(1, N, 1);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            long a = Long.parseLong(line[0]);
            long b = Long.parseLong(line[1]);

            long min = minFind(1, N, 1, a, b);
            long max = maxFind(1, N, 1, a, b);
            answer.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(answer);
    }

    /*
     원래 데이터 범위를 반씩 분할하며 그 구간의 합 저장.
     완전 이진 트리므로 왼쪽 자식은 2*idx 오른쪽 자식은 2*idx + 1
     startIdx: 구간 시작, endIdx: 구간 끝, node: 부모 노드
     */
    public static long maxInit(int startIdx, int endIdx, int node) {
        if (startIdx == endIdx) {
            return maxTree[node] = nums[startIdx];
        }
        int mid = (startIdx + endIdx) / 2;
        long left = maxInit(startIdx, mid, node * 2);
        long right = maxInit(mid + 1, endIdx, node * 2 + 1);
        return maxTree[node] = Math.max(left, right);
    }

    public static long minInit(int startIdx, int endIdx, int node) {
        if (startIdx == endIdx) {
            return minTree[node] = nums[startIdx];
        }
        int mid = (startIdx + endIdx) / 2;
        long left = minInit(startIdx, mid, node * 2);
        long right = minInit(mid + 1, endIdx, node * 2 + 1);
        return minTree[node] = Math.min(left, right);
    }

    private static long maxFind(int start, int end, int node, long left, long right) {
        if(start > right || end < left) {
            return 0;
        }

        if(left <= start && end <= right) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;

        return Math.max(maxFind(start, mid, node * 2, left, right), maxFind(mid + 1, end, node * 2 + 1, left, right));
    }

    private static long minFind(int start, int end, int node, long left, long right) {
        if(start > right || end < left) {
            return Integer.MAX_VALUE;
        }

        if(left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;

        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }
}
