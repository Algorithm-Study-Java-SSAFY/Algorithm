package 괄호추가하기_16637;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int N;
    static String line;

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(in.readLine());
        line = in.readLine();

        solution();
        out.write(Integer.toString(answer));
        in.close();
        out.close();
    }

    public static void solution() {
        int operator = line.length() / 2; //연산자 수
        int maxNum = (int) Math.round(operator / 2.0); // 최대 추가 괄호 쌍 수
        ArrayList<Character> operators = new ArrayList<>();

        for(int i = 0; i < line.length(); i++) {
            if("+-*/".indexOf(line.charAt(i)) >= 0) {
                operators.add(line.charAt(i));
            }
        }
        boolean[] visited = new boolean[operators.size()];

        dfs(operators, visited, 0, maxNum);
    }

    public static void dfs(ArrayList<Character> operators, boolean[] visited, int idx, int maxNum) {

        System.out.println(Arrays.toString(visited));
        go(operators, visited);
        if(idx >= operators.size()) {
            return;
        }

        for(int i = idx; i < operators.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(i == 0 || !visited[i-1]) {
                    visited[i] = true;
                    dfs(operators, visited, i + 2, maxNum);
                    visited[i] = false;
                } else {
                    dfs(operators, visited, i + 1, maxNum);
                }

                visited[i] = false;
            }
        }

    }

    public static int go(ArrayList<Character> operators, boolean[] visited) {
        int ret = 0;
    	Queue<Integer> queue = new LinkedList<Integer>();
       
    	for(int i = 0; i < visited.length; i++) {
    		int idx = i * 2 + 1;
    		if (visited[i]) {
    			queue.add(idx);
    		} 
    	}
    	
     
        return ret;
    }

    public static int caculate(int a, int b, char operator) {
        if (operator == '+') {
            return a + b;
        }
        if (operator == '-') {
            return a - b;
        }
        if (operator == '*') {
            return a * b;
        }
        if (operator == '/') {
            return a / b;
        }
        return 0;
    }

}
