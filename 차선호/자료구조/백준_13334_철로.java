import java.util.*;
import java.io.*;


class Path implements Comparable<Path>{
	int start;
	int end;
	
	public Path(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Path o) {
		if(end!=o.end) return end-o.end;
		else return start-o.start;
	}
	
	
}

public class Main {
	
	static int N,D,ANSWER;
	static List<Path> LIST;
	static PriorityQueue<Integer> PQ;
	
	public static void main(String[] args) throws Exception {
		
		
		init();
		solution();
	
		System.out.println(ANSWER);
	}
	
	static void solution() {
		ANSWER = 0;
		
		Collections.sort(LIST);
		PQ = new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			Path path = LIST.get(i);
			if(path.end-path.start>D) continue;
			PQ.add(path.start);
			
			while(!PQ.isEmpty()) {
				if(path.end-PQ.peek()<=D) break;
				PQ.poll();
			}
			ANSWER = Math.max(ANSWER, PQ.size());
		}
		
	}

	
	
	//초기 입력
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		LIST = new ArrayList<>();
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			int house = Integer.parseInt(input[0]);
			int company = Integer.parseInt(input[1]);
			if(house<=company) {
				LIST.add(new Path(house,company));
			}else {
				LIST.add(new Path(company,house));
			}
		}
		
		D = Integer.parseInt(br.readLine());
		
	}
}