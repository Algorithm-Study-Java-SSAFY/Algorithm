package 현대.비밀메뉴;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    static int M;
    static int N;
    static int K;

    static String secret;
    static String user;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = line[0];
        N = line[1];
        K = line[2];

        secret = String.join("" ,in.readLine().split(" "));
        user = String.join("" ,in.readLine().split(" "));
        System.out.println(solution());
    }

    public static String solution() {
        if(N < M) {
            return "normal";
        }
        if(user.contains(secret)) {
            return "secret";
        }
        return "normal";
    }
}
