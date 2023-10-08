import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_2563 {
	static boolean[][] visited;
	static int answer;
	public static void solution(int x, int y) {
		for (int i = x; i < x + 10; i++) {
			for (int j = y; j < y + 10; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					answer++;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		visited = new boolean[101][101];
		answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			solution(x, y);
		}
		System.out.println(answer);
	}
}
