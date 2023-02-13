import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {

        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < participant.length; i++) {
            if (hm.containsKey(participant[i])) {
                int cnt = (int) hm.get(participant[i]);
                hm.put(participant[i], ++cnt);
            } else {
                hm.put(participant[i], 1);
            }
        }

        for (int j = 0; j < completion.length; j++) {
            int cnt = (int) hm.get(completion[j]);
            String name = completion[j];
            if (cnt == 1) {
                hm.remove(name);
            } else {
                hm.put(name, --cnt);
            }
        }

        String temp = hm.keySet().toString();
        answer = temp.substring(1, temp.length() - 1);

        return answer;
    }
}