import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_15961 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] belt = new int[N];
		for (int i = 0; i < N; i++) belt[i] = Integer.parseInt(br.readLine());
		int[] visited = new int[d + 1];
		int l, r;
		l = 0;
		r = k - 1;
		int answer = 0;
		int subSum = 0;
		for (int i = l; i <= r; i++) {
			if (visited[belt[i]] == 0 && belt[i] != c) {
				subSum++;
			}
			visited[belt[i]]++;
		}
		if (subSum > answer) answer = subSum;
		do {
			visited[belt[l]]--;
			if (visited[belt[l]] == 0 && belt[l] != c) subSum--;
			l++;
			r++;
			if (l == belt.length) l = 0;
			if (r == belt.length) r = 0;
			if (visited[belt[r]] == 0 && belt[r] != c) {
				subSum++;
			}
			visited[belt[r]]++;
			if (subSum > answer) answer = subSum;
		} while(l != 0);
		System.out.println(answer + 1);
	}
}
