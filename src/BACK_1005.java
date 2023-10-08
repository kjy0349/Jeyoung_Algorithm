import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BACK_1005 {
	static int answer;
	static int target;
	static boolean[][] graph;
	static int[] d;
	public static void find_sol(int target) {
		boolean flag = false;
		int max = 0;
		for (int i = 0; i < graph[0].length; i++) {
			if (graph[target][i]) {
				flag = true;
				if (max < d[i]) max = d[i];
				find_sol(i);
			}
		}
		if (flag) answer += max;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = inputs[0];
			int K = inputs[1];
			graph = new boolean[N][N];
			answer = 0;
			d = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < K; i++) {
				int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				graph[line[0] - 1][line[1] - 1] = true;
				graph[line[1] - 1][line[0] - 1] = true;
			}
			target = Integer.parseInt(br.readLine()) - 1;
			find_sol(target);
			bw.write(answer + "\n");
		}
		bw.flush();
	}
}
