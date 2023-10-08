import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_6808 {
	static int[] src;
	static int lose;
	static int win;
	static boolean[] visited;
	static List<Integer> arr;
	public static void solution(int choice, int gyu, int in) {
		if (choice == src.length) {
			if (gyu > in) win++;
			else if (gyu < in) lose++;
			return ;
		} else {
			for (int i = 0; i < src.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					int gscore = arr.get(choice);
					int iscore = src[i];
					int value = gscore + iscore;
					solution(choice + 1, gscore > iscore ? gyu + value : gyu, gscore < iscore ? in + value : in);
					visited[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			src = new int[9];
			arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			win = lose = 0;
			visited = new boolean[9];
			for (int i = 0; i < 9; i++) arr.add(Integer.parseInt(st.nextToken()));
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!arr.contains(i)) src[idx++] = i;
			}
			solution(0, 0, 0);
			sb.append("#" + test);
			sb.append(" " + win + " " + lose + "\n");
		}
		System.out.println(sb);
	}
}
