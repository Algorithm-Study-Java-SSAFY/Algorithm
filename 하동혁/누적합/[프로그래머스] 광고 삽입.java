import java.io.*;
import java.util.*;

class Solution {
    
    static String playTime;
	static String advTime; 
	static String[] logArr;
	
	static int playSeconds;
	static int advSeconds; 
	static int[][] logSeconds; 
	
	static HashMap<Integer, List<Integer>> startMap; // 시작, 종료 
	static HashMap<Integer, List<Integer>> endMap;  // 종료 , 시작 
	static long[] timeArr; 
	
	static long maxDif; 
	static long maxStart; 
    
    public String solution(String play_time, String adv_time, String[] logs) {
		
		playTime = play_time;
		advTime = adv_time; 
		logArr = logs; 
        
		playSeconds = 0; 
		advSeconds = 0; 
		logSeconds = new int[logArr.length][2]; // 시작, 종료
		changeSecond();
        
        if(playSeconds == advSeconds){
            return "00:00:00";    
        }
        else{
            startMap = new HashMap<>(); // 시작 , 종료 
            endMap = new HashMap<>(); 
            timeSetting();

            timeArr = new long[playSeconds]; // 초로 변경한 시간 크기의 배열  
            startSetting();

            timeStack(); 

            maxDif = 0L; 
            maxStart =0L; 
            find();

            String answer = "";

            String s1 = String.valueOf(maxStart/3600);
            if(s1.length() == 1) s1 = 0 + s1;
            answer += s1 + ":";
            maxStart = maxStart - (maxStart/3600)*3600;

            String s2 = String.valueOf(maxStart/60);
            if(s2.length() == 1) s2 = 0 + s2;
            answer += s2 + ":";
            maxStart = maxStart - (maxStart/60)*60; 

            String s3 = String.valueOf(maxStart);
            if(s3.length() == 1) s3 = 0 + s3;
            answer += s3;

            return answer;            
        }
		

    }
    
    
    static void find() {
        Long dif = timeArr[advSeconds-1];
        if(maxDif < dif) {
				maxDif  = dif;
				maxStart = 0; 
		}
        
		for(int i=1; i<=timeArr.length-advSeconds; i++) {
			dif = timeArr[i+advSeconds-1] - timeArr[i-1];
			if(maxDif < dif) {
				maxDif  = dif;
				maxStart = i; 
			}
		}
	}
	
	
	static void timeStack() {
		long num = 0; 
		for(int i=1; i<timeArr.length; i++) {
			if(timeArr[i]!=0) {
				num = num + timeArr[i];
				timeArr[i] = num + timeArr[i-1];
			}else if(timeArr[i] == 0) {
				timeArr[i] = timeArr[i-1] + num;
			}
			
			if(endMap.containsKey(i)) {
				int size = endMap.get(i).size();
				num = num - size; 
                timeArr[i] -= size;
			}

		}
	}
	
	// 3. 각 영상들의 시작 시간을 배열에 표시  - timeArr
	static void startSetting() {
		
		for(int i=1; i<timeArr.length; i++) {
			if(startMap.containsKey(i)) {
				int size = startMap.get(i).size();
				timeArr[i] = size; 
			}
		}
		
	}
	
	// 2. 각 영상들의 시작 종료 Map생성 
	static void timeSetting() {
		for(int i=0; i<logSeconds.length; i++) {
			int start = logSeconds[i][0];
			int end = logSeconds[i][1]; 
			if(!startMap.containsKey(start)) {
				startMap.put(start, new ArrayList<>());
				startMap.get(start).add(end);
			}else {
				startMap.get(start).add(end);
			}
			
			if(!endMap.containsKey(end)) {
				endMap.put(end, new ArrayList<>());
				endMap.get(end).add(start);
			}else {
				endMap.get(end).add(start);
			}
			
			
		}
	}

	
	
	// 1. 각 영상들의 시간을 초로 변경 
	static void changeSecond() {
		String[] s1 = playTime.split(":");
		playSeconds += 3600 * Integer.parseInt(s1[0]); 
		playSeconds += 60 * Integer.parseInt(s1[1]);
		playSeconds += Integer.parseInt(s1[2]);

		String[] s2 = advTime.split(":");
		advSeconds += 3600 * Integer.parseInt(s2[0]); 
		advSeconds += 60 * Integer.parseInt(s2[1]);
		advSeconds += Integer.parseInt(s2[2]);
		
		
		for(int i=0; i<logArr.length; i++) {
			String[] s3 = logArr[i].split("-");
			String[] s4 = s3[0].split(":");
			String[] s5 = s3[1].split(":");
			int start = 0; 
			start += 3600 * Integer.parseInt(s4[0]); 
			start += 60 * Integer.parseInt(s4[1]);
			start += Integer.parseInt(s4[2]);
			logSeconds[i][0] = start;
			
			int end = 0; 
			end += 3600 * Integer.parseInt(s5[0]); 
			end += 60 * Integer.parseInt(s5[1]);
			end += Integer.parseInt(s5[2]);
			logSeconds[i][1] = end; 
		}
		
		
	}
}