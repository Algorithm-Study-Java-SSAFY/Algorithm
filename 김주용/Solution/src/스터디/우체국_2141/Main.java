package 스터디.우체국_2141;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Town {
    long distance;
    long people;
    public Town(long distance, long people) {
        this.distance = distance;
        this.people = people;
    }
}

public class Main {

    static int N;
    static Town[] towns;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());


        towns = new Town[N];
        for (int i = 0; i < N; i++) {
            String[] line = in.readLine().split(" ");
            towns[i] = new Town(Long.parseLong(line[0]), Long.parseLong(line[1]));
        }
    }

    static public void solution() {

    }
}
