package _SWExpert;

import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        Solution s = new Solution();

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.println("#" + test_case);
            int n = sc.nextInt();
            s.solution(n);
        }
    }

    public void solution(int n) {
        int [][] board = new int [n][n];
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int d = 0, cnt = 2;
        int y = 0, x= 0;
        board[0][0] = 1;
        while (cnt <= n*n){
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (-1 < ny && ny < n && -1 < nx && nx < n) {
                if (board[ny][nx] == 0) {
                    board[ny][nx] = cnt++;
                    y = ny;
                    x = nx;
                } else {
                    d = (d + 1) % 4;
                }
            } else {
                d = (d + 1) % 4;
            }
        }
        print(board);
    }

    public void print(int[][] board) {
        for(int[] line : board) {
            for (int el: line) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
