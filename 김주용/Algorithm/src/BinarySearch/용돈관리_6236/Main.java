package BinarySearch.용돈관리_6236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N, M;
    static List<Integer> moneyList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int answer = Integer.MAX_VALUE;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);  // N일 동안 사용할 금액
        M = Integer.parseInt(line[1]);  // N일 동안 정확히 M번 인출

        int K = 0;  // 하루에 최대 K원 인출
        int sum = 0;
        for(int i = 0; i < N; i++) {
            moneyList.add(Integer.parseInt(in.readLine())); // i 번째 날에 필요한 금액
            sum += moneyList.get(i);
        }
        /*
        -> 정확히 K원을 M번 인출해서 매일 필요한 금액을 모두 충당
         */
        int left = 1, right = sum;
        while (left <= right) {
            int mid = (left + right) / 2; // 인출 금액
            if(getCount(mid)) { // 인출 금액이 더 큰 경우 -> 더 줄여서 최소 금액 찾기 ->  금액 줄이기
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {    // 인출 금액이 더 작은 경우 -> i번째 날에 필요한 금액 충족 x -> 더 큰 금액으로 늘리기
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean getCount(int money) { //K원 씩 몇 번 인출해야 하는지
        int cnt = 1, restMoney = money;
        for(int i = 0; i < N; i++) {
            if(moneyList.get(i) > money) {  // i 번째 날에 돈 부족
                return false;
            }
            // 쓰고 남은 돈으로 i 번째 날 돈이 부족하면
            if(moneyList.get(i) > restMoney ) {  //인출
                cnt++;
                restMoney = money;
            }
            restMoney -= moneyList.get(i); // 남은 돈
        }

        return cnt <= M;
    }
}
