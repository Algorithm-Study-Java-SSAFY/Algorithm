import java.util.*;
import java.io.*;

public class Main {

	static int N, K;
	static int[] sensor;
	static int[] sensorDis; 
	static int answer; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		sensor = new int[N];
		sensorDis = new int[N-1];
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			sensor[i] = Integer.parseInt(s[i]);
		}
		
		Arrays.sort(sensor);
		
		// 센서 사이 거리 
		for(int i=0; i<N-1; i++) {
			sensorDis[i] = sensor[i+1] - sensor[i];
		}
		
		Arrays.sort(sensorDis);
		
		for(int i=0; i<N-K; i++) {
			answer += sensorDis[i];
		}
		
		System.out.println(answer);
		
	}



}