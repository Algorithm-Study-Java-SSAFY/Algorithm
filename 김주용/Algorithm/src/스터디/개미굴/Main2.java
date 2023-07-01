package 스터디.개미굴;

import java.io.*;
import java.util.*;

public class Main2 {
    static final String FLOOR = "--";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<String> cave = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String info = "";
            for (int j = 0; j < K; j++) {
                info += st.nextToken() + " ";
            }
            cave.add(info.trim());
        }

        Collections.sort(cave);

        String start = "", now = "";
        String[] written = new String[16];
        int floor = 1;

        for (String info : cave) {
            floor = 1;
            st = new StringTokenizer(info);
            start = st.nextToken();
            if (!start.equals(written[floor])) {
                written = new String[16];
                written[floor] = start;
                sb.append(start).append("\n");
            }

            floor++;

            while (st.hasMoreTokens()) {
                now = st.nextToken();
                if (!now.equals(written[floor])) {
                    written[floor] = now;
                    for (int i = 1; i < floor; i++) {
                        sb.append(FLOOR);
                    }
                    sb.append(now).append("\n");
                }
                floor++;
            }
        }

        System.out.print(sb);
        br.close();
    }
}



