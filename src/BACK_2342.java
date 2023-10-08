package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BACK_2342 {
	static ArrayList<Integer> coms;
	static int answer;
	static int [][][] dp; // 0,0 ~ 4,4
	static int count;
	public static int getMPower(int start, int target) {
		if (start == 0) return 2; // 시작 지점에 발이 있는 경우
		if (start == 1) {
			if (target == 2 || target == 4) return 3;
			else return 4;
		} else if (start == 2) {
			if (target == 1 || target == 3) return 3;
			else return 4;
		} else if (start == 3) {
			if (target == 2 || target == 4) return 3;
			else return 4;
		} else if (start == 4) {
			if (target == 1 || target == 3) return 3;
			else return 4;
		} else return -1;
	}
	
	public static void recur(int depth, int left, int right, int subSum) {
		count++;
		if (depth == coms.size()) {
			if (answer > subSum) {
				answer = subSum;
			}
			return;
		}
		int target = coms.get(depth);
		if (left == target || right == target) { // 이미 밟은 곳 밟을 때
			if (subSum + 1 < dp[depth][left][right]) {
				dp[depth][left][right] = subSum + 1;
				recur(depth + 1, left, right, subSum + 1);
			}
		} else { // 밟지 않은 곳 밟을 때
			int leftPower = getMPower(left, target);
			int rightPower = getMPower(right, target);
			if (leftPower == -1 || rightPower == -1) System.out.println("Error.");
			if (subSum + leftPower < dp[depth][target][right]) {
				dp[depth][target][right] = subSum + leftPower;
				recur(depth + 1, target, right, subSum + leftPower);
			}
			if (subSum + rightPower < dp[depth][left][target]) {
				dp[depth][left][target] = subSum + rightPower;
				recur(depth + 1, left, target, subSum + rightPower);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		coms = new ArrayList<>();
		count = 0;
		while (true) {
			int elem = Integer.parseInt(st.nextToken());
			if (elem == 0) break;
			coms.add(elem);
		}
		dp = new int[coms.size() + 1][5][5];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = 0; k < dp[i][j].length; k++) dp[i][j][k] = Integer.MAX_VALUE;
			}
		}

		answer = Integer.MAX_VALUE;
		recur(0, 0, 0, 0);
		System.out.println(answer + " " + count + " " + coms.size());
	}
}
