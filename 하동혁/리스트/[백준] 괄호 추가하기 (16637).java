import java.util.*;
import java.io.*;

/*
 * 연산자 우선순위는 모두 동일 -> 왼쪽부터 순서대로 계산해야함  
 * 수식은 0~9 사이의 정수 
 * 연산자는 + , - , *
 * 
 * 수식의 길이는 1~19 
 * 
 * 괄호를 처서 최댓값을 구해야함 -> 중첩된 괄호는 X
 * 괄호 안에는 연산자가 하나만 들어가 있어야 함 
 * 
 * 수식은 언제나 홀수 => 수식의 길이가 N일 경우 연산자 수는 N/2이다. 
 */

public class Main {
	
	static int N; // 수식의 길이 
	static int operCnt; 
	static String data; // 수식 저장
	static HashSet<String> opSet = new HashSet<>();
	static List<String> combiList = new ArrayList<String>(); 
	static int[] visited;
	static int[] changeIdx; 
	static String[] newData; 
	static Deque<String> dq;
	static List<Integer> answer = new ArrayList<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		data = br.readLine(); 
		operCnt = N/2; 
		visited = new int[operCnt];
		
		opSet.add("+");
		opSet.add("-");
		opSet.add("*");

		if(data.length()==1) {
			System.out.println(Integer.parseInt(String.valueOf(data.charAt(0))));
		}else {
			makeCombi(0,"");

			// 조합을 하나씩 계산 
			for (int i=0; i<combiList.size(); i++) {
				change(combiList.get(i));
			}
			Collections.sort(answer);
			System.out.println(answer.get(answer.size()-1));
		}

	}
	
	
	// 조합 계산 -> s는 각 조합의 인덱스를 가지고 있음.
	// 수식의 길이가 최대 19이기 때문에 s의 최대 인덱스는 N/2인 9가 최대로 문자열로 붙여서 사용 가능 (한자리 뿐이라서)
	// 조합 인덱스를 수식 인덱스로 변경 => (조합인덱스 * 2) + 1
	static void change(String s) { 
		
		// 조합 인덱스를 => 수식 인덱스로 전환 
		// changeIdx는 연산자의 위치이고 이 연산자의 좌우 정수를 둘러싸는 괄호를 만든다. 
		changeIdx = new int[s.length()];  
		newData = data.split("");
		
		for (int i=0; i<s.length(); i++) {
			changeIdx[i] = Integer.parseInt(String.valueOf(s.charAt(i))) * 2 + 1;
		}

		// 괄호 내부를 계산한 결과를 setNum에 담는다. 
		for(int i=0; i<changeIdx.length; i++) {
			int idx =changeIdx[i];
			int num  = operator(data.charAt(idx), idx);
			newData[idx-1] = "A";
			newData[idx+1] = "A";
			newData[idx] = String.valueOf(num);
		}
//		System.out.println(Arrays.toString(newData));
		
		dq = new ArrayDeque<String>(); 
		for(int i=0; i<newData.length; i++) {
			if(!newData[i].equals("A")) {
				dq.add(newData[i]);
			}
		}
		
//		System.out.println(dq);
		
		// 계산 
		calculate();
		
	}
	
	// newData 계산  => A는 무시
	static void calculate() {
		while(dq.size()!=1) { // 앞에 3개 빼고 계산 후 다시 앞으로 넣기 
			int left = Integer.parseInt(dq.removeFirst());
			String op = dq.removeFirst();
			int right = Integer.parseInt(dq.removeFirst());
			int res = 0; 
			if(op.equals("+")) {
				res = left+right;
			}else if(op.equals("-")) {
				res = left-right;
			}else if(op.equals("*")) {
				res = left*right;
			}
			
			dq.addFirst(String.valueOf(res));
		}
		answer.add(Integer.parseInt(dq.removeFirst()));
	}
	
	// 괄호 정리 
	static int operator(char op, int idx) { // op : 연산자 / idx: 연산자 인덱스 
		int res = 0; 
		int left = Integer.parseInt(newData[idx-1]);
		int right = Integer.parseInt(newData[idx+1]);
		
		if(op == '+') {
			res = left+right;
		}else if(op == '-') {
			res = left-right;
		}else if(op == '*') {
			res = left*right;
		}
		
		return res; 
	}

	
	// 괄호가 위치할 조합을 생성 - 순서 O / 중복 X (== 부분집합) 
	// 괄호 X를 기준 양 옆에는 또 다른 괄호가 올 수 없도록 조건을 추가 
	static void makeCombi(int n, String s) {
		int cnt = 0;
		for(int i=n; i<operCnt; i++) {
			if(visited[i] == 0) {
				boolean check1 = false;
				boolean check2 = false; 
				cnt++; 
				visited[i] = 1; 
				if(i-1>=0 && visited[i-1] == 0) {
					visited[i-1] = 1;  
					check1 = true; 
				}
				if(i+1<operCnt && visited[i+1] == 0) {
					visited[i+1] = 1;
					check2 = true;
				}
				
				combiList.add(s+i);
				makeCombi(i+1, s+i);
				
				visited[i] = 0; 
				if(check1) visited[i-1] = 0;  
				if(check2) visited[i+1] = 0;
			}
		}
		if(cnt==0) return; 
	}
	


}