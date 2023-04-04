import java.util.*;
import java.io.*;


public class Main {
	
	static BufferedReader br;
	static int M,len,cnt,mid;
	static PriorityQueue<Integer> leftPq;
	static PriorityQueue<Integer> rightPq;
	static List<Integer> result;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++) {
			init();
			input();
			output();
			System.out.println(sb);
		}
	}
	
	static void output() {
		sb.append(cnt+"\n");
		for(int i=0;i<cnt;i++) {
			if(i%10==0) sb.append(result.get(i));
			else if(i%10==9) sb.append(" "+result.get(i)+"\n");
			else sb.append(" "+result.get(i));
		}
	}
	
	static void input() throws Exception{
		for(int i=0;i<M/10+1;i++) {
			String[] input = br.readLine().split(" ");
			for(String s:input) {
				int num = Integer.parseInt(s); //현재 숫자 
				if(len==0) mid = num; //맨 처음에는 바로 mid
				else {
//					System.out.println(leftPq.peek()+"////"+mid+"/////"+rightPq.peek());
					if(leftPq.size()==rightPq.size()) { //양 큐의 사이즈가 같으면
						if(num >= mid) rightPq.add(num); 
						else leftPq.add(num);
					}else if(leftPq.size()>rightPq.size()) { //왼쪽 큐의 크기가 더 크다면
						if(num < mid) { //num이 mid보다 작다면
							rightPq.add(mid); //우선 mid를 오른쪽 큐에 넣어주고
							if(num >= leftPq.peek()) mid = num; //num이 왼쪽 큐 가장 위 원소보다 크거나 같으면 그냥 num이 mid가 된다.
							else { //num이 왼쪽 큐 가장 위 원소보다 작으면
								mid = leftPq.poll(); //mid는 왼쪽 큐 가장 위 원소가 되고
								leftPq.add(num); //num은 왼쪽 큐에 넣는다
							}
						}else rightPq.add(num); //num이 mid보다 크거나 같으면 num을 오른쪽 큐에 넣는다.
						
					}else if(leftPq.size()<rightPq.size()) { //오른쪽 큐의 크기가 더 크다면
						if(num > mid) { //num이 mid보다 크면
							leftPq.add(mid); //우선 mid를 왼쪽 큐에 넣는다.
							if(num <= rightPq.peek()) mid = num; //num이 오른쪽 큐 가장 위 원소보다 작거나 같으면 그냥 num이 mid가 된다.
							else { //num이 오른쪽 큐 가장 위 원소보다 크면
								mid = rightPq.poll(); //mid는 오른쪽 큐 가장 위 원소가 되고
								rightPq.add(num); //num은 오른쪽 큐에 넣어준다.
							}
						}else leftPq.add(num); //num이 mid보다 작거나 같으면 num을 왼쪽 큐에 넣는다.
					}
				}
				len++;
				if(len%2==1) {
					cnt++;
					result.add(mid);
				}
			}
		}
	}
	
	static void init() throws Exception{
		M = Integer.parseInt(br.readLine());
		len = 0;
		cnt = 0;
		result = new ArrayList<>();
		leftPq = new PriorityQueue<>(Collections.reverseOrder());
		rightPq = new PriorityQueue<>();
		sb = new StringBuffer();
	}
}