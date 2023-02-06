import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split("");
        Stack<String> stack = new Stack<>();

        int cnt = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("(")) {
                stack.push(tokens[i]);
            } else if (tokens[i].equals(")") && tokens[i - 1].equals("(")) {
                stack.pop();
                cnt += stack.size();
            } else {
                cnt++;
                stack.pop();
            }
        }
        System.out.println(cnt);
    }
}
