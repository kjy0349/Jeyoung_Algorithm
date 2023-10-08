import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BACK_11660 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] nums = new int[N][N + 1];
		int[][] sums = new int[N][N];
		int sub_sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sub_sum = 0;
			for (int j = 1; j <= N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken()) + sub_sum;
				sub_sum = nums[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int result = 0;
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken());
			for (int j = x1; j <= x2; j++) {
				result += (nums[j][y2] - nums[j][y1 - 1]);
			}
			bw.write(result + "\n");
		}
		bw.flush();
	}
}
