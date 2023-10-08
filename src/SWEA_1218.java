import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
public class SWEA_1218 {
	static HashMap<String, String> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new HashMap<>();
		map.put("(", ")");
		map.put("[", "]");
		map.put("{", "}");
		map.put("<", ">");
		Deque<String> st = new ArrayDeque<>();
		for (int test = 1; test <= 10; test++) {
			boolean is_poss = true;
			st.clear();
			int size = Integer.parseInt(br.readLine());
			String line = br.readLine();
			for (int i = 0; i < size; i++) {
				String elem = Character.toString(line.charAt(i));
				if (map.keySet().contains(elem)) {
					st.push(elem);
				} else {
					if (!st.isEmpty()) {
						String compare = st.pop();
						if (!(map.get(compare).equals(elem))) {
							is_poss = false;
							break;
						}
					} else {
						is_poss = false;
						break;
					}
				}
			}
			sb.append("#");
			sb.append(test);
			sb.append(" ");
			sb.append(is_poss ? 1 : 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
