import java.io.*;
import java.util.*;

//주석
public class Solution {
 
	static List<int[]> result = new ArrayList<>();
	static int[] apeach, ryan;
	static int N;
	static int max = 0;
	static int[] staticAnswer;
	
    public static void main(String[] args) throws Exception {
        int n = 9;
        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(n,info)));
    }
    static int[] solution(int n, int[] info) {
    	apeach = info;
    	N = n;
    	ryan = new int[11];
        int[] answer = {};
        backtracking(0,0);
        if(max != 0) answer = getAnswer(); //중복 정답 중 가장 낮은 점수 많이 맞춘 경우 찾기
        else return new int[] {-1};
        return answer;
    }
    static int[] getAnswer() {
    	for(int[]a:result) {
    		System.out.println(Arrays.toString(a));
    	}
    	int[] arr = new int[11];
    	for(int i=10;i>-1;i--) {
    		for(int j=0;j<result.size();j++) {
    			if(result.get(j)[i] > arr[i]) return result.get(j);
    		}
    	}
    	return arr;
    }
    static void search(int[] ryan) {
    	int score = calculation();
    	if(score>0 && score >= max) {
    		if(score>max) result.clear(); //최댓값 갱신시 리스트 클리어
    		max = score;
    		result.add(ryan.clone());
    	}
    }
    static int calculation() {
    	int ryanScore = 0;
    	int apeachScore = 0;
    	
    	for(int i=0;i<11;i++) {
    		if(ryan[i]>apeach[i]) ryanScore += 10-i;
    		else if(apeach[i]!=0 && apeach[i]>=ryan[i]) apeachScore += 10-i;
    	}
    	
    	return ryanScore - apeachScore;
    }
    static void backtracking(int start, int cnt) {
    	if(cnt==N) { //모든 화살을 쐈을 때
//    		System.out.println(Arrays.toString(ryan));
    		search(ryan); //이 겨우 점수 계산
    		return;
    	}
    	for(int i=start;i<10;i++) { //해당 점수에서
    		if(N-cnt>apeach[i]) { //남은 화살로 apeach보다 더 많이 쏠 수 있으면
    			ryan[i] = apeach[i]+1;
    			cnt += apeach[i]+1;
    			backtracking(i+1,cnt);
    			cnt -= apeach[i]+1;
    			ryan[i] = 0;
    		}
    	}
    	//마지막까지 왔을 때는 남은 화살 다 쏜다.
    	ryan[10] = N-cnt;
			cnt = N;
			backtracking(11,cnt);
			cnt -= ryan[10];
			ryan[10] = 0;
    }
}