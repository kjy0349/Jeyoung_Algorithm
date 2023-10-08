import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_11060 {
	static int[] d;
	static int[] input;
	private static void find(int idx) {
		for (int i = input[idx]; i >= 1; i--) {
			int next = idx + i;
			if (next < input.length && (d[next] == 0 || d[idx] + 1 < d[next])) {
				d[next] = d[idx] + 1;
				find(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(0);
			return ;
		}
		d = new int[N + 1];
		input = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		find(1);
		System.out.println(d[N] == 0 ? -1 : d[N]);
	}
}
