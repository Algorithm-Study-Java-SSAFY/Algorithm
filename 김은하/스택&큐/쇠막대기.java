import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] stick = str.split("");
		Stack<String> stack = new Stack<>();
		int left = 0;
		int right = 0;
		int answer = 0;

		for (int i = 0; i < stick.length; i++) {
			if (stick[i].equals("(")) {
				left += 1;
			} else {
				right += 1;
			}

			if (!stack.empty()) {
				if (stack.peek().equals("(") && stick[i].equals(")")) {
					answer += left - right;
				}
				
				if (stack.peek().equals(")") && stick[i].equals(")")) {
					answer += 1;
				}
			}

			stack.push(stick[i]);
		}
		System.out.println(answer);
	}

}
