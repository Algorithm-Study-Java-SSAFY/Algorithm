import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> pMap = new HashMap<>();

        for (String people : participant) {
            if (pMap.containsKey(people)) {
                pMap.put(people, pMap.get(people) + 1);
            } else {
                pMap.put(people, 1);
            }
        }

        for (String compPeople : completion) {
            pMap.replace(compPeople, pMap.get(compPeople) - 1);
        }

        for (String people : participant) {
            if (pMap.get(people) > 0) {
                answer = people;
                break;
            }
        }
        return answer;
    }
}