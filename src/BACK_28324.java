import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_28324 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] V = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) V[i] = Integer.parseInt(st.nextToken());
		int answer = 1;
		int current = 1;
		for (int i = N - 2; i >= 0; i--) {
			if (current + 1 <= V[i]) answer += ++current;
			else answer += current;
			System.out.print(current + " ");
		}
		System.out.println();
		System.out.println(answer);
	}
}
