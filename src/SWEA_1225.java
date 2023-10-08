import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_1225 {
	public static boolean cycle(Deque<Integer> deque) {
		for (int i = 1; i <= 5; i++) {
			int elem = deque.pollFirst() - i;
			if (elem <= 0) {
				elem = 0;
				deque.addLast(elem);
				return false;
			}
			deque.addLast(elem);
		}
		return true;
	}
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Deque<Integer> deque = new ArrayDeque<>();
		try {
			while (true) {
				int T = Integer.parseInt(br.readLine());
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < 8; i++) deque.add(Integer.parseInt(st.nextToken()));
				while (true) {
					if (!cycle(deque)) {
						break;
					}
				}
				sb.append("#");
				sb.append(T);
				sb.append(" ");
				for (int i = 0; i < 8; i++) {
					sb.append(deque.pollFirst() + (i == 7 ? "\n" : " "));
				}
			}
		} catch(Exception e) {
			System.out.println(sb);
			return ;
		}
	}
}
