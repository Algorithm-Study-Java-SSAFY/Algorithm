import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> participantHash = new HashMap<String, Integer>();

        for (String name : participant) {
            participantHash.put(name, participantHash.getOrDefault(name, 0) + 1);
        }

        for (String name : completion) {
            participantHash.put(name, participantHash.get(name) - 1);
        }
        
        for (Map.Entry<String, Integer> entry: participantHash.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
            }
        }
        return answer;
    }
}