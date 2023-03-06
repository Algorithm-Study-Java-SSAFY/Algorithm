import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[][] ground;
	static final int L = 0;
	static final int D = 1;
	static final int R = 2;
	static final int U = 3;
	static int[] DX = { 0, 1, 0, -1 };
	static int[] DY = { -1, 0, 1, 0 };
	static int out;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		ground = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			ground[i] = Stream.of(line).mapToInt(Integer::parseInt).toArray();
		}

		tornado();
		bw.write(String.valueOf(out));

		br.close();
		bw.close();
	}

	public static void tornado() {
		int x = N / 2;
		int y = N / 2;
		int cnt = 0;
		int len = 1;
		int direction = 0;

		while (len < N) {
			// 위치 이동 및 모래 옮기기
			for (int i = 1; i <= len; i++) {
				x += DX[direction];
				y += DY[direction];
				moveSand(direction, x, y);
			}

			// 다음 이동 길이와 방향 설정
			direction = (direction + 1) % 4;
			cnt++;
			if (cnt == 2 && len != N - 1) {
				cnt = 0;
				len++;
			}
			if (cnt == 3 && len == N - 1) {
				len++;
			}
		}
	}

	public static void moveSand(int direction, int x, int y) {
		int sand = ground[x][y];

		if (direction == L) {

			if (x > 1) {
				ground[x - 2][y] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			if (x > 0) {
				ground[x - 1][y] += sand * 0.07;
				ground[x - 1][y + 1] += sand * 0.01;
			} else {
				out += sand * 0.07;
				out += sand * 0.01;
			}
			if (y > 0 && x > 0) {
				ground[x - 1][y - 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (y > 1) {
				ground[x][y - 2] += sand * 0.05;
			} else {
				out += sand * 0.05;
			}
			if (x < N - 1) {
				ground[x + 1][y] += sand * 0.07;
				ground[x + 1][y + 1] += sand * 0.01;
			} else {
				out += sand * 0.07;
				out += sand * 0.01;
			}
			if (y > 0 && x < N - 1) {
				ground[x + 1][y - 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (x < N - 2) {
				ground[x + 2][y] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			int temp = 0;
			temp += (int) (sand * 0.02);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.05);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.02);
			sand -= temp;
			if (y > 0) {
				ground[x][y - 1] += sand;
			} else {
				out += sand;
			}

		} else if (direction == D) {

			if (y > 1) {
				ground[x][y - 2] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			if (y > 0) {
				ground[x - 1][y - 1] += sand * 0.01;
				ground[x][y - 1] += sand * 0.07;
			} else {
				out += sand * 0.01;
				out += sand * 0.07;
			}
			if (y > 0 && x < N - 1) {
				ground[x + 1][y - 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (x < N - 2) {
				ground[x + 2][y] += sand * 0.05;
			} else {
				out += sand * 0.05;
			}
			if (y < N - 1) {
				ground[x - 1][y + 1] += sand * 0.01;
				ground[x][y + 1] += sand * 0.07;
			} else {
				out += sand * 0.01;
				out += sand * 0.07;
			}
			if (x < N - 1 && y < N - 1) {
				ground[x + 1][y + 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (y < N - 2) {
				ground[x][y + 2] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			int temp = 0;
			temp += (int) (sand * 0.02);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.05);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.02);
			sand -= temp;
			if (x < N - 1) {
				ground[x + 1][y] += sand;
			} else {
				out += sand;
			}

		} else if (direction == R) {

			if (x > 1) {
				ground[x - 2][y] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			if (x > 0) {
				ground[x - 1][y - 1] += sand * 0.01;
				ground[x - 1][y] += sand * 0.07;
			} else {
				out += sand * 0.01;
				out += sand * 0.07;
			}
			if (x > 0 && y < N - 1) {
				ground[x - 1][y + 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (y < N - 2) {
				ground[x][y + 2] += sand * 0.05;
			} else {
				out += sand * 0.05;
			}
			if (x < N - 1) {
				ground[x + 1][y - 1] += sand * 0.01;
				ground[x + 1][y] += sand * 0.07;
			} else {
				out += sand * 0.01;
				out += sand * 0.07;
			}
			if (x < N - 1 && y < N - 1) {
				ground[x + 1][y + 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (x < N - 2) {
				ground[x + 2][y] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			int temp = 0;
			temp += (int) (sand * 0.02);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.05);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.02);
			sand -= temp;
			if (y < N - 1) {
				ground[x][y + 1] += sand;
			} else {
				out += sand;
			}

		} else if (direction == U) {

			if (y > 1) {
				ground[x][y - 2] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			if (y > 0) {
				ground[x][y - 1] += sand * 0.07;
				ground[x + 1][y - 1] += sand * 0.01;
			} else {
				out += sand * 0.07;
				out += sand * 0.01;
			}
			if (y > 0 && x > 0) {
				ground[x - 1][y - 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (x > 1) {
				ground[x - 2][y] += sand * 0.05;
			} else {
				out += sand * 0.05;
			}
			if (y < N - 1) {
				ground[x][y + 1] += sand * 0.07;
				ground[x + 1][y + 1] += sand * 0.01;
			} else {
				out += sand * 0.07;
				out += sand * 0.01;
			}
			if (x > 0 && y < N - 1) {
				ground[x - 1][y + 1] += sand * 0.1;
			} else {
				out += sand * 0.1;
			}
			if (y < N - 2) {
				ground[x][y + 2] += sand * 0.02;
			} else {
				out += sand * 0.02;
			}
			int temp = 0;
			temp += (int) (sand * 0.02);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.05);
			temp += (int) (sand * 0.01);
			temp += (int) (sand * 0.07);
			temp += (int) (sand * 0.1);
			temp += (int) (sand * 0.02);
			sand -= temp;
			if (x > 0) {
				ground[x - 1][y] += sand;
			} else {
				out += sand;
			}

		}
		ground[x][y] = 0;
	}
}
