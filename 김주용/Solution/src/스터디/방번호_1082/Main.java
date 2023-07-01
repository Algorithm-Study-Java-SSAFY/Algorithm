package 스터디.방번호_1082;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    static int N;
    static int M;
    static int[] prices;
    static char[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        prices = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(in.readLine());
        solution();
    }

    public static void solution() {
        // 최소 비용 숫자로 자릿수 최대로 늘이기
        int[] ret = getInit();
        int minPrices = ret[0];
        int minIdx = ret[1];
        int cnt = M / minPrices; // 자릿수
        answer = new char[cnt];
        for(int i = 0; i < cnt; i++) {
            answer[i] = (char) (minIdx + '0');
        }
        // 남은 돈으로 큰 수로 교체
        int remain = M % minPrices;
        int start = 0;
        for(int i = 0; i < answer.length; i++) {
            int cur = answer[i] - '0';
            int price = prices[cur];
            remain = find(remain, price, i);

            if (answer[start] == '0') { // 시작 수가 0이면 돈 반환받고 자릿수 줄이기
                remain += minPrices;
                start++;;
            }
        }

        if(start == cnt) {
            System.out.println(0);
            return;
        }
        for(int i = start; i < answer.length; i++) {
            System.out.print(answer[i]);
        }
    }

    // 가장 금액이 작은 수로 자릿수 맞추기. 금액 같으면 더 큰 수가 더 좋음
    public static int[] getInit() {
        int minPrices = Integer.MAX_VALUE, idx = -1;
        for(int i = 0; i < N; i++) {
            if(minPrices >= prices[i]) {
                minPrices = prices[i];
                idx = i;
            }
        }
        return new int[] {minPrices, idx};
    }

    // 현재 숫자를 더 큰 수로 바꿀 수 있는지
    public static int find(int remain, int curPrice, int curIdx) {
        for(int i = N-1; i >= 0; i--) {
            if(remain + curPrice >= prices[i]) {
                answer[curIdx] = (char) (i + '0');
                return remain + curPrice - prices[i];
            }
        }
        return remain;
    }
}
