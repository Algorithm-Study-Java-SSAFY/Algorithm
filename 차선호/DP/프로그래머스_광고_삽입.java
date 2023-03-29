
import java.util.*;
import java.io.*;

public class Solution {

	
	static List<int[]> log = new ArrayList<>();
	static int lenPlay, lenAdv;
	static long[] ps;
	static long[] dp;
	static long max = -1;
	static long point = 0;
	
	public static void main(String[] args) throws Exception{
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		System.out.println(solution(play_time, adv_time, logs));
	}
	
	static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        lenPlay = calculation(Integer.parseInt(play_time.substring(0,2)),Integer.parseInt(play_time.substring(3,5)),Integer.parseInt(play_time.substring(6,8)));
        lenAdv = calculation(Integer.parseInt(adv_time.substring(0,2)),Integer.parseInt(adv_time.substring(3,5)),Integer.parseInt(adv_time.substring(6,8)));
        for(int i=0;i<logs.length;i++) {
        	int s = calculation(Integer.parseInt(logs[i].substring(0,2)),Integer.parseInt(logs[i].substring(3,5)),Integer.parseInt(logs[i].substring(6,8)));
        	int e = calculation(Integer.parseInt(logs[i].substring(9,11)),Integer.parseInt(logs[i].substring(12,14)),Integer.parseInt(logs[i].substring(15,17)));
        	log.add(new int[] {s,e});
        }
        ps = new long[lenPlay];
		pSum(log);
		dp = new long[lenPlay];
		setDp();
		find();
		answer = intToStr(point);
        return answer;
    }
	
	// 정답 포인트 찾기
	static void find() {
		for(int i=0;i<lenPlay;i++) {
			if(dp[i]>max) {
				max = dp[i];
				point = i-lenAdv+1;
				if(point<0) point = 0;
			}
		}
	}
	
	// dp 배열 생성
	static void setDp() {
		dp[0] = ps[0];
		for(int i=1;i<lenAdv;i++) {
			dp[i] = ps[i] + dp[i-1];
		}
		for(int i=lenAdv;i<lenPlay;i++) {
			dp[i] += dp[i-1] + ps[i] - ps[i-lenAdv];
		}
	}
	
	// 누적합 이용해서 누적 고객 배열 생성
	static void pSum(List<int[]> log) {
		for(int[] data:log) {
			ps[data[0]]++;
			if(data[1]<lenPlay) ps[data[1]]--;
		}
		
		for(int i=1;i<lenPlay;i++) {
			ps[i] += ps[i-1];
		}
		
	}
	
	// 문자열 시간을 정수 시간으로
	static int calculation(int h, int m, int s) {
		int result = 0;
		result += h*3600 + m*60 +s;
		return result;
	}
	
	// 정수 시간을 문자열 시간으로
	static String intToStr(long time) {
		System.out.println(time);
		String str = "";
		long h = time/3600;
		time -= h*3600;
		long m = time/60;
		time -= m*60;
		long s = time;
		String sh = "";
		if(h<10) sh = "0"+String.valueOf(h);
		else sh = String.valueOf(h);
		String sm = "";
		if(m<10) sm = "0"+String.valueOf(m);
		else sm = String.valueOf(m);
		String ss = "";
		if(s<10) ss = "0"+String.valueOf(s);
		else ss = String.valueOf(s);
		
		str = sh+":"+sm+":"+ss;
		
		return str;
	}
	
	
}
