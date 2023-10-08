import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BACK_2493 {
	static class Cord {
		int value;
		int index;
		Cord (int value, int index) {
			this.value = value;
			this.index = index;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] tops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Deque<Cord> st = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			while (true) {
				if (st.isEmpty()) {
					sb.append("0 ");
					st.push(new Cord(tops[i], i));
					break;
				}
				Cord cord = st.peek();
				if (cord.value > tops[i]) {
					sb.append((cord.index + 1) + " ");
					st.push(new Cord(tops[i], i));
					break;
				} else st.pop();
			}			
		}
		for (Cord cord : st) System.out.println(cord.index + " " + cord.value);
		System.out.println(sb);
	}
}
