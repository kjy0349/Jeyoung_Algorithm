import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_16637 {
	static long answer;
	static int N;
	static String line;
	static char[] opers;
	static int[] nums;
	
	public static long calc(char oper, long num, long sum) {
		switch (oper) {
			case '+':
				return num + sum;
			case '-':
				return num - sum;
			case '*':
				return sum * num;
		}
		System.out.println("error.");
		return -1;
	}
	
	public static void recur(int depth, long subSum) {		
		if (depth == nums.length) {
			if (subSum > answer) answer = subSum;
			return ;
		}
		recur(depth + 1, calc(opers[depth], subSum, nums[depth]));
		if (depth + 2 <= nums.length) {			
			recur(depth + 2, calc(opers[depth], subSum, calc(opers[depth + 1], nums[depth], nums[depth + 1])));
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		line = br.readLine();
		answer = Long.MIN_VALUE;
		opers = new char[(N - 1) / 2];
		nums = new int[(N - 1) / 2];
		int idx = 0;
		for (int i = 1; i < line.length(); i+=2) {
			opers[idx] = line.charAt(i);
			nums[idx++] = line.charAt(i + 1) - '0';
		}
		recur(0, line.charAt(0) - '0');
		System.out.println(answer);
	}
}
