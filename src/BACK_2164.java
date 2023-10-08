import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BACK_2164 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> que = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) que.add(i);
		while (que.size() != 1) {
			que.poll();
			que.add(que.poll());
		}
		System.out.println(que.poll());
	}
}
