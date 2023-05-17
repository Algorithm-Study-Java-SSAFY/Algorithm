import java.util.*;
import java.io.*;

public class Main{
	
	static int N,K,ANSWER,FINISH;
	static List<int[]> HEIGHT;
	static PriorityQueue<int[]> HOLES;
	
	
	public static void main(String[] args) throws Exception {
		init();
		int idx = 0;
		int[] hole = new int[3];
		while(!HOLES.isEmpty()) {
			hole = HOLES.poll();
			List<int[]> height = new ArrayList<>();
			while(true) {
				if(HEIGHT.get(idx)[1]==hole[0]) {
					height.add(HEIGHT.get(idx));
					height.add(hole);
					solution(height);
					idx++;
					break;
				}
				else {
					height.add(HEIGHT.get(idx));
					idx++;
				}
			}
		}
		List<int[]> height = new ArrayList<>();
		height.add(hole);
		while(idx<HEIGHT.size()) {
			height.add(HEIGHT.get(idx));
			idx++;
		}
		solution(height);
		
		System.out.println(ANSWER);
	}
	
	
	static void solution(List<int[]> height) {
//		for(int i=0;i<height.size();i++) {
//			System.out.println(Arrays.toString(height.get(i)));
//		}
//		System.out.println("---------------------------------");
		int[][] leftRight = new int[height.size()][2];
		
		for(int i=1;i<height.size()-1;i++) {
			int h = height.get(i)[2];
			int left = h;
			int right = h;
			for(int j=0;j<height.size();j++) {
				if(j<=i) {//왼쪽 minHeight값 저장
					if(height.get(j)[2]<left) {
						left = height.get(j)[2];
					}
				}else {//오른쪽 minHeight값 저장
					if(height.get(j)[2]<right) {
						right = height.get(j)[2];
					}
				}
			}
			leftRight[i][0] = left;
			leftRight[i][1] = right;
//			System.out.println(Arrays.toString(height.get(i))+"//"+Arrays.toString(leftRight[i]));
		}
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		
		for(int i=1;i<height.size()-1;i++) {
			int minusHeight = Math.max(leftRight[i][0], leftRight[i][1]);
			int total = (height.get(i)[1]-height.get(i)[0])*height.get(i)[2];
			int remain = total - minusHeight*(height.get(i)[1]-height.get(i)[0]);
//			System.out.println(Arrays.toString(height.get(i))+"//"+remain);
			ANSWER += remain;
		}
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
	}
	
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		HEIGHT = new ArrayList<>();
		HEIGHT.add(new int[] {0,0,0});
		
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N-2;i+=2) {
			String[] input1 = br.readLine().split(" ");
			String[] input2 = br.readLine().split(" ");
			HEIGHT.add(new int[] {Integer.parseInt(input1[0]),Integer.parseInt(input2[0]),Integer.parseInt(input1[1])});
		}
		FINISH = Integer.parseInt(br.readLine().split(" ")[0]);
		HEIGHT.add(new int[] {FINISH,FINISH,0});
		
		HOLES = new PriorityQueue<>((o1,o2)->{
			return o1[0]-o2[0];
		});
		K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			input = br.readLine().split(" ");
			int startHole = Integer.parseInt(input[0]);
			int endHole = Integer.parseInt(input[2]);
			int heightHole = Integer.parseInt(input[1]);
			HOLES.add(new int[] {startHole,endHole,heightHole});
		}
		
		ANSWER = 0;
	}
}