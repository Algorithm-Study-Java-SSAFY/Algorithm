package BinarySearch.용돈관리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N, M;
    static List<Integer> moneyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        int K = 0;
        for(int i = 0; i < N; i++) {
            moneyList.add(Integer.parseInt(in.readLine()));
        }

        while (true) {
            //돈을 뽑고

            // 계산
            for(int i = 0; i < N; i++) {

            }
        }



    }
}
