import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BACK_6987 {
	static int[][] arr;
	static boolean is_poss;
	public static void dfs(int a, int b) {
		if (a == 5) {
			is_poss = true;
			return;
		}
		for (int i = 0; i < 3; i++) {
			arr[a][i]--;
			arr[b][2 - i]--;
			if (arr[a][i] >= 0 && arr[b][2 - i] >= 0) {
				if (b == 5) {
					dfs(a + 1, a + 2);
				} else dfs(a, b + 1);
			}
			arr[a][i]++;
			arr[b][2 - i]++;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		arr = new int[6][3];
		for (int test = 0; test < 4; test++) {
			st = new StringTokenizer(br.readLine());
			is_poss = false;
			int[] sum = new int[3];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					sum[j] += arr[i][j];
				}
			}
			dfs(0, 1);
			if (sum[0] != sum[2] || 30 - sum[1] != sum[0] + sum[2]) is_poss = false;
			bw.write((is_poss? "1" : "0") + " ");
		}
		bw.flush();
	}
}
