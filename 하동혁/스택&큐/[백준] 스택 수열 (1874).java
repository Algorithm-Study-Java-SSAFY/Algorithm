import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();

		int cnt = 1;
		for (int i = 1; i <= n; i++) {
			int k = Integer.parseInt(br.readLine()); 
			while(cnt<=k) {
				stack.push(cnt);
				sb.append("+").append("\n");
				cnt++;
			}
		
			// pop
			if(!(stack.isEmpty()) && k == stack.peek()) {
				stack.pop();
				sb.append("-").append("\n");
			}


		}

		sb.deleteCharAt(sb.lastIndexOf("\n"));
		if (stack.isEmpty()) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}

	}

}