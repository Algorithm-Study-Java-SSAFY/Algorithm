package 쇠막대기_10799;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Stack<Character> stack = new Stack<Character>();
        int answer = 0;
        for (int i = 0; i < line.length(); i++) {   // 100,000
            char cur = line.charAt(i);
            if (stack.isEmpty() || cur == '(') {
                stack.push(cur);
                continue;
            }
            // 괄호 닫히는 지점
            stack.pop();
            if (line.charAt(i-1) == '(') {  // 레이저 지점: 남아있는 막대기들은 더 길어지므로 레이저와 무조건 겹치게 됨
                answer += stack.size();
            } else {    // 쇠 막대기가 끝나는 지점: 레이저 기준 왼쪽은 모두 더해줬으니까 마지막 남은 오른쪽 1개 +
                answer += 1;
            }
        }

        System.out.println(answer);
        br.close();
    }
}
