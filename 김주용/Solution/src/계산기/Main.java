package 계산기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int zflip4 = 80000;
	static int s23 = 650000;
	
	static int fee;
	static int initMonth;
	
	static int month = 24;
	
	static double discount = 0.75;
	static double tax = 0.1;
	
	public static void main(String[] args) {
		int fee = 75000;
		double a = get(3, 0.75, fee, 0);
		double b = get(6, 1, fee, 9900 * 3);
		System.out.println((b + zflip4) - (a + s23));
	}
	
	private static double get(int initMonth, double discount, int fee, int service) {
		double ret = service;
		ret += (initMonth) * 85000 * discount;
		ret += (month - initMonth) * (fee * discount + (fee * discount) * 0.1);
		System.out.println(ret);
		return ret;
	}

}
