package 스터디.같이눈사람만들래_20366;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

class Snowman {
	int idxA;
	int idxB;
	long height;

	public Snowman(int idxA, int idxB, long height) {
		this.idxA = idxA;
		this.idxB = idxB;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Snowman [idxA=" + idxA + ", idxB=" + idxB + ", height=" + height + "]";
	}

}

public class Main {
	static int N;
	static long[] diameters;

	static long answer = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		diameters = Stream.of(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		solution();
	}


	public static void solution() {
		ArrayList<Snowman> snowmans = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				snowmans.add(new Snowman(i, j, diameters[i] + diameters[j]));
			}
		}
		Collections.sort(snowmans, (o1, o2) -> {
			return (int) (o1.height - o2.height);
		});
//		for (Snowman s : snowmans) {
//			System.out.println(s);
//		}
		int M = snowmans.size();
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				Snowman elsaSnowman = snowmans.get(i);
				Snowman annaSnowman = snowmans.get(j);
				if (elsaSnowman.idxA == annaSnowman.idxA || elsaSnowman.idxB == annaSnowman.idxB) {
					continue;
				}
				if (elsaSnowman.idxB == annaSnowman.idxA || elsaSnowman.idxA == annaSnowman.idxB) {
					continue;
				}
				answer = Math.min(answer, Math.abs(elsaSnowman.height - annaSnowman.height));
				break;
			}
		}

		System.out.println(answer);
	}

}
