import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1149 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N + 1][3];
		StringTokenizer st;
		for (int i = 1; i < input.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) input[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] d = new int[N + 1][3];
		for (int i = 1; i < d.length; i++) {
			d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + input[i][0];
			d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + input[i][1];
			d[i][2] = Math.min(d[i - 1][1], d[i - 1][0]) + input[i][2];
		}
		System.out.println(Arrays.stream(d[N]).min().getAsInt());
	}
}
