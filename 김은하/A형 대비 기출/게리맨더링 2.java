import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] A;
    static int[][] section;
    static int minGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        gerrymandering();
//        countSection(2, 4, 2, 2);
        bw.write(String.valueOf(minGap));

        br.close();
        bw.close();
    }

    public static void gerrymandering() {
        for (int x = 1; x <= A.length; x++) {
            for (int y = 1; y <= A.length; y++) {
                for (int d1 = 1; d1 <= A.length; d1++) {
                    for (int d2 = 1; d2 <= A.length; d2++) {
                        countSection(x, y, d1, d2);
                    }
                }
            }
        }
    }

    public static void countSection(int x, int y, int d1, int d2) {
        int[] population = new int[5];

        if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) {
           section = new int[N + 1][N + 1];
            // 경계선 긋기
            for (int i = 0; i <= d1; i++) {
                section[x+i][y-i] = 5;
                section[x+d2+i][y+d2-i] = 5;
            }
            for (int i = 0; i <= d2; i++) {
                section[x+i][y+i] = 5;
                section[x+d1+i][y-d1+i] = 5;
            }
            
            // 1번 구역
            for (int r = 1; r < x+d1; r++) {
                for (int c = 1; c <= y; c++) {
                    if (section[r][c] == 5)
                        break;
                    section[r][c] = 1;
                }
            }
            
            // 2번 구역
            for (int r = x+d2; r >= 1; r--) {
                for (int c = N; c > y; c--) {
                    if (section[r][c] == 5)
                        break;
                    section[r][c] = 2;
                }
            }
            
            // 3번 구역
            for (int r = x+d1; r <= N; r++) {
                for (int c = 1; c < y-d1+d2; c++) {
                    if (section[r][c] == 5)
                        break;
                    section[r][c] = 3;
                }
            }
            
            // 4번 구역
            for (int r = N; r > x+d2 ; r--) {
                for (int c = N; c >= y-d1+d2; c--) {
                    if (section[r][c] != 0)
                        break;
                    section[r][c] = 4;
                }
            }
            
            
            // 인구 계산
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (section[i][j] == 1)
                        population[0] += A[i][j];
                    else if (section[i][j] == 2)
                        population[1] += A[i][j];
                    else if (section[i][j] == 3)
                        population[2] += A[i][j];
                    else if (section[i][j] == 4)
                        population[3] += A[i][j];
                    else
                        population[4] += A[i][j];
                    
                }
            }
            

//            for (int i = 1; i <= N; i++) {
//                for (int j = 1; j <= N; j++) {
//                    System.out.print(section[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println("-----------------------------");
//            System.out.println();
            Arrays.sort(population);
            int temp = population[4] - population[0];
            minGap = minGap > temp ? temp : minGap;
        }
    }
}
