import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BACK_1759 {
	static Set<Character> set;
	static char[] nums;
	static char[] choosed;
	static int R;
	static StringBuilder sb = new StringBuilder();
	public static void comb(int start, int depth, int jcount, int mcount) {
		if (depth == R) {
			if (jcount >= 1 && mcount >= 2) {
				for (int i = 0; i < choosed.length; i++) {
					sb.append(choosed[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = start; i < nums.length; i++) {
			choosed[depth] = nums[i];
			comb(i + 1, depth + 1, set.contains(choosed[depth]) ? jcount + 1 : jcount,
			!set.contains(choosed[depth]) ? mcount + 1 : mcount);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
		R = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		nums = new char[N];
		choosed = new char[R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = st.nextToken().charAt(0);
		Arrays.sort(nums);
		comb(0, 0, 0, 0);
		System.out.print(sb);
	}
}
