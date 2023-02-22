package 스터디.수찾기_1920;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());
        int[] A = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(in.readLine());
        int[] X = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(A);
        for(int x : X) {
            if(Arrays.binarySearch(A, x) >= 0) {
                out.write("1\n");
                continue;
            }
            out.write("0\n");
        }

        in.close();
        out.close();
    }
}
