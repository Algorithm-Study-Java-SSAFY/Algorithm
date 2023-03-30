import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        if (play_time.equals(adv_time))
			return "00:00:00";
		else {
			int play_len = convertToSecond(play_time);
			int adv_len = convertToSecond(adv_time);
			int[] timeline = new int[play_len + 1];

			int ending = 0;
			for (int i = 0; i < logs.length; i++) {
				String[] temp = logs[i].split("-");
				timeline[convertToSecond(temp[0])] += 1;
				ending = convertToSecond(temp[1]);
				timeline[ending] -= 1;
			}

			for (int i = 1; i < play_len; i++) {
				timeline[i] += timeline[i - 1];
			}

			System.out.println();

			long max_time = 0, max = 0, now = 0;
			int start = 0, end = adv_len - 1;
			
			for (int i = 0; i < adv_len; i++) {
				now += timeline[i];
			}

			max = now;

			while (end < play_len) {

				start++;
				end++;

				now += timeline[end] - timeline[start - 1];

				if (now > max) {
					max_time = start;
					max = now;
				}
			}
			return convertToTime((int)max_time);
		}
    }
    
    public static int convertToSecond(String time) {
		String[] timeList = time.split(":");
		int result = 0, multi = 3600;

		for (String t : timeList) {
			result += Integer.parseInt(t) * multi;
			multi /= 60;
		}

		return result;
	}

	public static String convertToTime(int second) {
		String[] result = new String[3];

		result[2] = String.valueOf(second % 60);
		second = (second - Integer.parseInt(result[2])) / 60;
		result[1] = String.valueOf(second % 60);
		second = (second - Integer.parseInt(result[1])) / 60;
		result[0] = String.valueOf(second);

		for (int i = 0; i < result.length; i++) {
			if (result[i].length() == 1)
				result[i] = "0" + result[i];
		}

		return result[0] + ":" + result[1] + ":" + result[2];
	}
}
