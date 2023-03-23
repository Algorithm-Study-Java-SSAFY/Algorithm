import java.util.*;
import java.io.*;


public class Main {

	static List<Integer> sensor = new ArrayList<>();
	static List<Integer> distances = new ArrayList<>();
	static int N,K;
	static int answer=0;
	
	public static void main(String[] args) throws Exception{
		init();
		getDistances();
		answer = count();
		System.out.println(answer);
	}
	
	static int count() {
		for(int i=0;i<N-K;i++) {
			answer += distances.get(i);
		}
		return answer;
	}
	
	static void getDistances() {
		for(int i=0;i<N-1;i++) {
			int distance = sensor.get(i+1)-sensor.get(i);
//			if(distance!=0) distances.add(distance);
			distances.add(distance);
		}
		Collections.sort(distances);
		
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			sensor.add(Integer.parseInt(input[i]));
		}
		Collections.sort(sensor);
	}
}