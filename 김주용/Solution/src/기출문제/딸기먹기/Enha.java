package 기출문제.딸기먹기;


import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static int M;
    static HashMap<Integer, List<Integer>> apple = new HashMap<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int temp = 0;
        
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    if (temp > 0) {
                        apple.put(temp, Arrays.asList(i,j));
                    }
                }
            }
            M = apple.size();
            
            
            bw.write("#" + test_case + " " + minimumTurn() + "\n");
        }
        
        br.close();
        bw.close();
    }
    
    public static int minimumTurn() {
        int min = 0;
        int turn = 0;
        int direction = 0;
        List<Integer> position = new ArrayList<>();
        int x = 0;
        int y = -1;
        
        for (int i = 1; i <= M; i++) {
            position = apple.get(i);
            
            if (direction == 0) {
                if (x < position.get(0)) {
                    turn += 1;
                    if (y <position.get(1)) {
                        turn += 0;
                    } else {
                        turn += 1;
                    }
                } else {
                    turn += 3;
                }
                
            } else if (direction == 1) {
                if (y <position.get(1)) {
                    turn += 3;
                } else {
                    turn += 1;
                    if (x < position.get(0)) {
                        turn += 0;
                    } else {
                        turn += 1;
                    }
                }
                
            } else if (direction == 2) {
                if (x < position.get(0)) {
                    turn += 3;
                } else {
                    turn += 1;
                    if (y <position.get(1)) {
                        turn += 1;
                    } else {
                        turn += 0;
                    }
                }
                
            } else if (direction == 3) {
                if (y <position.get(1)) {
                    turn += 1;
                    if (x < position.get(0)) {
                        turn += 1;
                    } else {
                        turn += 0;
                    }
                } else {
                    turn += 3;
                }
                
            }
            
            x = position.get(0);
            y = position.get(1);
            min += turn;
            direction = (direction + turn) %4;
            turn = 0;
        }
        
        return min;
    }
}