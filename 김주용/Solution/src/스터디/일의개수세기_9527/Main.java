package 스터디.일의개수세기_9527;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        long A = Long.parseLong(line[0]);
        long B = Long.parseLong(line[1]);
    }

    // 조합 -> 불가
    // 패턴
    static long[] counts = new long[96]; // 10^16 ~= 2^48
    public static void solution(long A, long B) {
        counts[0] = 0;
        counts[1] = 1;
        for(int i = 2; i <= 96; i++) {
            counts[i] = 2 * counts[i-1] + (long) (Math.pow(2, i-1));
        }

    }

    public static void getCount() {

    }
}
