import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BACK_17952 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] line = new int[3];
			int idx = 0;
			while (st.hasMoreElements()) {
				line[idx++] = Integer.parseInt(st.nextToken());
			}
			if (line[0] == 0) {				
				if (!stack.isEmpty()) {
					int[] elem = stack.peek();
					elem[2]--;
					if (elem[2] == 0) {
						answer += elem[1];
						stack.removeFirst();
					}
				}
			} else {
				line[2]--;
				if (line[2] == 0) answer += line[1];
				else stack.addFirst(line);
			}
		}
		System.out.println(answer);
	}
}
