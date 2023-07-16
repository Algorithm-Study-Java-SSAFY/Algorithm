import java.util.*;
import java.io.*;


class BOOM{
	String str;
	int score;
	
	BOOM(String str, int score){
		this.str = str;
		this.score = score;
	}
}

public class Main {
	
	static String S;
	static int M;
	static HashMap<String, Integer> SCORE_MAP;
	static PriorityQueue<BOOM> PQ;
	static int replace, ANSWER;
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		solution();
		
		System.out.println(ANSWER + S.length()-replace);
	}
	
	static void solution() {
		
		initPQ();
		
		removeStr();
		
	}
	
	static void removeStr() {
		while(!PQ.isEmpty()) {
			
			BOOM boom = PQ.poll();
			String str = boom.str;
			int score = boom.score;
			
			while(true) {
				int idx = S.indexOf(str); //문자열 일치 인덱스
				if(idx==-1) break; //문자열을 포함하지 않으면 넘어가
				int endIdx = idx+str.length(); //끝나는 인덱스
				for(int i=idx;i<endIdx;i++) { //해당 문자열을 _ 처리
					S = S.substring(0,i)+'_'+S.substring(i+1);
					replace++; //이미 점수처리된 문자열 길이 빼주려고
				}
				ANSWER += score;
//				System.out.println(S);
			}
			
		}
	}
	
	static void initPQ() {
		PQ = new PriorityQueue<>((o1,o2)-> {
			int o1Quot = S.length() / o1.str.length(); //o1의 문자열의 길이가 몇 개 들어가는지
			int o2Quot = S.length() / o2.str.length(); //o2의 문자열의 길이가 몇 개 들어가는지
			//각 몫만큼 점수 더하고 나머지를 1점 처리했을 때 높은 점수가 우선순위가 더 높다.
			return (o2.score*o2Quot + S.length()-o2.str.length()) - (o1.score*o1Quot + S.length()-o1.str.length());
		});
		
		for(String key : SCORE_MAP.keySet()) {
			PQ.add(new BOOM(key,SCORE_MAP.get(key)));
		}
	}
	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		M = Integer.parseInt(br.readLine());
		
		SCORE_MAP = new HashMap<>();
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			String str = input[0];
			int score = Integer.parseInt(input[1]);
//			int priority = score + (100-str.length());
			if(str.length()>score) continue;
			if(SCORE_MAP.containsKey(str)) {
				if(score>SCORE_MAP.get(str)) SCORE_MAP.put(str, score);
			}else {
				SCORE_MAP.put(str,score);
			}
		}
		replace = 0;
		ANSWER = 0;
	}
}