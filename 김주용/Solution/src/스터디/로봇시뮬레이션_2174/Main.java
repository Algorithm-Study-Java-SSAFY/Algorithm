package 스터디.로봇시뮬레이션_2174;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Command {
    int robotNum;
    char type;
    int repeat;

    public Command(int robotNum, char type, int repeat) {
        this.robotNum = robotNum;
        this.type = type;
        this.repeat = repeat;
    }
}

class Robot {
    int num, d;

    public Robot(int num, int d) {
        this.num = num;
        this.d = d;
    }
}

public class Main {

    static int WIDTH, HEIGHT;
    static int N, M;

    static Robot[][] board;
    static Command[] commands;

    static HashMap<Integer, int[]> robots;
    // 북 동 남 서
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        WIDTH = Integer.parseInt(line[0]);
        HEIGHT = Integer.parseInt(line[1]);
        board = new Robot[HEIGHT][WIDTH];

        robots = new HashMap<>();
        line = in.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        for (int i = 0; i < N; i++) {
            line = in.readLine().split(" ");
            int x = Integer.parseInt(line[0]) - 1;
            int y = HEIGHT - Integer.parseInt(line[1]);
            int d = getDirect(line[2].charAt(0));

            board[y][x] = new Robot(i + 1, d);
            robots.put(i + 1, new int[]{y, x});
        }

        commands = new Command[M];
        for (int i = 0; i < M; i++) {
            line = in.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            char type = line[1].charAt(0);
            int repeat = Integer.parseInt(line[2]);

            commands[i] = new Command(num, type, repeat);
        }

        System.out.println(solution());
    }

    public static String solution() {
        for (Command cur : commands) {
            int[] point = robots.get(cur.robotNum);
            Robot curRobot = board[point[0]][point[1]];

            if (cur.type == 'L') {
                board[point[0]][point[1]].d = getLeft(curRobot.d, cur.repeat);
            } else if (cur.type == 'R') {
                board[point[0]][point[1]].d = getRight(curRobot.d, cur.repeat);
            } else {
                int ny = point[0], nx = point[1];
                for (int i = 0; i < cur.repeat; i++) {
                    ny += dy[curRobot.d];
                    nx += dx[curRobot.d];
                    if (!inRange(ny, nx)) {
                        return "Robot " + cur.robotNum + " crashes into the wall";
                    }
                    if (board[ny][nx] != null) {
                        return "Robot " + cur.robotNum + " crashes into robot " + board[ny][nx].num;
                    }
                }
                board[ny][nx] = curRobot;
                board[point[0]][point[1]] = null;
                robots.put(curRobot.num, new int[]{ny, nx});
            }
        }
        return "OK";
    }

    public static int getRight(int direct, int repeat) {
        for (int i = 0; i < repeat; i++) {
            direct = (direct + 1) % 4;
        }
        return direct;
    }

    public static int getLeft(int direct, int repeat) {
        for (int i = 0; i < repeat; i++) {
            direct--;
            if (direct < 0) {
                direct = 3;
            }
        }
        return direct;
    }


    public static int getDirect(char direct) {
        if (direct == 'N') {
            return 0;
        }
        if (direct == 'E') {
            return 1;
        }
        if (direct == 'S') {
            return 2;
        }
        return 3;
    }

    public static boolean inRange(int y, int x) {
        return -1 < y && y < HEIGHT && -1 < x && x < WIDTH;
    }
}
