package 모의고사;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        String[] pattern = { "12345", "21232425", "3311224455"};
        String[] people = { "12345", "21232425", "3311224455" };
        // 정답 개수보다 길게 패턴 만들기
        int n = answers.length / 5;
        for(int i = 0; i < n; i++) {
            people[0] += pattern[0];
            people[1] += pattern[1];
            people[2] += pattern[2];
        }
        // 점수 구하기
        int[] score = new int[3];
        int maxScore = 0;
        for(int i = 0; i < answers.length; i++) {
            for(int j = 0; j < 3; j++) {
                if(answers[i] == people[j].charAt(i) - '0') {
                    score[j] += 1;
                    maxScore = Math.max(score[j], maxScore);
                }
            }
        }
        // 최고점자 구하기
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < 3; i++) {
            if(score[i] == maxScore){
                ret.add(i + 1);
            }
        }

        return ret.stream().mapToInt(i -> i.intValue()).toArray();
    }
}