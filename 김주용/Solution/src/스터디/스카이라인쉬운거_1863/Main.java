package 스터디.스카이라인쉬운거_1863;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class SkyLine {
    int x, y;
    public SkyLine(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N;
    static int answer;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());


        for(int i = 0; i <= N; i++) {
            int y;
            if(i < N) {
                String[] line = in.readLine().split(" ");
                y = Integer.parseInt(line[1]);
            } else {
                y = 0;
            }
            // 높이가 작아지면 건물
            while (!stack.isEmpty() && stack.peek() >= y) {
                if(stack.peek() != y) {
                    answer++;
                }
                stack.pop();
            }
            stack.push(y);;
        }

        System.out.println(answer);
    }

    public static void print(String a) {
        System.out.println(a);
        Stack<Integer> tempStack = new Stack<>();

        // 임시 스택에 원소들을 복사
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        // 임시 스택의 원소들을 출력하면서 원래 스택에 복원
        while (!tempStack.isEmpty()) {
            int element = tempStack.pop();
            System.out.print(element + " ");
            stack.push(element);
        }
        System.out.println();
    }
}
