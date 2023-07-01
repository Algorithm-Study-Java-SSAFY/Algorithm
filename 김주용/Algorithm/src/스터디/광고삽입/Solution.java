package 스터디.광고삽입;



public class Solution {
	
	static long retSum = 0;
	static long retStart = 0;
	
	static long[] culSums;
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String playTime = "02:03:55";
		String advTime = "00:14:15";
		String[] logs = new String[] { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30" };
		s.solution(playTime, advTime, logs);
	}

	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		
		int playTime = getSec(play_time);
		int advTime = getSec(adv_time);
		culSums = new long[playTime + 1];
		for(String log : logs) {
			String[] info = log.split("-");
			int start = getSec(info[0]);
			int end = getSec(info[1]);
			
			culSums[start] += 1;
			culSums[end] -= 1;
		}
		
		for(int i = 1; i < playTime; i++) { // i 시간에 로그 수 
			culSums[i] += culSums[i-1];
		}
		
		for(int i = 1; i < playTime; i++) { // i 시간에 누적 시간 
			culSums[i] += culSums[i-1];
		}

		for(int end = playTime - 1; end >= advTime; end--) { // 마지막 시간 미만 시작 시간은 이상 
			long sum = culSums[end] - culSums[end - advTime]; 
			if(end - advTime == 0) {
				sum = culSums[end];
			}
			if(sum >= retSum) {
				retSum = sum;
				retStart = end - advTime + 1;
			}
		}
		
		answer = getDate(retStart);
		System.out.println(answer);
		System.out.println(retSum);
		return answer;
	}
	
	
	public static int getSec(String value) {
		String[] data = value.split(":");
		return Integer.parseInt(data[0]) * 3600 + Integer.parseInt(data[1]) * 60 + Integer.parseInt(data[2]);
	}
	
	public static String getDate(long maxTime) {
		long hour = maxTime / 3600;
		long minute = (maxTime - 3600 * hour) / 60;
		long sec = maxTime - 3600 * hour - 60 * minute;
		String HH = String.valueOf(hour);
		String mm = String.valueOf(minute);
		String ss = String.valueOf(sec);
		if(HH.length() < 2) {
			HH = "0" + HH;
		}
		if(mm.length() < 2) {
			mm = "0" + mm;
		}
		if(ss.length() < 2) {
			ss = "0" + ss;
		}
		return HH + ":" + mm + ":" + ss;
	}
}
