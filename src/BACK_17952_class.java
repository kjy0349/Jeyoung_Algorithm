import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BACK_17952_class {
	static class Work {
		int A;
		int T;
		Work(int A, int T) {
			this.A = A;
			this.T = T;
		}
	}
	static Work cur;
	static ArrayDeque<Work> stack = new ArrayDeque<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		cur = null;
		int answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 업무 여부
			if (w == 0) { // 새로 들어온 업무가 없을 때
				if (cur != null) { // 진행 중인 업무가 있음
					cur.T--;
					if (cur.T == 0) {
						answer += cur.A;
						cur = stack.pollFirst();
					}
				}
			} else {
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				if (cur != null) stack.offerFirst(cur); // 일이 들어왔는데, 이미 진행중인 업무가 있을 때
				cur = new Work(A, T - 1);
				if (cur.T == 0) {
					answer += cur.A;
					cur = stack.pollFirst();
				}
			}
		}
		System.out.println(answer);
	}
}
