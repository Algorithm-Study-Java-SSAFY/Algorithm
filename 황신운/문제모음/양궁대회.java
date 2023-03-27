package testBaek;

import java.util.List;

class 양궁대회 {
    public static int N, maxRyanScore;
    public static int[] apeach, ryan;
    public static List<int[]> resultList;
    public static int[] resultArr = new int[11];
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        N = n;
        apeach = info.clone();
        maxRyanScore = -1;
        solve();
        if(maxRyanScore < 0) return new int[] {-1};
        //answer = resultList.get(resultList.size()-1);
        answer = resultArr.clone();
        return answer;
    }

    public static void solve() {
        ryan = new int[11];
        shoot(0,0);
    }

    public static void shoot(int start, int level) {
        if(level == N) {
            checkIfRyanWins();
            return;
        }

        for(int i=start; i<11; i++) {
            ryan[i] += 1;
            shoot(i, level+1);
            ryan[i] -= 1;
        }
    }

    public static void checkIfRyanWins() {
        int ryanScore = 0, apeachScore = 0;
        for(int i=0, score = 10; i<11; i++, score--) {
            if(apeach[i] + ryan[i] == 0) continue;
            else if(apeach[i] < ryan[i]) ryanScore += score;
            else if(apeach[i] >= ryan[i]) apeachScore += score;
        }

        if(ryanScore > apeachScore) {
            if((ryanScore - apeachScore) >= maxRyanScore) {
                if((maxRyanScore == (ryanScore - apeachScore)) && !check(ryan)) {
                    return;
                }

                maxRyanScore = (ryanScore - apeachScore);
                resultArr = ryan.clone();
            } 
        }

    }
    public static boolean check(int[] ryan) {
        for(int i = 10; i >= 0; i--) {
            if(ryan[i] < resultArr[i]) {
            	// result 0100111 
                // ryan 0011011 -> ryan[i] < resultArr[i] 조건이 없으면, true가 나중에 return 됨
                return false;
            } else if(ryan[i] > resultArr[i]) {
                return true;
            }
        }
        return false;
    }
}