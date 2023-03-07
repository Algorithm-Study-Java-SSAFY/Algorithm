package testBaek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 괄호추가하기 {
	public static int maxNum;
	public static String formula;
	public static boolean[] possibleOp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		formula = br.readLine();
		maxNum = calc(formula);
		for (int i = 0; i <= n / 2; i++) {
			possibleOp = new boolean[n / 2];
			getPossibleOp(0, n, i, 0);
		}

		System.out.println(maxNum);
	}

	public static void getPossibleOp(int start, int n, int r, int level) {
		if (level == r) {
			if (checkPossible(n)) {
				String newFormula = makeNewF();
				maxNum = Math.max(maxNum, calc(newFormula));
				return;
			}
		}
		for (int i = start; i < n / 2; i++) {
			possibleOp[i] = true;
			getPossibleOp(i + 1, n, r, level + 1);
			possibleOp[i] = false;
		}
	}

	public static boolean checkPossible(int n) {
		for (int i = 0; i < n / 2 - 1; i++) {
			if (possibleOp[i] && possibleOp[i + 1])
				return false;
		}
		return true;
	}

	public static int calc(String formula) {
		Stack<Character> opStack = new Stack<>();
		Queue<Character> tmpQue = new LinkedList<>();
		Stack<Integer> finalStack = new Stack<>();

		for (int i = 0; i < formula.length(); i++) {
			char inputC = formula.charAt(i);
			if (opStack.isEmpty() || inputC == '(')
				opStack.push(inputC);
			else {
				if (inputC == ')') {
					char tmpC = opStack.peek();
					while (tmpC != '(') {
						tmpQue.offer(opStack.pop());
						tmpC = opStack.peek();
					}
					opStack.pop();
				} else if (inputC == '*' || inputC == '+' || inputC == '-') {
					while (!opStack.isEmpty()) {
						char tmpC = opStack.peek();
						if (tmpC == '(')
							break;
						tmpQue.offer(opStack.pop());
					}
					opStack.push(inputC);
				} else {
					tmpQue.offer(inputC);
				}
			}
		}

		while (!opStack.isEmpty())
			tmpQue.offer(opStack.pop());

		int len = tmpQue.size();
		for (int i = 0; i < len; i++) {
			char c = tmpQue.poll();
			if (c == '*' || c == '+' || c == '-') {
				int num1 = finalStack.pop();
				int num2 = finalStack.pop();
				if (c == '*')
					finalStack.push(num1 * num2);
				else if (c == '+')
					finalStack.push(num1 + num2);
				else
					finalStack.push(num2 - num1);
			} else {
				finalStack.push(Integer.parseInt("" + c));
			}
		}
		return finalStack.pop();
	}

	public static String makeNewF() {
		Stack<Character> newF = new Stack<>();

		for (int i = 0; i < formula.length(); i++) {
			char c = formula.charAt(i);
			if (c == '*' || c == '+' || c == '-') {
				if (possibleOp[(i - 1) / 2] == true) {
					char tC = newF.pop();
					newF.push('(');
					newF.push(tC);
					newF.push(c);
					newF.push(formula.charAt(++i));
					newF.push(')');
				} else {
					newF.push(c);
				}
			} else {
				newF.push(c);
			}
		}
		String result = "";
		
		Iterator iter = newF.iterator();
		while(iter.hasNext()) {
			result += iter.next();
		}
		return result;
	}
}
