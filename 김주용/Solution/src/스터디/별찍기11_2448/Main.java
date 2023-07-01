package 스터디.별찍기11_2448;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int K;

    static String[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        K = (int) (Math.log(N / 3) / Math.log(2));
        answer = new String[N];

        answer[0] = "  *  ";
        answer[1] = " * * ";
        answer[2] = "*****";

        for(int i = 1; i <= K; i++) {
            solution(i);
        }


        for(int i = 0; i < N; i++) {
            System.out.println(answer[i]);
        }
    }

    public static void solution(int k) {
        int bot = 3 * (int) Math.pow(2, k);
        int mid = bot / 2;

        for(int i = mid; i < bot; i++) {
            answer[i] = answer[i - mid] + " " + answer[i - mid];
        }

        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < mid; i++) {
            temp.append(" ");
        }
        for(int i = 0; i < mid; i++) {
            answer[i] = temp + answer[i] + temp;
        }
    }
}
