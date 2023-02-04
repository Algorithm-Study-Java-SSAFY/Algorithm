import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();
        String pipes = br.readLine();
        int answer = 0;

        for (int i = 0; i < pipes.length(); i++) {
            if (stack.empty() || pipes.charAt(i) == '(') {
                stack.push(pipes.charAt(i));
            } else {
                stack.pop();
                if (pipes.charAt(i - 1) == '(') {
                    answer += stack.size();
                } else {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);

    }
}