import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_12891 {
	public static boolean check(int[] target, int[] counts) {
		boolean is_poss = true;
		for (int i = 0; i < target.length; i++) {
			if (counts[i] < target[i]) {
				is_poss = false;
				break;
			}
		}
		return is_poss;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		String line = br.readLine();
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		int[] target = new int[4];
		for (int i = 0; i < 4; i++) target[i] = Integer.parseInt(st.nextToken());
		int[] counts = new int[4];
		for (int i = 0; i < S; i++) {
			if (line.charAt(i) == 'A') counts[0]++;
			else if (line.charAt(i) == 'C') counts[1]++;
			else if (line.charAt(i) == 'G') counts[2]++;
			else if (line.charAt(i) == 'T') counts[3]++;
		}
		int left = 0;
		for (int i = S; i < line.length(); i++) {
			if (check(target, counts)) {
				answer++;
			}
			if (line.charAt(i) == 'A') counts[0]++;
			else if (line.charAt(i) == 'C') counts[1]++;
			else if (line.charAt(i) == 'G') counts[2]++;
			else if (line.charAt(i) == 'T') counts[3]++;
			if (line.charAt(left) == 'A') counts[0]--;
			else if (line.charAt(left) == 'C') counts[1]--;
			else if (line.charAt(left) == 'G') counts[2]--;
			else if (line.charAt(left) == 'T') counts[3]--;
			left++;
		}
		if (check(target, counts)) {
			answer++;
		}
		System.out.println(answer);
	}
}
